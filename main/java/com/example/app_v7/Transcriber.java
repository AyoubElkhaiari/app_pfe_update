package com.example.app_v7;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;

public class Transcriber {

    public static Intent speak(View v) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM) ;
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 5) ;
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start Speaking ...") ;
        return intent ;
    }
}
