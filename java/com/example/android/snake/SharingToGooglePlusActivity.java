package com.example.android.snake;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.plus.PlusShare;

/**
 * Created by chris on 3/14/2017.
 */

public class SharingToGooglePlusActivity extends Activity {
    Button shareBtnGoogle;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_to_google_plus);

        shareBtnGoogle = (Button)findViewById(R.id.share_button);

        shareBtnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new PlusShare.Builder(getApplicationContext())
                        .setType("text/plain")
                        .setText("This was my Score on the Snake App: "+SnakeView.parseScroe)
                        .setContentUrl(Uri.parse("https://plus.google.com"))
                        .getIntent();

                startActivityForResult(shareIntent,0);
            }
        });


    }
}
