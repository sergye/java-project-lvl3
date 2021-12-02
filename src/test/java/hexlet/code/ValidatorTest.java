package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @Test
    void testRequired() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        assertThat(schema.required().isValid("")).isEqualTo(true);
        assertThat(schema.required().isValid(null)).isEqualTo(false);
    }

    @Test
    void testMinLength() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        final int length = 5;

        boolean expected = schema.minLength(length).isValid("12345");
        assertThat(expected).isEqualTo(true);
        expected = schema.minLength(length).isValid("1234");
        assertThat(expected).isEqualTo(false);
    }

    @Test
    void testContains() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
    }

    @Test
    void testRequiredNumber() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        final int value = 10;

        assertThat(schema.required().isValid(null)).isEqualTo(false);
        assertThat(schema.required().isValid(value)).isEqualTo(true);
        assertThat(schema.required().isValid("5")).isEqualTo(false);
    }

    @Test
    void testPositive() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        final int firstValue = -10;
        final int secondValue = 10;

        assertThat(schema.positive().isValid(0)).isEqualTo(false);
        assertThat(schema.positive().isValid(firstValue)).isEqualTo(false);
        assertThat(schema.positive().isValid(secondValue)).isEqualTo(true);
    }

    @Test
    void testRange() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        final int start = -3;
        final int end = 4;
        final int firstValue = -5;
        final int secondValue = 7;

        assertThat(schema.range(start, end).isValid(start)).isEqualTo(true);
        assertThat(schema.range(start, end).isValid(end)).isEqualTo(true);
        assertThat(schema.range(start, end).isValid(0)).isEqualTo(true);
        assertThat(schema.range(start, end).isValid(firstValue)).isEqualTo(false);
        assertThat(schema.range(start, end).isValid(secondValue)).isEqualTo(false);
    }

    @Test
    void testRequiredMap() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        assertThat(schema.required().isValid(null)).isEqualTo(false);
        assertThat(schema.required().isValid(new HashMap<String, String>())).isEqualTo(true);
    }

    @Test
    void testSizeof() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, String> data = new HashMap<>();
        data.put("key", "value");
        assertThat(schema.sizeof(1).isValid(data)).isEqualTo(true);
        assertThat(schema.sizeof(2).isValid(data)).isEqualTo(false);
    }
}
