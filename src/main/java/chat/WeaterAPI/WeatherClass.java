package chat.WeaterAPI;

public class WeatherClass
{
    private float latitude;
    private float longitude;
    private float generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private  String timezone_abbreviation;
    private float elevation;
    private Current_Units currentUnits;
    private Current current;
    private Hourly_units hourlyUnits;
    private Hourly hourly;

    public WeatherClass()
    {

    }

    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

    public void setGenerationtime_ms(float generationtime_ms)
    {
        this.generationtime_ms = generationtime_ms;
    }

    public void setUtc_offset_seconds(int utc_offset_seconds)
    {
        this.utc_offset_seconds = utc_offset_seconds;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public void setTimezone_abbreviation(String timezone_abbreviation)
    {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public void setElevation(float elevation)
    {
        this.elevation = elevation;
    }

    public void setCurrentUnits(Current_Units currentUnits)
    {
        this.currentUnits = currentUnits;
    }

    public void setCurrent(Current current)
    {
        this.current = current;
    }

    public void setHourlyUnits(Hourly_units hourlyUnits)
    {
        this.hourlyUnits = hourlyUnits;
    }

    public void setHourly(Hourly hourly)
    {
        this.hourly = hourly;
    }

    public float getLatitude()
    {
        return latitude;
    }

    public float getLongitude()
    {
        return longitude;
    }

    public float getGenerationtime_ms()
    {
        return generationtime_ms;
    }

    public int getUtc_offset_seconds()
    {
        return utc_offset_seconds;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public String getTimezone_abbreviation()
    {
        return timezone_abbreviation;
    }

    public float getElevation()
    {
        return elevation;
    }

    public Current_Units getCurrentUnits()
    {
        return currentUnits;
    }

    public Current getCurrent()
    {
        return current;
    }

    public Hourly_units getHourlyUnits()
    {
        return hourlyUnits;
    }

    public Hourly getHourly()
    {
        return hourly;
    }
}
