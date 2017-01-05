package com.example.osama.giotclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize LED Toggle Button
        ToggleButton toggleGPIO = (ToggleButton) findViewById(R.id.toggleButton);

        //Create Toggle Listener
        toggleGPIO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean toggled) {
                //Get IP Address, Port No and GPIO PIN Name from user on Screen
                String ipaddress = ((EditText)findViewById(R.id.ipddresstext)).getText().toString();
                int portno = Integer.parseInt(((EditText)findViewById(R.id.portnotext)).getText().toString());
                String gpioname = ((EditText)findViewById(R.id.gpiotext)).getText().toString();


                //Send the LED Toggle Command
                Client myClient = new Client(ipaddress,portno,gpioname,toggled);
                myClient.start();


            }
        });
    }
}
