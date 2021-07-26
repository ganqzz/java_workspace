package demo.logging;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogConfigurationDemo {

    public static void main(String[] args) throws SecurityException, IOException {
        LogManager lm = LogManager.getLogManager();
        lm.readConfiguration(
                LogConfigurationDemo.class.getResourceAsStream("logging.properties"));
//		Logger logger = lm.getLogger(Logger.GLOBAL_LOGGER_NAME);
        Logger logger = lm.getLogger(""); // RootLogger

        System.out.println(logger.getLevel());

        for (Handler h : logger.getHandlers())
            System.out.println(h);

        for (int i = 0; i < 10; i++) {
            // logging messages
            logger.log(Level.INFO, "Msg" + i);
        }
    }
}
