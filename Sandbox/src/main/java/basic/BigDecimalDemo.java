package basic;

import java.math.BigDecimal;

public class BigDecimalDemo {
    public static void main(String[] args) {

        BigDecimal bd = new BigDecimal(10.3);
        System.out.println(bd);

        Double d = 10.3;
        String ds = d.toString();
        BigDecimal bd2 = new BigDecimal(ds);
        System.out.println(bd2);

    }

}
