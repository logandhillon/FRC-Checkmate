package com.logandhillon.frc.checkmate;

/**
 * Test result for registered tests in {@link DebugCommand}.
 * 
 * @see DebugCommand#register
 */
public class TestResult {
    protected final boolean ok;
    protected final String message;

    private TestResult(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    /**
     * @return test result that PASSED with no message
     */
    public static TestResult success() {
        return new TestResult(true, null);
    }

    /**
     * @param message any information to note about the test
     * @return test result that PASSED
     */
    public static TestResult success(String message) {
        return new TestResult(true, message);
    }

    /**
     * @param message reason the test failed
     * @return test result that FAILED
     */
    public static TestResult fail(String message) {
        return new TestResult(false, message);
    }
}