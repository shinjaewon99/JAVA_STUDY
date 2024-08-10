package junit;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

/**
 * 테스트가 얼마나 걸리는지 측정하는 클래스
 */
public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    // private static final long THRESHOLD = 1000L;
    private final long THRESHOLD;


    public FindSlowTestExtension(final long time) {
        this.THRESHOLD = time;
    }

    @Override
    public void beforeTestExecution(final ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {
        Method requiredTestMethod = context.getRequiredTestMethod();
        SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);

        String testMethodName = context.getRequiredTestMethod().getName();

        ExtensionContext.Store store = getStore(context);
        long start_time = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - start_time;

        if (duration > THRESHOLD && annotation == null) {
            System.out.printf(" 속도가 너무 느립니다. [%s] 에 @Slowtest를 작성 해야 합니다. ", testMethodName);
        }
    }

    private static ExtensionContext.Store getStore(final ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();

        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
        return store;
    }
}
