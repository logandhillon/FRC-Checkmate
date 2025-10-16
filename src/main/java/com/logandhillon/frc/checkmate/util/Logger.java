package com.logandhillon.frc.checkmate.util;

import java.util.logging.Level;

public class Logger {
    public static void log(Level level, String format, Object... args) {
        System.out.printf("[Checkmate] (" + level.getName() + ") " + format + "%n", args);
    }
}
