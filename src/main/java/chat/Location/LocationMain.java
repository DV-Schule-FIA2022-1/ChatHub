package chat.Location;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import  java.lang.System;
import java.io.PrintStream;
import java.util.Arrays;


public class LocationMain
{
    //wetter api URL (KEIN KEY?!) https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m
    // BESSER https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,apparent_temperature,is_day,precipitation&hourly=temperature_2m
    //geolocation https://ipinfo.io/json (KEIN KEY)


    public  LocationMain()
    {

        Gson gson = new Gson();
        Gson gson2 = new Gson();
        try
        {
            URL url = new URL("https://ipinfo.io/json");
            String json = stream(url);
            Geolocation g = gson.fromJson(json,Geolocation.class);

            String parts[] = g.getLoc().split(",") ;
            float lat = Float.parseFloat(parts[0]);
            float lon = Float.parseFloat(parts[1]);

            URL url2 = new URL("https://api.open-meteo.com/v1/forecast?latitude=" +lat+"&longitude="+lon+"&current=temperature_2m,apparent_temperature,is_day,precipitation&hourly=temperature_2m");
            String weather = stream(url2);
            WeatherClass w = gson2.fromJson(weather,WeatherClass.class);
            System.out.println(w.getElevation());

        } catch (MalformedURLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public String stream(java.net.URL url) throws java.io.IOException

    {

        try(java.io.InputStream input = url.openStream())

        {

            java.io.InputStreamReader isr = new java.io.InputStreamReader(input);

            java.io.BufferedReader reader = new java.io.BufferedReader(isr);

            StringBuilder json = new StringBuilder();

            int c;

            while((c = reader.read()) != -1)

            {

                json.append((char)c);

            }

            return json.toString();

        }

    }

}
