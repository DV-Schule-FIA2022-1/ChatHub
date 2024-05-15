package chat.Location;

import java.io.IOException;
import java.net.URI;
import java.net.URL;


import chat.IndiaDistanceApi.Userdistance;
import chat.IpGeolocationAPIFolder.Geolocation;
import chat.OpenMeteoApi.GeolocationOpenMeteoApi;
import chat.WeaterAPI.WeatherClass;
import com.google.gson.Gson;

import  java.lang.System;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import java.lang.String;

public class LocationMain
{
    //UserKlasse braucht geolocation g (muss noch implemented werden)


    //wetter api URL (KEIN KEY?!) https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m
    // BESSER https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,apparent_temperature,is_day,precipitation&hourly=temperature_2m
    //geolocation https://ipinfo.io/json (KEIN KEY)

    Gson gson = new Gson();
    //Gson gson2 = new Gson();
   // Gson gson3 = new Gson();
    URL url;
    String json;
    GeolocationOpenMeteoApi g;
    Geolocation g2;
    String parts[];

    //URL url2;
    //private User user;
    ArrayList<GeolocationOpenMeteoApi> listCoordinates;

    public  LocationMain(/*User user*/)
    {
        //this.user = user;

        //location();

        try
        {


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://india-pincode-with-latitude-and-longitude.p.rapidapi.com/api/v1/distance"))
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("X-RapidAPI-Key", "c40b571c54msh3852022a451aafdp1e569djsn05830a4a9bf4")
                    .header("X-RapidAPI-Host", "india-pincode-with-latitude-and-longitude.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString("lat1=50&lng1=10&lat2=50.005&lng2=10.001&unit=km"))
                    .build();


            String queryString = request.getQueryString();
            String requestURL = url.toString();
            url = new URL("https://india-pincode-with-latitude-and-longitude.p.rapidapi.com/api/v1/distance");

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
            //  Userdistance u = new Userdistance();


        }catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void location()
    {
        try
        {
            url = new URL("https://ipinfo.io/json");
            json = stream(url);

            g = gson.fromJson(json, GeolocationOpenMeteoApi.class);

            parts = g.getLoc().split(",") ;
            g.setLat(Float.parseFloat(parts[0]));
            g.setLon(Float.parseFloat(parts[1]));


            URL url = new URL("https://api.ipgeolocation.io/ipgeo?apiKey=7d698786739843e39a69b50c9f1afd0f&ip="+ g.getIp()); // ipgeolocation.io
            String json = stream(url);
            //System.out.println(json);
            g2= gson.fromJson(json, Geolocation.class);
            weather(g2);
            //user.setGeolocation(g2);
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void weather(Geolocation g2)
    {
        try
        {
            url = new URL("https://api.open-meteo.com/v1/forecast?latitude=" +g2.getLatitude()+"&longitude="+g2.getLongitude()+"&current=temperature_2m,apparent_temperature,is_day,precipitation&hourly=temperature_2m");
            String weather = stream(url);
            WeatherClass w = gson.fromJson(weather,WeatherClass.class);
            System.out.println(w.getElevation());
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void distanceBetween() //liste mit user übergeben
    {
        ArrayList test = new ArrayList();//Liste mit Usern
        ArrayList<Userdistance> distance = new ArrayList<>();

        //lat1,long1 lat2 long2 durch parameter ersetzen
        try
        {
            for(int a=0;a<test.size();a++)
            {
                for(int b=0; b<test.size();b++)
                {
                    if(b!=a)
                    {
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create("https://india-pincode-with-latitude-and-longitude.p.rapidapi.com/api/v1/distance"))
                                .header("content-type", "application/x-www-form-urlencoded")
                                .header("X-RapidAPI-Key", "c40b571c54msh3852022a451aafdp1e569djsn05830a4a9bf4")
                                .header("X-RapidAPI-Host", "india-pincode-with-latitude-and-longitude.p.rapidapi.com")
                                .method("POST", HttpRequest.BodyPublishers.ofString("lat1=50&lng1=10&lat2=50.005&lng2=10.001&unit=km"))
                                .build();
                        S
                        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


                     //   Userdistance u = new Userdistance()

                        System.out.println(response.body());

                    }

                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Geolocation getG2()
    {
        return g2;
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
