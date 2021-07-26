package demo.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogLevelsDemo {

    // private static final Logger logger = Logger.getLogger(""); // RootLogger
    // private static final Logger logger = Logger.getLogger("hoge");
    private static final Logger logger = Logger.getAnonymousLogger();
    // private static final Logger logger = Logger.getGlobal(); // "global"
    // private static final Logger logger = LogManager.getLogManager()
    //     .getLogger(Logger.GLOBAL_LOGGER_NAME); // "global"

    public static void main(String[] args) {
        // Default Level = INFO
        logger.severe("Logging an SEVERE-level message");
        logger.warning("Logging an WARNING-level message");
        logger.info("Logging an INFO-level message");
        logger.config("Logging an CONFIG-level message");
        logger.fine("Logging an fine-level message");
        logger.finer("Logging an FINER-level message");
        logger.finest("Logging an FINEST-level message");

        System.err.println("---");

        logger.setLevel(Level.ALL);

        System.out.println(logger.getHandlers().length); // RootLogger以外は、0

        Handler handler;
        if (logger.getHandlers().length > 0) {
            handler = logger.getHandlers()[0]; // get RootLogger's Default Console Handler
        } else {
            logger.setUseParentHandlers(
                    false); // suppress Parent(include Root)'s Default Console Handler
            handler = new ConsoleHandler();
            logger.addHandler(handler);
        }

        handler.setLevel(Level.FINEST);

        logger.severe("Logging an SEVERE-level message again");
        logger.warning("Logging an WARNING-level message again");
        logger.info("Logging an INFO-level message again");
        logger.config("Logging an CONFIG-level message again");
        logger.fine("Logging an fine-level message again");
        logger.finer("Logging an FINER-level message again");
        logger.finest("Logging an FINEST-level message again");

        System.err.println("---");

        // FINER
        logger.entering(LogLevelsDemo.class.getName(), "main");
        logger.exiting(LogLevelsDemo.class.getName(), "main");

        // Parameterized
        logger.log(Level.INFO, "{0}", "Java");
        logger.log(Level.INFO, "{0} {1} {2}", new Object[]{"Java", "C#", "PHP"});
        logger.entering(LogLevelsDemo.class.getName(), "main", new Object[]{"Java", "C#", "PHP"});
        logger.exiting(LogLevelsDemo.class.getName(), "main", "Java");
    }
}
