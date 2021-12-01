package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Validator {

    private List<Predicate<? super Object>> validationList = new ArrayList<>();

    public final void addToValidationList(Predicate<? super Object> validation) {
        this.validationList.add(validation);
    }

    public final  <T> boolean isValid(T t) {
        return this.validationList.stream()
                .allMatch(x -> x.test(t));
    }

    public final StringSchema string() {
        return new StringSchema();
    }

}
