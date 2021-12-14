package com.example.speedtestandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import android.content.Intent;

import java.io.BufferedReader;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.util.Log;
import java.net.HttpURLConnection;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv0 = (TextView) findViewById(R.id.best_server_text);

        Button b1 = (Button) findViewById(R.id.devam);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

        Button b2 = (Button) findViewById(R.id.best_server_button);
        b2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        //new BestServer().execute();
    }
    //new BestServer().execute();
    //tv.setText("Download speed: " + Download_bit);


   /* public class BestServer extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {


            StringBuilder htmlText = new StringBuilder();
            try {
                URL openUrl = new URL("http://c.speedtest.net/speedtest-servers-static.php");
                URLConnection connection = openUrl.openConnection();

                InputStream is = connection.getInputStream();

                InputStreamReader isReader = new InputStreamReader(is, "UTF-8"); // bazı karakterlerin düzgün çıkması için UTF-8 seçtik
                int gelenData = 0;
                do {
                    gelenData = isReader.read();

                    if (gelenData != -1) { // -1 olunda okumanın sonuna yani dosyanın sonuna geldiğimizi ifade ediyor.
                        //  System.out.print((char) gelenData);
                        htmlText.append((char) gelenData);

                    }

                } while (gelenData != -1);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            // web sayfasının kaynak kodlarını çekmiş olduk.

            // Projemizi biraz daha ileri götürelim ve regularExpression kullanarak
            // Kaynak kodları içinde istediğimiz etiketler arasındaki verileri alalım

            String regexPattern = "(http://)(.*?)(:8080/speedtest)";
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(htmlText);

            String[] server_address = {};
            while (matcher.find()) {

                server_address = Arrays.copyOf(server_address, server_address.length + 1);
                server_address[server_address.length - 1] = matcher.group(2);

            }
            //System.out.println(server_address[0]);

            int[] pings = new int[server_address.length];

            for (int i = 0; i < 5; i++) {


                String ip = server_address[i];
                String pingResult = "";

                String pingCmd = "ping " + ip;
                try {
                    Runtime r = Runtime.getRuntime();
                    Process p = r.exec(pingCmd);

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(p.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        pingResult += inputLine;
                        Log.v(" ", " " + inputLine);
                    }
                    pingResult += "Average = 100000ms";

                    String regexPattern2 = "(Average = )(.*?)(ms)";
                    Pattern pattern2 = Pattern.compile(regexPattern2);
                    Matcher matcher2 = pattern2.matcher(pingResult);

                    String[] ping_value = {};
                    while (matcher2.find()) {

                        ping_value = Arrays.copyOf(ping_value, ping_value.length + 1);
                        ping_value[ping_value.length - 1] = matcher2.group(2);

                    }

                    //System.out.println(ping_value[0]);


                    pings[i] = Integer.parseInt(ping_value[0]);
                    in.close();

                } catch (IOException e) {
                    System.out.println(e);
                }
            }

            int depo = pings[0];
            int index = 0;
            for (int i = 0; i < 5; i++) {
                if (pings[i] < depo) {
                    depo = pings[i];
                    index = i;
                }
            }

            String yanit = "En iyi Server : " + server_address[index] + "\n Ping :  " + depo;

            return yanit;
        }


        protected void onPostExecute(String result) {
            tv0.setText(result);
        }
    }*/

}


