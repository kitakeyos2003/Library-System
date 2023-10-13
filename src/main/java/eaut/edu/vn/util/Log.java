package eaut.edu.vn.util;

import org.apache.log4j.Logger;

public class Log {
    private static final Logger LOG = Logger.getLogger(Log.class);

    // Info Level Logs
    public static void info(String message) {
        LOG.info(message);
    }

    public static void info(Object object) {
        LOG.info(object);
    }

    // Warn Level Logs
    public static void warn(String message) {
        LOG.warn(message);
    }

    public static void warn(Object object) {
        LOG.warn(object);
    }

    public static void warn(String message, Throwable throwable) {
        LOG.warn(message, throwable);
    }

    // Error Level Logs
    public static void error(String message) {
        LOG.error(message);
    }

    public static void error(String message, Throwable throwable) {
        LOG.error(message, throwable);
    }

    public static void error(Object object) {
        LOG.error(object);
    }

    // Fatal Level Logs
    public static void fatal(String message) {
        LOG.fatal(message);
    }

    // Debug Level Logs
    public static void debug(String message) {
        LOG.debug(message);
    }

    public static void debug(String message, Throwable throwable) {
        LOG.debug(message, throwable);
    }

    public static void debug(Object object) {
        LOG.debug(object);
    }
}
