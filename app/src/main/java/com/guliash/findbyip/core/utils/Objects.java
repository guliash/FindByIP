package com.guliash.findbyip.core.utils;

public class Objects {

    public static <T> T requireInstance(Object object, Class<T> clazz) {
        return clazz.cast(object);
    }

}
