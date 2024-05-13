package chat.Location;

public class Geolocation
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
    public Geolocation()
    {

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
