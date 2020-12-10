package com.lti.launch.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * The type Json utils.
 *
 * @author Parking Cloud, bhkwon@iparking.co.kr
 * @since 2019.03.04
 */
public class GsonUtils {

    private final Gson gson;

    private GsonUtils(boolean isPrettyJson) {
        if (isPrettyJson) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(HashMap.class, new HashMapDeserializer())
                    .setPrettyPrinting().create();
        } else {
            gson = new GsonBuilder()
                    .registerTypeAdapter(HashMap.class, new HashMapDeserializer())
                    .create();
        }

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    private static GsonUtils getInstance(boolean isPrettyJson) {
        return new GsonUtils(isPrettyJson);
    }

    private static Gson getGson(boolean isPrettyJson) {
        return getInstance(isPrettyJson).gson;
    }

    /**
     * Is json valid boolean.
     *
     * @param jsonInString the json in string
     * @return the boolean
     */
    public static boolean isJSONValid(String jsonInString) {
        try {
            getGson(false).fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    /**
     * To json string.
     *
     * @param object the object
     * @return the string
     */
    public static String toJson(Object object) {
        try {
            return getGson(false).toJson(object);
        } catch (com.google.gson.JsonSyntaxException ex) {
            return null;
        }
    }

    /**
     * From json t.
     *
     * @param <T>     the type parameter
     * @param jsonStr the json str
     * @param cls     the cls
     * @return the t
     */
    public static <T> T fromJson(String jsonStr, Class<T> cls) {
        try {
            return getGson(false).fromJson(jsonStr, cls);
        } catch (com.google.gson.JsonSyntaxException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * From json json element.
     *
     * @param json the json
     * @return the json element
     * @throws Exception the exception
     */
    public static JsonElement fromJson(String json) {
        try {
            return getGson(false).toJsonTree(json);
        } catch (com.google.gson.JsonSyntaxException ex) {
            return null;
        }
    }

    /**
     * From json t.
     *
     * @param <T>     the type parameter
     * @param jsonStr the json str
     * @param type    the type
     * @return the t
     */
    public static <T> T fromJson(String jsonStr, Type type) {
        try {
            return getGson(false).fromJson(jsonStr, type);
        } catch (com.google.gson.JsonSyntaxException ex) {
            return null;
        }
    }

    /**
     * To pretty json string.
     *
     * @param json the json
     * @return the string
     */
    public static String toPrettyJson(String json) {
        try {
            return getGson(true).toJson(getGson(false).fromJson(json, Object.class));
        } catch (com.google.gson.JsonSyntaxException ex) {
            return null;
        }
    }
}

