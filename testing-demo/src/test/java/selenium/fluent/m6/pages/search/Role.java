package selenium.fluent.m6.pages.search;

public enum Role {

    BUSINESS_PROFESSIONAL("Business Professional"),
    SOFTWARE_DEVELOPMENT("Software Development");
    // and other

    private String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
