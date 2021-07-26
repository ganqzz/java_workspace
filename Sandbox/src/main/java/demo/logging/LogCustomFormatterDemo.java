package demo.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class LogCustomFormatterDemo {

    private static final Logger logger = Logger.getGlobal();
//	private static final Logger logger = LogManager.getLogManager()
//			.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("logdemo.log");
        FileOutputStream fos = new FileOutputStream(file);

        // Handler
        //   ConsoleHandler
        //   StreamHandler
        //   SocketHandler
        //   FileHandler : limit, rotation

        // Custom Formatter
        Handler handler = new StreamHandler(fos, new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + " : " + record.getSourceClassName() + " -:- "
                       + record.getSourceMethodName() + " -:- " + record.getMessage() + "\n";
            }
        });

        logger.addHandler(handler);
        logger.info("Hello VTC!");
    }
}
