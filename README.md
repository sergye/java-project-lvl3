# Валидатор данных:
[![Actions Status](https://github.com/sergye/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/sergye/java-project-lvl3/actions)
[![Actions Status](https://github.com/sergye/java-project-lvl3/actions/workflows/ci-gradle.yml/badge.svg)](https://github.com/sergye/java-project-lvl3/actions)
<a href="https://codeclimate.com/github/sergye/java-project-lvl3/maintainability"><img src="https://api.codeclimate.com/v1/badges/76c5e031e0fe8649f941/maintainability" /></a>
<a href="https://codeclimate.com/github/sergye/java-project-lvl3/test_coverage"><img src="https://api.codeclimate.com/v1/badges/76c5e031e0fe8649f941/test_coverage" /></a>

<br>
Валидатор данных – библиотека, с помощью которой можно проверять корректность любых данных.

Пример использования:<br>
```
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// строки
StringSchema schema = v.string();
schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid("");; // false

schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

// числа
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// объект Map с поддержкой проверки структуры
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false
```