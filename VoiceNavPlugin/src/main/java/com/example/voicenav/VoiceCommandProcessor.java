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

import android.content.Context;
import android.widget.Toast;

public class VoiceCommandProcessor {

    public static void handleCommand(Context ctx, String command) {
        command = command.toLowerCase();

        if (command.contains("next")) {
			AtakControl.advanceNavPoint();
		} else if (command.contains("previous")) {
			AtakControl.previousNavPoint();
		} else if (command.contains("drop marker")) {
		// get current device location and call dropMarker
		Location loc = ... // use ATAK GPS or Android fused location
		AtakControl.dropMarker(ctx, loc.getLatitude(), loc.getLongitude(), "Voice Drop");
		} else if (command.contains("zoom in")) {
			AtakControl.zoomIn();
		} else if (command.contains("zoom out")) {
			AtakControl.zoomOut();
		}
    }
}

