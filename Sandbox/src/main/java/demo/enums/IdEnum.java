package demo.enums;

public enum IdEnum {

    HOGE(1), FUGA(2);

    private Object id;

    IdEnum(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public static void main(String[] args) {
        System.out.println(IdEnum.HOGE);
        System.out.println(IdEnum.FUGA);
    }
}
