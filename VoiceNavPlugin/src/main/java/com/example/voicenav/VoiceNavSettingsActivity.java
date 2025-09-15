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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.CompoundButton;

public class VoiceNavSettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Switch toggle = new Switch(this);
        toggle.setText("Enable Voice Navigation");
        toggle.setChecked(VoiceNavPreferences.isEnabled(this));

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean enabled) {
                VoiceNavPreferences.setEnabled(VoiceNavSettingsActivity.this, enabled);

                if (enabled) {
                    startService(new Intent(VoiceNavSettingsActivity.this, VoiceNavService.class));
                } else {
                    stopService(new Intent(VoiceNavSettingsActivity.this, VoiceNavService.class));
                }
            }
        });

        setContentView(toggle);
    }
}

