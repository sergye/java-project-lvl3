package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        this.addToValidationList(x -> x instanceof Map);
        return this;
    }

    public MapSchema sizeof(int numberOfEntries) {
        this.addToValidationList(x -> ((Map) x).size() == numberOfEntries);
        return this;
    }
}
