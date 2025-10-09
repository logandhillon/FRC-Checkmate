package com.logandhillon.frc.checkmate.util;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.Command;

public class DebugCommand {
    private static ShuffleboardTab tab;

    private DebugCommand() {}

    /**
     * Adds a {@link Command} to the debug tab on {@link ShuffleboardTab} as a button.
     * @param name The name to be displayed for the button
     * @param cmd The {@link Command} to run
     */
    public static void register(String name, Command cmd) {
        if (tab == null) tab = Shuffleboard.getTab("Debug");

        tab.add(name, cmd.withName("DEBUG-" + name).ignoringDisable(true)).withWidget("Command");
    }

    @SuppressWarnings("unchecked")
    public static <T> Supplier<T> putNumber(String name, T defaultValue) {
        try (final SimpleWidget widget = tab.add(name, defaultValue)) {
            return () -> (T)widget.getEntry().get().getValue();
        }
    }
}
