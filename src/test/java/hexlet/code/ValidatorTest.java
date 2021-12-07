package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    void testStringRequired() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("Vasya"));
    }

    @Test
    void testStringMinLength() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        final int length = 5;
        schema.minLength(length);

        boolean expected = schema.isValid("12345");
        assertTrue(expected);
        expected = schema.isValid("1234");
        assertFalse(expected);
    }

    @Test
    void testStringContains() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
    }

    @Test
    void testNumberRequired() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        final int value = 10;

        assertFalse(schema.required().isValid(null));
        assertTrue(schema.required().isValid(value));
        assertFalse(schema.required().isValid("5"));
    }

    @Test
    void testNumberPositive() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        final int firstValue = -10;
        final int secondValue = 10;

        assertFalse(schema.positive().isValid(0));
        assertFalse(schema.positive().isValid(firstValue));
        assertTrue(schema.positive().isValid(secondValue));
    }

    @Test
    void testNumberRange() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        final int start = -3;
        final int end = 4;
        final int firstValue = -5;
        final int secondValue = 7;

        assertTrue(schema.range(start, end).isValid(start));
        assertTrue(schema.range(start, end).isValid(end));
        assertTrue(schema.range(start, end).isValid(0));
        assertFalse(schema.range(start, end).isValid(firstValue));
        assertFalse(schema.range(start, end).isValid(secondValue));
    }

    @Test
    void testRequiredMap() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();

        assertFalse(schema.required().isValid(null));
        assertTrue(schema.required().isValid(new HashMap<String, String>()));
    }

    @Test
    void testSizeofMap() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertTrue(schema.sizeof(1).isValid(map));
        assertFalse(schema.sizeof(2).isValid(map));
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
