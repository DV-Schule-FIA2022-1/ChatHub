package chat.weaterAPI;

public class Hourly
{
    private String[] time;
    private float temperature_2m[];

    public Hourly()
    {
    }

    public void setTime(String[] time)
    {
        this.time = time;
    }

    public void setTemperature_2m(float[] temperature_2m)
    {
        this.temperature_2m = temperature_2m;
    }

    public String[] getTime()
    {
        return time;
    }

    public float[] getTemperature_2m()
    {
        return temperature_2m;
    }
}
