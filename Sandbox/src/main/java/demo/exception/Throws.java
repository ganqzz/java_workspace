package demo.exception;

class Throws {
    public static void main(String[] args) {
        try {
            System.out.println("a(): before");
            a();
            System.out.println("a(): after");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("main(): catch{} : " + e.getMessage());
        } finally {
            System.out.println("main(): finally{}");
        }
    }

    static void a() {
        try {
            System.out.println("b(): before");
            b();
            System.out.println("b(): after");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("a(): catch{} : " + e);
        } finally {
            System.out.println("a(): finally{}");
        }
    }

    static void b() {
        try {
            System.out.println("c(): before");
            c();
            System.out.println("c(): after");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("b(): catch{} Array : " + e);
        } catch (Exception e) {
            System.out.println("b(): catch{} : " + e);
        } finally {
            System.out.println("b(): finally{}");
        }
    }

    static void c() throws Exception {
        System.out.println("d(): before");
        d();
        System.out.println("d(): after");
    }

    static void d() {
        try {
            int[] ia = new int[10];
            System.out.println("before access to ia[20]");
            System.out.println(ia[20]);
            System.out.println("after access to ia[20]");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("d(): catch{} : " + e);
            throw e;
        } finally {
            System.out.println("d(): finally{}");
        }
    }
}
