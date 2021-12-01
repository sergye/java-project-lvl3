package hexlet.code;

public final class StringSchema extends Validator {

    public StringSchema required() {
        this.addToValidationList(x -> x instanceof String && x != null);
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.addToValidationList(x -> x == null || (x instanceof String && ((String) x).length() >= minLength));
        return this;
    }

    public StringSchema contains(String str) {
        this.addToValidationList(x -> x == null || (x instanceof String && ((String) x).contains(str)));
        return this;
    }
}
