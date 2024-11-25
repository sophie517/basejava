package ru.javawebinar.basejava.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateAdapter implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDate.parse(json.getAsString(), FORMATTER);
    }

    @Override
    public JsonElement serialize(LocalDate ld, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(ld.format(FORMATTER));
    }
}
