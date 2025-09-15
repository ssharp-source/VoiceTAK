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
