package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        this.addToValidationList(x -> x instanceof Map);
        return this;
    }

    public MapSchema sizeof(int numberOfEntries) {
        this.addToValidationList(x -> x == null || ((Map) x).size() == numberOfEntries);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.addToValidationList(x -> isValueValid((Map<String, Object>) x, schemas));
        return this;
    }

    private boolean isValueValid(Map<String, Object> map,  Map<String, BaseSchema> schemas) {
        for (Map.Entry<String, Object> key : map.entrySet()) {
            if (!schemas.get(key
                    .getKey())
                    .isValid(key.getValue())) {
                return false;
            }
        }
        return true;
    }
}
