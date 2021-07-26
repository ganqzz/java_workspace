package basic;

class I2 {
    String getDescription() {
        return this.getClass().toString(); // thisの参照するオブジェクトに注意する。
    }
}

class J2 extends I2 {
    String getDescription() {
        return this.getClass().toString() + ":" + super.getClass() + ":" + super.getDescription();
    }
}

class K2 extends J2 {
    String getDescription() {
        return this.getClass().toString() + ":" + super.getClass().toString() + ":"
               + super.getDescription();
    }
}

class ThisSuper {
    public static void main(String[] args) {
        System.out.println(new I2().getDescription());
        System.out.println(new J2().getDescription());
        System.out.println(new K2().getDescription());
    }
}
