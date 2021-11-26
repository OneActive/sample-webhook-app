package ai.active.morfeus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationLogger {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationLogger.class);

    public static void logError(String message, Object object) {
        logger.error(message, object);
    }

    public static void logInfo(String message, Object object) {
        logger.info(message, object);
    }

    public static void logError(String message) {
        logger.error(message);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }
    public static void logInfo(String message,String className) {
        logger.info(message,className);
    }
}
