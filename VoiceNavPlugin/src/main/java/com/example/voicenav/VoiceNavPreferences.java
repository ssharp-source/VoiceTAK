package com.example.voicenav;

import android.content.Context;
import android.content.SharedPreferences;

public class VoiceNavPreferences {
    private static final String PREFS = "VoiceNavPrefs";
    private static final String KEY_ENABLED = "enabled";

    public static boolean isEnabled(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_ENABLED, false);
    }

    public static void setEnabled(Context ctx, boolean enabled) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_ENABLED, enabled).apply();
    }
}
