package com.pduda.tourney.domain.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyUtils {

    public static <T> Set<T> asSet(T... testCategories) {
        Set<T> toReturn = new HashSet<T>();
        for (T testCategory : testCategories) {
            toReturn.add(testCategory);
        }

        return toReturn;
    }

    public static <T> List<T> asList(T... testCategories) {
        List<T> toReturn = new ArrayList<T>();
        for (T testCategory : testCategories) {
            toReturn.add(testCategory);
        }

        return toReturn;
    }
}
