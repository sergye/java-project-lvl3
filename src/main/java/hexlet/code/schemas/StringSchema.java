package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        this.addToValidationList(x -> x instanceof String && !((String) x).isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.addToValidationList(x -> x == null || (((String) x).length() >= minLength));
        return this;
    }

    public StringSchema contains(String str) {
        this.addToValidationList(x -> x == null || (((String) x).contains(str)));
        return this;
    }
}
