package chat.weaterAPI;

public class Current
{
    private String time;
    private int interval;
    private float temperature_2m;
    private float apparent_temperature;
    private int is_day;
    private int precipitation;

    public Current()
    {
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setInterval(int interval)
    {
        this.interval = interval;
    }

    public void setTemperature_2m(float temperature_2m)
    {
        this.temperature_2m = temperature_2m;
    }

    public void setApparent_temperature(float apparent_temperature)
    {
        this.apparent_temperature = apparent_temperature;
    }

    public void setIs_day(int is_day)
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

    public float getTemperature_2m()
    {
        return temperature_2m;
    }

    public float getApparent_temperature()
    {
        return apparent_temperature;
    }

    public int getIs_day()
    {
        return is_day;
    }

    public int getPrecipitation()
    {
        return precipitation;
    }
}
