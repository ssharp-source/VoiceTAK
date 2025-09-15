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

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.atakmap.android.navigation.NavManager;

import java.util.ArrayList;

public class VoiceNavService extends Service {
    private SpeechRecognizer recognizer;

    @Override
    public void onCreate() {
        super.onCreate();

        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        recognizer.setRecognitionListener(new RecognitionListener() {
            @Override public void onReadyForSpeech(Bundle params) {}
            @Override public void onRmsChanged(float rmsdB) {}
            @Override public void onBufferReceived(byte[] buffer) {}
            @Override public void onPartialResults(Bundle partialResults) {}
            @Override public void onEvent(int eventType, Bundle params) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                Log.e("VoiceNav", "Speech error: " + error);
                startListening();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    for (String cmd : matches) {
                        Log.i("VoiceNav", "Heard: " + cmd);
                        if (cmd.equalsIgnoreCase("next nav point")) {
                            advanceNavPoint();
                        }
                    }
                }
                startListening();
            }
        });

        startListening();
    }

    private void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizer.startListening(intent);
    }

    private void advanceNavPoint() {
        NavManager nav = NavManager.getInstance();
        if (nav != null) {
            nav.nextWaypoint();
            Log.i("VoiceNav", "Advanced to next nav point");
        } else {
            Log.w("VoiceNav", "NavManager not available");
        }
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (recognizer != null) recognizer.destroy();
    }
}

