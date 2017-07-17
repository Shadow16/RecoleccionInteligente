package test.recoleccioninteligente;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img = (ImageView) findViewById(R.id.imageView3);
        img.animate().translationY(-260).setDuration(2200);


        new Handler().postDelayed(new Runnable() {




            @Override
            public void run() {
                Intent main = new Intent(Splash.this, Login.class);
                startActivity(main);
                finish();
            }
        }, 2500);

    }
}
