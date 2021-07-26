package demo.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHierarchyDemo {

    private static final Logger root_logger = Logger.getLogger(""); // RootLogger
    private static final Logger hoge_logger = Logger.getLogger("hoge");
    private static final Logger fuga_logger = Logger.getLogger("hoge.fuga");

    public static void main(String[] args) {

        System.out.println(root_logger.getLevel());
        System.out.println(hoge_logger.getLevel());
        System.out.println(fuga_logger.getLevel());

        root_logger.setLevel(Level.WARNING);
        System.out.println(root_logger.getLevel());

        root_logger.getHandlers()[0].setLevel(Level.FINER);

        hoge_logger.setLevel(Level.WARNING);
        Handler h = new ConsoleHandler();
        h.setLevel(Level.FINER);
        fuga_logger.addHandler(h);

        fuga_logger.setLevel(Level.FINER);
        Handler hf = new ConsoleHandler();
        hf.setLevel(Level.FINER);
        fuga_logger.addHandler(hf);

        fuga_logger.log(Level.FINER, "fuga");

        // Loggerのレベルは、自身から近い順番のレベルで決定され、各Handlerのレベルよりも優先される。
    }
}
