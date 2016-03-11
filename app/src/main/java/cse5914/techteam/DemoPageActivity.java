package cse5914.techteam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

// This page contains the entrances to various demo activities
public class DemoPageActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    private EditText speakOutput;
    Button speakToText, callCamera;
    Button switchColor, map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_page);
        //invoke google voice
        speakOutput = (EditText) findViewById(R.id.speakOutput);
        speakToText = (Button) findViewById(R.id.speak);
        speakToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    startActivityForResult(intent, REQUEST_CODE);
            }

        });
        //invoke camera
        callCamera = (Button) findViewById(R.id.callCamera);
        callCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
        //onclick to change screen background
        switchColor = (Button) findViewById(R.id.switchColor);
        switchColor.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               DemoPageActivity.this.getWindow().getDecorView().setBackgroundColor(Color.rgb(255, 102, 153));
           }
        });
        //invoke map
        map = (Button) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DemoPageActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If Voice recognition is successful then it returns RESULT_OK
        if(resultCode == RESULT_OK) {
            //auto fill the highest confidence result to the text box
            ArrayList<String> textMatchList = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            speakOutput.setText(textMatchList.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
