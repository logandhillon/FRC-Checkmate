# Examples

This document provides example test cases for the **FRC-Checkmate** system testing framework.  
These examples are designed to help developers and new team members understand how to write and register tests using `RobotSystemTest` and `TestResult`.

---

## Basic Tests

```java
RobotSystemTest.register("Expect a failure", () -> TestResult.fail("Generic failure"));
RobotSystemTest.register("Expect a pass", () -> TestResult.success());
RobotSystemTest.register("Expect a pass with msg", () -> TestResult.success("Generic success"));
```

These demonstrate the simplest usage of the test framework:

- `TestResult.success(reason?)` — the test passed, optionally with a success message.
- `TestResult.fail(reason)` — the test failed, with an explanation message.

---

## Conditional Tests

```java
RobotSystemTest.register("Battery voltage should be above 11V", () -> {
    double batteryVoltage = RobotHardware.getBatteryVoltage();
    if (batteryVoltage < 11.0) {
        return TestResult.fail("Battery too low: " + batteryVoltage + "V");
    }
    return TestResult.success("Battery OK: " + batteryVoltage + "V");
});
```

This test checks the robot’s battery voltage and fails if it’s below the safe operating threshold.

---

## Subsystem Behavior Test

```java
RobotSystemTest.register("Drivetrain responds to forward command", () -> {
    Drivetrain drivetrain = new Drivetrain();
    drivetrain.setForwardPower(0.5);
    double encoderVelocity = drivetrain.getEncoderVelocity();

    if (encoderVelocity > 0.1) {
        return TestResult.success("Drivetrain moving forward (" + encoderVelocity + ")");
    }
    return TestResult.fail("Drivetrain not responding, encoder: " + encoderVelocity);
});
```

This test ensures the drivetrain subsystem responds properly when commanded to move.

---

## Sensor Initialization Test

```java
RobotSystemTest.register("Gyro initialization", () -> {
    Gyro gyro = new Gyro();
    if (!gyro.isCalibrated()) {
        return TestResult.fail("Gyro failed to calibrate");
    }
    return TestResult.success("Gyro calibrated successfully");
});
```

Validates that the gyro sensor is properly calibrated before use.

---

## Multiple Condition Test

```java
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
```

Tests multiple conditions to ensure all safety and sensor checks for the arm subsystem pass.

---

## Intentional Failure Example

```java
RobotSystemTest.register("Expect failure in drivetrain", () -> {
    return TestResult.fail("Intentional failure to test reporting system");
});
```

Used to confirm that your test reporting system handles failures correctly.

---

## Notes

- Each test should return a `TestResult` — either **success** or **failure**.
- You can access any robot subsystems, sensors, or simulated devices in your tests.
- Tests can be run automatically before a match, during system checks, or manually by developers.
- Use descriptive names and clear failure messages to make diagnosing issues easy.

---

### Suggested Naming Convention

Use descriptive names that clearly define the intent:

- ✅ `"Battery voltage above threshold"`
- ✅ `"Gyro calibration completes within 3s"`
- ❌ `"Test 1"` or `"Check stuff"`

---

**FRC-Checkmate** is built for maintainable, automated system validation.  
Use these examples as templates when writing your own robot system tests.
