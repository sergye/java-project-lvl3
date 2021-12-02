package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    void testStringRequired() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        assertThat(schema.required().isValid("")).isEqualTo(true);
        assertThat(schema.required().isValid(null)).isEqualTo(false);
    }

    @Test
    void testStringMinLength() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        final int length = 5;

        boolean expected = schema.minLength(length).isValid("12345");
        assertThat(expected).isEqualTo(true);
        expected = schema.minLength(length).isValid("1234");
        assertThat(expected).isEqualTo(false);
    }

    @Test
    void testStringContains() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
    }

    @Test
    void testNumberRequired() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        final int value = 10;

        assertThat(schema.required().isValid(null)).isEqualTo(false);
        assertThat(schema.required().isValid(value)).isEqualTo(true);
        assertThat(schema.required().isValid("5")).isEqualTo(false);
    }

    @Test
    void testNumberPositive() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        final int firstValue = -10;
        final int secondValue = 10;

        assertThat(schema.positive().isValid(0)).isEqualTo(false);
        assertThat(schema.positive().isValid(firstValue)).isEqualTo(false);
        assertThat(schema.positive().isValid(secondValue)).isEqualTo(true);
    }

    @Test
    void testNumberRange() {
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
    void testSizeofMap() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        assertThat(schema.sizeof(1).isValid(map)).isEqualTo(true);
        assertThat(schema.sizeof(2).isValid(map)).isEqualTo(false);
    }

    @Test
    void testShapeMap() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        final int age = 50;
        Map<String, Object> vova = Map.of("name", "Vova", "age", age);
        assertTrue(schema.isValid(vova));

        final int negativeNumber = -20;
        Map<String, Object> dima = Map.of("name", "Dima", "age", negativeNumber);
        assertFalse(schema.isValid(dima));
    }
}
