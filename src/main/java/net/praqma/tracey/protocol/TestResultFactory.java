package net.praqma.tracey.protocol;

import net.praqma.tracey.protocol.Events.*;

import java.util.logging.Logger;

public class TestResultFactory {
    private static final Logger log = Logger.getLogger( TestResultFactory.class.getName() );

    private static TestResult.Builder prepareTestResult(final TestResult.TestResultType result) {
        final TestResult.Builder testResult = TestResult.newBuilder();
        testResult.setResult(result);
        return testResult;
    }

    public static TestResult createSuccess(final String testId) {
        return prepareTestResult(TestResult.TestResultType.SUCCESS).setTestId(testId).build();
    }

    public static TestResult createUnstable(final String testId) {
        return prepareTestResult(TestResult.TestResultType.UNSTABLE).setTestId(testId).build();
    }

    public static TestResult createFailed(final String testId) {
        return prepareTestResult(TestResult.TestResultType.FAILED).setTestId(testId).build();
    }

    public static TestResult createSkipped(final String testId) {
        return prepareTestResult(TestResult.TestResultType.SKIPPED).setTestId(testId).build();
    }

    public static TestResult createAborted(final String testId) {
        return prepareTestResult(TestResult.TestResultType.ABORTED).setTestId(testId).build();
    }

    public static TestResult create(final String testId, final TestResult.TestResultType result) {
        return prepareTestResult(result).setTestId(testId).build();
    }
}