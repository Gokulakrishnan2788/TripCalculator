package com.gokulPramati.tripcalculator.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class JsonParser {

    /**
     *
     * @param object
     * @return
     */
    public static String ToJsonString(Object object) {
        Gson gson = new Gson();
        String json = null;
        if (object != null) {
            json = gson.toJson(object);
        }
        return json;
    }

    /**
     *
     * @param json
     * @param modelClass
     * @param <T>
     * @return
     */
    public static <T> T ToObject(String json, Class<T> modelClass) {
        Gson gson = new Gson();
        T object = null;
        object = gson.fromJson(json, (Type) modelClass);
        return object;
    }
}
