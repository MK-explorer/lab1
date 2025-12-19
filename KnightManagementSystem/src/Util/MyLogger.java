package Util;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {
    private static final Logger logger = Logger.getLogger("KnightSystem");
    private static boolean initialized = false;

    public static Logger getLogger() {
        if (!initialized) {
            try {
                // 1. Лог у файл
                FileHandler fileHandler = new FileHandler("knight_system.log", true);
                fileHandler.setFormatter(new SimpleFormatter());
                fileHandler.setLevel(Level.ALL);
                logger.addHandler(fileHandler);

                // 2. Лог у консоль (тільки INFO і вище)
                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setFormatter(new SimpleFormatter());
                consoleHandler.setLevel(Level.INFO);
                logger.addHandler(consoleHandler);

                // 3. Критичні помилки → на e-mail (ТІЛЬКИ SEVERE)
                EmailHandler emailHandler = new EmailHandler();
                emailHandler.setLevel(Level.SEVERE);
                logger.addHandler(emailHandler);

                logger.setLevel(Level.ALL);
                logger.setUseParentHandlers(false);
                initialized = true;

                logger.info(" Logger ініціалізовано успішно!");

            } catch (IOException e) {
                System.err.println(" Не вдалося створити лог-файл: " + e.getMessage());
            }
        }
        return logger;
    }

    // Методи для логування
    public static void logInfo(String message) {
        getLogger().info(message);
    }

    public static void logWarning(String message) {
        getLogger().warning(message);
    }

    public static void logSevere(String message) {
        getLogger().severe(message);
    }

    public static void logException(String message, Exception e) {
        getLogger().log(Level.SEVERE, message, e);
    }
}