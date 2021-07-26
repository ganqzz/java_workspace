package demo.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFileHandlerDemo {
    private static final Logger logger = Logger.getLogger("com.pluralsight");

    public static void main(String[] args) throws SecurityException, IOException,
                                                  InterruptedException {
        FileHandler h = new FileHandler("%h/myapp_%g.log", 2000, 4);
        h.setFormatter(new SimpleFormatter()); // default XMLFormatter
        logger.addHandler(h);

        for (int i = 0; i < 100; i++) {
            logger.info("message: " + i);
            Thread.sleep(10);
        }
    }
}
