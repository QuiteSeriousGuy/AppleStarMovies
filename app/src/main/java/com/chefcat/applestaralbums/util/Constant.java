package com.chefcat.applestaralbums.util;

import android.content.Context;

public class Constant {

    public static final String DB_NAME = "applestaralbums.db";

    // Shared Preference.
    public static final String PREF_NAME = "com.chefcat.applestaralbums";
    public static final int PREF_MODE = Context.MODE_PRIVATE;

    // Default searches
    public static final String DEFAULT_SEARCH_TERM = "star";
    public static final String DEFAULT_SEARCH_COUNTRY = "au";
    public static final String DEFAULT_SEARCH_MEDIA = "movie";
    public static final String DEFAULT_SEARCH_ATTRIBUTE = "all";
    public static final String DEFAULT_SEARCH_LIMIT = "200";

    // Shared Preferences
    public static final String SHARED_PREFERENCES_NAME = "com.chefcar.applestaralbum";
    public static final String SHARED_PREF_LAST_IN = "last_in";
    public static final String SHARED_PREF_LAST_SCREEN = "last_screen";
    public static final String SHARED_PREF_LAST_INFO = "last_info";

    // Screens
    public static final int MAIN_SCREEN = 0;
    public static final int VIEWED_SCREEN = 1;


    private Constant() {
        // This utility class is not publicly instantiable
    }
}
