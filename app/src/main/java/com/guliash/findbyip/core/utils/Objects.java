package com.guliash.findbyip.core.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Objects {

    @NonNull
    public static <T> T requireInstance(@NonNull Object object, @NonNull Class<T> clazz) {
        return clazz.cast(object);
    }

    @NonNull
    public static <T> T requireNonNull(@Nullable T object) {
        if (object == null) {
            throw new NullPointerException("Required non null instance");
        } else {
            return object;
        }
    }

}
