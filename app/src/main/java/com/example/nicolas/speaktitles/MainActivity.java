package com.example.nicolas.speaktitles;

import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private long startTime;
    private ListView listView;
    private Button button;
    private TextView textView;
    private TextToSpeech tts;
    private ArrayList<String> arrayList;
    String pattern = "HH:mm:ss.SSS";
    SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern/*, new Locale("fr", "FR")*/);
    String date;
    Date dat = new Date();


    private class Timer extends AsyncTask<Integer, Integer, Void> {
        @Override

        protected Void doInBackground(Integer... params) {
            //while (true) {
                try {
                    Thread.sleep(0,1);

                     //

                    publishProgress(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            //}

            return null;
        }
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            //dat.setTime(System.currentTimeMillis()-startTime);
           // date = simpleDateFormat.format(dat);
            //date = ;
            //textView.setText(date);
            speak();
            // Mise à jour de la ProgressBar
            //refreshNb();r

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button5);
        listView=(ListView)findViewById(R.id.listview);
        textView=(TextView)findViewById(R.id.textView3);



        String hey="salut";
        /*ArrayList<String>*/ arrayList = new ArrayList<>();
        arrayList.add("Peu importe ce qu'il faut.");
        arrayList.add("'Avengers: Endgame' est une méditation sur le temps -");
        arrayList.add("et il nous laisse un message centenaire approprié: ");
        arrayList.add(" que nous devrions chérir les moments que nous avons sur cette terre avec nos proches. ");
        arrayList.add(" Comme le dit Tony Stark, citant son père à son père,");
        arrayList.add(" \"Aucune somme d'argent jamais acheté une seconde de temps.\" ");
        arrayList.add(" À la suite de la prise des doigts de Thanos, nous voyons les Avengers originaux revisiter ");

        ArrayAdapter simpleAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(simpleAdapter);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.FRENCH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }else {
                        button.setEnabled(true);
                    }
                }else {
                    Log.e("TTS", "Initialization failed");
                }

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = System.currentTimeMillis();
                Timer timer= new Timer();
                timer.execute();
                //speak();

            }
        });
arrayList.
    }
    private  void speak(){
        for(int i=0; i<arrayList.size();i++){
            tts.speak(arrayList.get(i), TextToSpeech.QUEUE_FLUSH, null);
            while(tts.isSpeaking()){
                i=i;
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
