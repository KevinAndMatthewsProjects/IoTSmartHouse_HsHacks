package com.example.hshack.smarthouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.endpoints.pubsub.Publish;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.io.Console;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-2dd510a2-1209-11e7-8403-0619f8945a4f");
        pnConfiguration.setPublishKey("pub-c-180ed609-5056-42f7-8aba-ad47e2165711");
        setContentView(R.layout.activity_main);

        final PubNub pubnub = new PubNub(pnConfiguration);


        //CheckBox box = findViewById(R.id.)
        //toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          //  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //    Publish p = pubnub.publish();
              //  p.channel("1");
                //p.message("a" + (isChecked ? "1" : "0"));
               // pubnub.publish().message("Start").channel("1").shouldStore(true).usePOST(true);

         Switch s = (Switch)(findViewById(R.id.switch1));
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pubnub.publish()
                        .message(Arrays.asList(isChecked ? "on1" : "off1"))
                        .channel("test")
                        .shouldStore(true)
                        .usePOST(true)
                        .async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                if (status.isError()) {
                                    // something bad happened.
                                    System.out.println("error happened while publishing: " + status.toString());
                                } else {
                                    System.out.println("publish worked! timetoken: " + result.getTimetoken());
                                }
                            }
                        });
            }
        });

        Switch s2 = (Switch)(findViewById(R.id.switch2));
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pubnub.publish()
                        .message(Arrays.asList(isChecked ? "on2" : "off2"))
                        .channel("test")
                        .shouldStore(true)
                        .usePOST(true)
                        .async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                if (status.isError()) {
                                    // something bad happened.
                                    System.out.println("error happened while publishing: " + status.toString());
                                } else {
                                    System.out.println("publish worked! timetoken: " + result.getTimetoken());
                                }
                            }
                        });
            }
        });


        Switch s3 = (Switch)(findViewById(R.id.switch3));
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pubnub.publish()
                        .message(Arrays.asList(isChecked ? "onA" : "offA"))
                        .channel("test")
                        .shouldStore(true)
                        .usePOST(true)
                        .async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                if (status.isError()) {
                                    // something bad happened.
                                    System.out.println("error happened while publishing: " + status.toString());
                                } else {
                                    System.out.println("publish worked! timetoken: " + result.getTimetoken());
                                }
                            }
                        });
            }
        });

        Switch s4 = (Switch)(findViewById(R.id.switch4));
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pubnub.publish()
                        .message(Arrays.asList(isChecked ? "onS" : "offS"))
                        .channel("test")
                        .shouldStore(true)
                        .usePOST(true)
                        .async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                if (status.isError()) {
                                    // something bad happened.
                                    System.out.println("error happened while publishing: " + status.toString());
                                } else {
                                    System.out.println("publish worked! timetoken: " + result.getTimetoken());
                                }
                            }
                        });
            }
        });



    }


}
