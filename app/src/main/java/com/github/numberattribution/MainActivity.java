package com.github.numberattribution;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView textView;
    private String result;
    private String number;

    private Header list = new Header();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            NewsJSON nj = new NewsJSON(result);

            list = nj.getHeaders();
//            textView.setText(list.getProvince() + " " + list.getCity() + " " + list.getCompany());
            if(!list.getCity().equals("")) {
                textView.setText("查询号码" + "  "+editText.getText() + "\n"+"\n"
                        + "归属地为"+"  "+list.getProvince() + " " + list.getCity() + " " + list.getCompany()
                       +"\n"+"\n"  + "卡型" + "  " +list.getCardtype());
            }else{
                textView.setText("查询号码" + "  "+editText.getText() + "\n"+"\n"
                        + "归属地为"+"  "+list.getProvince() + " " + list.getProvince() + " " + list.getCompany()
                       +"\n"+"\n" + "号码卡型" + "  " +list.getCardtype());
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        textView.setText("");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText("");
                number = editText.getText().toString();
                if (number != null && !number.equals("")) {
                    RequestTheard rt = new RequestTheard();
                    rt.start();
                }
                else {
                    Toast.makeText(MainActivity.this,"输入号码不能为空!",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    class RequestTheard extends Thread{
        @Override
        public void run() {

            NetRequest hr = new NetRequest();
            result = hr.getRequestResult(number);
            handler.sendEmptyMessage(0);

        }
    }
}
