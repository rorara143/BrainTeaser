package com.vitin.mycomp.brainteaser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by MyComp on 12/13/2016.
 */

public class FrontPage  extends Activity {

    private Button play_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        //front page instructions for game
        ImageView myimageview = (ImageView) findViewById(R.id.imageView2);
        myimageview.setImageResource(R.drawable.welcome);

        play_button = (Button) findViewById(R.id.play_button);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.play_button) {
                    Intent i = new Intent(FrontPage.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });


    }
}