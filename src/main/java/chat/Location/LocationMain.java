package chat.Location;

import java.io.IOException;
import java.net.URI;
import java.net.URL;



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
//user braucht arrayliste mit klasse mit username und distanz, muss noch sortiert werden und die nähsten 4 werden angezeigt
    // + Liste mit anderen Usern o.ä.die übergeben werden kann


    Gson gson = new Gson();

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

        GeolocationOpenMeteoApi user1 = new GeolocationOpenMeteoApi(49.7939F, 9.9512F);
        ArrayList<GeolocationOpenMeteoApi> userlisttest = new ArrayList<>();
        GeolocationOpenMeteoApi user2 = new GeolocationOpenMeteoApi(49.9604785F, 9.7734603F);
        userlisttest.add(user2);
        distancebetweenCoords(userlisttest, user1);

    }

    public void distancebetweenCoords (ArrayList<GeolocationOpenMeteoApi> otherUsers, GeolocationOpenMeteoApi user1) //Liste mit user übergeben, originaler User übergebene werte austauschen mit richtiger Klasse!!!!!
    {
        ArrayList<UsernameDistanceClass> distanceEveryoneElse = new ArrayList<>();
        double el1 = 0;
        double el2 = 0;
        for (GeolocationOpenMeteoApi user2: otherUsers)
        {

            double r2d = 180.0D / 3.141592653589793D;
            double d2r = 3.141592653589793D / 180.0D;
            double d2km = 111189.57696D * r2d;

            double x = user1.getLat() * d2r;
            double y = user2.getLat() * d2r;
            double result = Math.acos( Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos(d2r * (user1.getLon() - user2.getLon()))) * d2km;
            result= Math.round(result *100.0 ) / 100.0 ;

            //UsernameDistanceClass new1 = new UsernameDistanceClass(user2.getUsername, result);
           // distanceEveryoneElse.add(new1);


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
