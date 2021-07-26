package etc.harness;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public final class TextComponents {
    private TextComponents() {}

    private static TextComponent DEFAULT = System.console() == null
                                           ? streamComponent(System.in, System.out)
                                           : new ConsoleWrapper(System.console());

    /**
     * The default system text I/O device.
     *
     * @return the default device
     */
    public static TextComponent defaultTextComponent() {
        return DEFAULT;
    }

    /**
     * Returns a text I/O device wrapping the given streams. The default system encoding is used to
     * decode/encode data.
     *
     * @param in  an input source
     * @param out an output target
     * @return a new device
     */
    public static TextComponent streamComponent(InputStream in, OutputStream out) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        PrintWriter writer = new PrintWriter(out, true);
        return new CharacterComponent(reader, writer);
    }

    /**
     * Returns a text I/O device wrapping the given streams.
     *
     * @param reader an input source
     * @param writer an output target
     * @return a new device
     */
    public static TextComponent characterComponent(BufferedReader reader, PrintWriter writer) {
        return new CharacterComponent(reader, writer);
    }
}
