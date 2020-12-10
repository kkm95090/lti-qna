package com.lti.launch.util;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * json은 형에 대한 정의가 따로 없기에 실수가 double형으로 변환 되는것을 방지
 *
 * @author Parking Cloud, bhkwon@iparking.co.kr
 * @since 2019.09.02
 */
public class HashMapDeserializer implements JsonDeserializer<HashMap<String, Object>> {

    @Override
    public HashMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        Map.Entry<String, JsonElement> entry = obj.entrySet().iterator().next();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(entry.getKey(), ParseObjectFromElement.SINGLETON.apply(entry.getValue()));
        return resultMap;
    }

    public enum ParseObjectFromElement implements Function<JsonElement, Object> {

        SINGLETON;
        @Override
        public Object apply(JsonElement input) {
            Object value = null;
            if (input == null || input.isJsonNull()) {
                value = null;
            } else if (input.isJsonPrimitive()) {
                JsonPrimitive primitive = input.getAsJsonPrimitive();
                if (primitive.isNumber()) {
                    value = primitive.getAsInt(); // Number 값은 무조건 integer로 처리
                } else if (primitive.isBoolean()) {
                    value = primitive.getAsBoolean();
                } else {
                    value = primitive.getAsString();
                }
            } else if (input.isJsonArray()) {
                value = Lists.newArrayList(Iterables.transform(input.getAsJsonArray(), this));
            } else if (input.isJsonObject()) {
                value = Maps.newLinkedHashMap(
                        Maps.transformValues(JsonObjectAsMap.INSTANCE.apply(input.getAsJsonObject()), this));
            }
            return value;
        }
    }

    public enum JsonObjectAsMap implements Function<JsonObject, Map<String, JsonElement>> {

        INSTANCE;

        private final Field members;

        JsonObjectAsMap() {
            try {
                members = JsonObject.class.getDeclaredField("members");
                members.setAccessible(true);
            } catch (NoSuchFieldException e) {
                throw new UnsupportedOperationException("cannot access gson internals", e);
            }
        }

        @Override
        public Map<String, JsonElement> apply(JsonObject in) {
            try {
                return (Map<String, JsonElement>) members.get(in);
            } catch (IllegalArgumentException e) {
                throw new UnsupportedOperationException("cannot access gson internals", e);
            } catch (IllegalAccessException e) {
                throw new UnsupportedOperationException("cannot access gson internals", e);
            }
        }
    }
}

