package com.pduda.tourney.domain.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyUtils {

    public static <T> Set<T> asSet(T... elements) {
        Set<T> toReturn = new HashSet<T>();
        toReturn.addAll(Arrays.asList(elements));
        return toReturn;
    }

    public static <T> List<T> asList(T... elements) {
        List<T> toReturn = new ArrayList<T>();
        toReturn.addAll(Arrays.asList(elements));
        return toReturn;
    }

    public static <T> T any(Collection<T> elements) {
        for (T element : elements) {
            return element;
        }

        throw new RuntimeException();
    }
}
