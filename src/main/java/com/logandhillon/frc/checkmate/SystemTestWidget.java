package com.logandhillon.frc.checkmate;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;

import com.logandhillon.frc.checkmate.util.Logger;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.Commands;

public class SystemTestWidget {
    public static void create(ShuffleboardLayout parent, String id, Supplier<TestResult> runnable) {
        ShuffleboardLayout layout = parent.getLayout(id, BuiltInLayouts.kGrid)
                .withSize(3, 1).withProperties(Map.of(
                        "Number of columns", 3,
                        "Label position", "HIDDEN"));

        GenericEntry isOk = layout.add(id + " OK?", true)
                .withSize(1, 1)
                .withWidget(BuiltInWidgets.kBooleanBox)
                .withPosition(1, 0)
                .getEntry();

        GenericEntry msg = layout.add(id + " Output", "")
                .withSize(1, 1)
                .withPosition(2, 0)
                .withWidget(BuiltInWidgets.kTextView)
                .getEntry();

        layout.add(
                "Execute " + id,
                Commands.runOnce(() -> {
                    Logger.log(Level.INFO, "Running test '%s'", id);
                    TestResult result = runnable.get();
                    isOk.setBoolean(result.ok);
                    msg.setString(String.format("%s: %s", result.ok ? "PASS" : "FAIL",
                            Objects.requireNonNullElse(result.message, "no message")));
                })
                        .withName("Execute")
                        .ignoringDisable(false))
                .withSize(1, 1)
                .withPosition(0, 0)
                .withWidget("Command");
    }
}