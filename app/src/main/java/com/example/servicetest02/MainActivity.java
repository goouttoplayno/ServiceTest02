package com.example.servicetest02;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_bind_service, button_unbind_service;
    private static final String TAG = "MainActivity";
    private IPerson person;
    boolean mBound = false;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            person = IPerson.Stub.asInterface(service);
            Log.d("person", "person对象的内存地址是：" + person);
            try {
                person.setName("smyh");
                person.setSex("nan");
                person.setAge(26);
                String p = person.getPerson();
                Log.d("person", "person的信息是： " + p);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_bind_service = (Button) findViewById(R.id.button_bind_service);
        button_bind_service.setOnClickListener(this);
        button_unbind_service = (Button) findViewById(R.id.button_unbind_service);
        button_unbind_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.button_unbind_service:
                if (mBound){
                    unbindService(connection);
                    mBound = false;
                }
                break;
            default:
                break;
        }
    }
}
