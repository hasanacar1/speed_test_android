package com.example.speedtestandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.os.NetworkOnMainThreadException;
import fr.bmartel.speedtest.model.SpeedTestError;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.inter.ISpeedTestListener;

import android.util.Log;
import android.widget.ExpandableListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.HttpURLConnection;

import java.net.SocketTimeoutException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

import java.util.Arrays;
import java.io.BufferedReader;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    TextView tv2;
    String Download_bit_end;
    String Download_bit_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        tv = (TextView) findViewById(R.id.download_text);
        tv2 = (TextView) findViewById(R.id.download_end);
        Button b = (Button) findViewById(R.id.download_button);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        new SpeedTestTask().execute();
        //tv.setText("Download speed: " + Download_bit);
    }

    public class SpeedTestTask extends AsyncTask<Void, String, String> {

        @Override
        protected String doInBackground(Void... params) {

            SpeedTestSocket speedTestSocket = new SpeedTestSocket();

            // add a listener to wait for speedtest completion and progress
            speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

                @Override
                public void onCompletion(SpeedTestReport report) {
                    // called when download/upload is finished
                    Log.v("speedtest", "[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest", "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
                    Download_bit_end = "Download speed : " + report.getTransferRateBit().floatValue()/1048576 + " Mbps";

                    publishProgress(Download_bit_end);
                }

                @Override
                public void onError(SpeedTestError speedTestError, String errorMessage) {
                    // called when a download/upload error occur
                }

                @Override
                public void onProgress(float percent, SpeedTestReport report) {
                    // called to notify download/upload progress
                    Log.v("speedtest", "[PROGRESS] progress : " + percent + "%");
                    Log.v("speedtest", "[PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest", "[PROGRESS] rate in bit/s   : " + report.getTransferRateBit());
                    Download_bit_progress = "Download = " + report.getTransferRateBit().floatValue()/1000000 + " Mbps" +"\n"
                            + "Progress : "+ percent + "%" ;

                    publishProgress(Download_bit_progress);
                    /*
                    try {
                        publishProgress(Download_bit_progress);
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                    }*/
                }
            });

            //https://ambeent-speed-test.azureedge.net/10MB-11.zip
            //http://sam1speedtest.turktelekom.com.tr/speedtest/random750x750.jpg
            speedTestSocket.startDownload("http://sam1speedtest.turktelekom.com.tr/speedtest/random2000x2000.jpg");

            return null;
        }

        protected void onProgressUpdate(String... progress) {
            tv.setText(Download_bit_progress);
            tv2.setText(Download_bit_end);
        }

        protected void onPostExecute(Void result) {

        }
    }
}
