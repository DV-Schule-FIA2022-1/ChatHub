package chat.OpenMeteoApi;

public class GeolocationOpenMeteoApi
{

    private String ip;
    private String city;
    private String region;
    private String country;
    private String loc;
    private String org;
    private int postal;
    private String timezone;
    private String readme;

    private float lat;
    private float lon;

    public float getLat()
    {
        return lat;
    }



    public GeolocationOpenMeteoApi()
    {

    }

    public GeolocationOpenMeteoApi(float lat, float lon)
    {
        this.lat = lat;
        this.lon = lon;
    }


    public void setLat(float lat)
    {
        this.lat = lat;
    }
    public float getLon()
    {
        return lon;
    }
    public void setLon(float lon)
    {
        this.lon = lon;
    }
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public void setLoc(String loc)
    {
        this.loc = loc;
    }

    public void setOrg(String org)
    {
        this.org = org;
    }

    public void setPostal(int postal)
    {
        this.postal = postal;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public void setReadme(String readme)
    {
        this.readme = readme;
    }

    public String getIp()
    {
        return ip;
    }

    public String getCity()
    {
        return city;
    }

    public String getRegion()
    {
        return region;
    }

    public String getCountry()
    {
        return country;
    }

    public String getLoc()
    {
        return loc;
    }

    public String getOrg()
    {
        return org;
    }

    public int getPostal()
    {
        return postal;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public String getReadme()
    {
        return readme;
    }
}
