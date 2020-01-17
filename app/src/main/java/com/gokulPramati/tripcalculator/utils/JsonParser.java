package com.gokulPramati.tripcalculator.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class JsonParser {
//    private static JsonParser instance = null;
//
//    private JsonParser() {
//
//    }
//
//    /**
//     * Singleton Instance
//     *
//     * @return
//     */
//    public static synchronized JsonParser getInstance() {
//        if (instance == null) {
//            instance = new JsonParser();
//        }
//        return instance;
//    }

    public static String ToJsonString(Object object) {
        Gson gson = new Gson();
        String json = null;
        if (object != null) {
            json = gson.toJson(object);
        }
        return json;
    }

    public static <T> T ToObject(String json, Class<T> modelClass) {
        Gson gson = new Gson();
        T object = null;
        object = gson.fromJson(json, (Type) modelClass);
        return object;
    }
}
