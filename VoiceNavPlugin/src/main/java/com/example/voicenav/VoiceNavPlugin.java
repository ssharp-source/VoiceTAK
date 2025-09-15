/*
 * ATAK Plugin – Copyright (c) 2025 Steven Sharp
 *
 * Licensed under the Non-Commercial AGPLv3.
 * You may use, modify, and share this code freely for non-commercial purposes.
 *
 * Commercial use requires a separate paid license.
 * Contact: stevensharp6@gmail.com
 */

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

