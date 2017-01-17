package demo.android.boundservicedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    static TextView tv_sw;
    Button btn_start;
    Button btn_pause;
    Button btn_reset;
    Button btn_exit;
    StopwatchService sws = new StopwatchService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        tv_sw = (TextView) findViewById(R.id.tv_time);
        btn_exit = (Button) findViewById(R.id.btn_exit);




        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"start",Toast.LENGTH_SHORT).show();
                sws.start();

            }
        });


        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sws.pause();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sws.reset();

            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);
            }
        });


    }


}
