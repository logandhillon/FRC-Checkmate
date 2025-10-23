public class examples {

    public static void main(String[] args) {
        // Example 1: Basic pass/fail tests
        RobotSystemTest.register("Expect a failure", () -> TestResult.fail("Generic failure"));
        RobotSystemTest.register("Expect a pass", () -> TestResult.success());
        RobotSystemTest.register("Expect a pass with msg", () -> TestResult.success("Generic success"));

        // Example 2: Conditional test based on logic
        RobotSystemTest.register("Battery voltage should be above 11V", () -> {
            double batteryVoltage = RobotHardware.getBatteryVoltage();
            if (batteryVoltage < 11.0) {
                return TestResult.fail("Battery too low: " + batteryVoltage + "V");
            }
            return TestResult.success("Battery OK: " + batteryVoltage + "V");
        });

        // Example 3: Simulated subsystem behavior
        RobotSystemTest.register("Drivetrain responds to forward command", () -> {
            Drivetrain drivetrain = new Drivetrain();
            drivetrain.setForwardPower(0.5);
            double encoderVelocity = drivetrain.getEncoderVelocity();

            if (encoderVelocity > 0.1) {
                return TestResult.success("Drivetrain moving forward (" + encoderVelocity + ")");
            }
            return TestResult.fail("Drivetrain not responding, encoder: " + encoderVelocity);
        });

        // Example 4: Sensor check
        RobotSystemTest.register("Gyro initialization", () -> {
            Gyro gyro = new Gyro();
            if (!gyro.isCalibrated()) {
                return TestResult.fail("Gyro failed to calibrate");
            }
            return TestResult.success("Gyro calibrated successfully");
        });

        // Example 5: Multiple condition test
        RobotSystemTest.register("Arm subsystem safety check", () -> {
            Arm arm = new Arm();
            boolean limits = arm.checkLimitSwitches();
            boolean encoderOK = arm.getEncoderValue() >= 0;

            if (!limits)
                return TestResult.fail("Limit switches not functioning");
            if (!encoderOK)
                return TestResult.fail("Encoder value invalid");

            return TestResult.success("Arm subsystem OK");
        });

        // Example 6: Expected failure (to demonstrate framework)
        RobotSystemTest.register("Expect failure in drivetrain", () -> {
            return TestResult.fail("Intentional failure to test reporting system");
        });
    }
}