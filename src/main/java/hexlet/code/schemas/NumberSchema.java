package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        this.addToValidationList(x -> x instanceof Number);
        return this;
    }

    public NumberSchema positive() {
        this.addToValidationList(x -> x == null || (x instanceof Integer && (Integer) x > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.addToValidationList(x -> x == null || (int) x >= min && (int) x <= max);
        return this;
    }
}
