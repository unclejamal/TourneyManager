package com.pduda.tourney.infrastructure.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {

    public static Gson createGsonFromBuilder() {
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.setExclusionStrategies(new GsonExclusionStrategy(null));
        return gsonbuilder.serializeNulls().create();
    }
    
        public static Gson createGsonFromBuilder(ExclusionStrategy exs) {
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.setExclusionStrategies(exs);
        return gsonbuilder.serializeNulls().create();
    }
}
