package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private static final int LENGTH = 5;

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
        boolean expected = schema.minLength(LENGTH).isValid("12345");
        assertThat(expected).isEqualTo(true);
        expected = schema.minLength(LENGTH).isValid("1234");
        assertThat(expected).isEqualTo(false);
    }

    @Test
    void testContains() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
    }

}
