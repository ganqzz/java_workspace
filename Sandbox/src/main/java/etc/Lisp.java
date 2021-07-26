package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Lisp {
    public static final int VERSION_MAJOR = 0;
    public static final int VERSION_MINOR = 1;
    public static final int VERSION_REVISION = 0;

    public static String getVersion() {
        return (VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_REVISION);
    }

    public static void main(String[] args) throws Exception {
        Lisp lisp = new Lisp();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        final String PROMPT1 = "LISP>";
        final String PROMPT2 = "    >";
        String prompt = PROMPT1;
        String line;

        System.out.println("-----------------------------------");
        System.out.println("        LISP   INTERPRETER         ");
        System.out.println("-----------------------------------");
        System.out.println(" Version " + Lisp.getVersion());
        System.out.println();
        System.out.println(" 'ctrl-z' to exit this interpreter ");
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();

        for (; ; ) {
            System.out.print(prompt + lisp.getIndent());

            line = in.readLine();
            if (line == null) {
                break;
            }

            line = line.trim();
            if (line.length() == 0) {
                continue;
            }

            if (line.charAt(0) == ':') {
                // System Command
                line = line.toLowerCase();
                if (":quit".equals(line) || ":exit".equals(line) || ":bye".equals(line)) {
                    break;
                } else {
                    switch (line) {
                        case ":help":
                            System.out.println("[Help]");
                            System.out.println(" :bye      same as ':exit'");
                            System.out.println(" :cancel   cancel inputting lisp code");
                            System.out.println(" :exit     exit interpreter");
                            System.out.println(" :help     show this help text");
                            System.out.println(" :quit     same as ':exit'");
                            System.out.println();
                            break;

                        case ":last":
                            System.out.println("[Last Code]");
                            System.out.println(lisp.getLastCode());
                            break;

                        case ":cancel":
                            System.out.println("[Cancel]");
                            lisp.cancel();
                            prompt = PROMPT1;
                            break;

                        default:
                            System.out.println("Error!! Unknown Command! -> " + line);
                            break;
                    }
                    continue;
                }
            }

            if (lisp.input(line)) {
                String result = lisp.excute();
                if (result != null) {
                    System.out.println(result);
                } else {
                    System.out.println("Error!!");
                    System.out.println(lisp.getErrorMessage());
                }
                prompt = PROMPT1;
            } else {
                prompt = PROMPT2;
            }
        }

    }

    private int bracketcounter = 0;
    private StringBuilder lastcode;

    public Lisp() {
        lastcode = new StringBuilder();
    }

    public void cancel() {
        bracketcounter = 0;
    }

    public boolean input(String src) {
        int sp = 0;

        if (bracketcounter == 0) {
            lastcode.delete(0, lastcode.length());
        } else {
            sp = -1;
        }

        int len = src.length();
        for (int i = 0; i < len; i++) {
            sp++;
            char ch = src.charAt(i);
            lastcode.append(ch);
            switch (ch) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    if (sp == 0) {
                        lastcode.delete(lastcode.length() - 1, lastcode.length());
                    }
                    sp = -1;
                    break;

                case '(': // Open Bracket
                    bracketcounter++;
                    sp = -1;
                    break;

                case ')': // Close Bracket
                    if (sp == 0) {
                        lastcode.delete(lastcode.length() - 2, lastcode.length() - 1);
                    }
                    bracketcounter--;
                    break;

                case ';': // Comment
                    lastcode.delete(lastcode.length() - 1, lastcode.length());
                    i = len;
                    sp--;
                    break;

                default: // Symbol (Atom)
                    break;
            }
        }
        if (sp >= 0 && bracketcounter > 0) {
            lastcode.append(' ');
        }
        return (bracketcounter == 0);
    }

    public String excute() {
        return (null);
    }

    public String getErrorMessage() {
        return ("");
    }

    public String getLastCode() {
        return (lastcode.toString());
    }

    public String getIndent() {
        final String spacer = "                    ";
        if (bracketcounter == 0) {
            return ("");
        } else if (bracketcounter * 2 < spacer.length()) {
            return (spacer.substring(spacer.length() - bracketcounter * 2));
        } else {
            return (spacer);
        }
    }
}
