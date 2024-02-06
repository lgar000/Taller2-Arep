package edu.escuelaing.arep.ASE.app;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    public  static  ConcurrentHashMap<String, String> movieCache = new ConcurrentHashMap<>();

    public static boolean movieInCache(String key){
        return movieCache.containsKey(key);
    }

    public static void saveInCache(String key, String value){
        movieCache.put(key,value);
    }

    public static String getMovieInCache(String key){
        return movieCache.get(key);
    }

}
