package demo.exception;

public class ExceptionChainDemo {
    public static void main(String[] args) {
        try {
            try {
                throw new Exception("One");
            } catch (Exception e) {
                throw new Exception(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
