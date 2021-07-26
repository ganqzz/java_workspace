package demo.exception;

public class CustomExceptions {

    public static final void main(String[] args) {
        String testString = null;
        try {
            testExceptions(testString);
        } catch (MyPersonalException mpe) {
            System.out.println("Ending with exception");
            if (mpe.getCause() != null) {
                mpe.getCause().printStackTrace();
            }
        }
    }

    public void printHello() {
        System.out.println();
    }

    private static void testExceptions(String value) throws MyPersonalException {

        try {
            value.length();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            throw new MyPersonalException(npe);
        } finally {
            System.out.println("Demo is complete");
        }
    }

    private static class MyPersonalException extends Exception {

        private static final long serialVersionUID = 1L;

        public MyPersonalException() {}

        public MyPersonalException(String arg0) {
            super(arg0);
        }

        public MyPersonalException(Throwable arg0) {
            super(arg0);
        }

        public MyPersonalException(String arg0, Throwable arg1) {
            super(arg0, arg1);
        }

        @Override
        public String getMessage() {
            return "MyPersonalException\n" + super.getMessage();
        }
    }
}
