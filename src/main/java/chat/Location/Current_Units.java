package chat.Location;

public class Current_Units
{
    private String time; //timeformat
    private int interval; // sunshine_duration
    private String temperature_2m;
    private String apparent_temperature;
    private boolean is_day;
    private int precipitation;

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setInterval(int interval)
    {
        this.interval = interval;
    }

    public void setTemperature_2m(String temperature_2m)
    {
        this.temperature_2m = temperature_2m;
    }

    public void setApparent_temperature(String apparent_temperature)
    {
        this.apparent_temperature = apparent_temperature;
    }

    public void setIs_day(boolean is_day)
    {
        this.is_day = is_day;
    }

    public void setPrecipitation(int precipitation)
    {
        this.precipitation = precipitation;
    }

    public String getTime()
    {
        return time;
    }

    public int getInterval()
    {
        return interval;
    }

    public String getTemperature_2m()
    {
        return temperature_2m;
    }

    public String getApparent_temperature()
    {
        return apparent_temperature;
    }

    public boolean isIs_day()
    {
        return is_day;
    }

    public int getPrecipitation()
    {
        return precipitation;
    }

    public Current_Units()
    {
    }
}
