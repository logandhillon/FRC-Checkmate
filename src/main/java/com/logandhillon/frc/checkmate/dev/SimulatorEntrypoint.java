package com.logandhillon.frc.checkmate.dev;

import com.logandhillon.frc.checkmate.RobotSystemTest;

import edu.wpi.first.wpilibj.RobotBase;

public class SimulatorEntrypoint {
    public static void main(String[] args) {
        RobotBase.startRobot(DevRobot::new);
    }

    public static void registerTests() {
        RobotSystemTest.register("Expect a failure", () -> RobotSystemTest.TestResult.fail("Generic failure"));
    }
}
