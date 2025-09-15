package com.example.voicenav;

import com.atak.plugins.impl.PluginLifecycle;
import android.content.Context;
import android.content.Intent;

public class VoiceNavPlugin extends PluginLifecycle {
    public VoiceNavPlugin(Context context, String apkPath) {
        super(context, apkPath);
    }

    @Override
    public void onCreate(Context context) {
        super.onCreate(context);

        // Start service only if enabled in prefs
        if (VoiceNavPreferences.isEnabled(context)) {
            context.startService(new Intent(context, VoiceNavService.class));
        }
    }
}
