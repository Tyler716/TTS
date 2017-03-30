package pundle.tts;

import android.content.DialogInterface;
import android.icu.text.IDNA;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;

    TextView text;
    String utteranceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.editText);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("No Language");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    if(textToSpeech.isLanguageAvailable(Locale.US) != TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                        alertDialog.show();
                    }
                    else {
                        textToSpeech.setLanguage(Locale.US);
                        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                            @Override
                            public void onStart(String utteranceId) {
                                utteranceID = utteranceId;
                            }

                            @Override
                            public void onDone(String utteranceId) {

                            }

                            @Override
                            public void onError(String utteranceId) {

                            }
                        });
                    }
                }
            }
        });
    }

    public void OnClickPronounce(View view) {
        textToSpeech.speak(text.getText(), TextToSpeech.QUEUE_FLUSH, null, utteranceID);
    }
}
