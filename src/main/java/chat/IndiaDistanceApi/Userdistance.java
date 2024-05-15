package chat.IndiaDistanceApi;

import java.math.BigDecimal;

public class Userdistance
{
    private int id;
    private BigDecimal latitude;
    private  BigDecimal longitude;
    private float distance;

    public Userdistance(int id, BigDecimal latitude, BigDecimal longitude, float distance)
    {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public BigDecimal getLatitude()
    {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude()
    {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }

    public float getDistance()
    {
        return distance;
    }

    public void setDistance(float distance)
    {
        this.distance = distance;
    }
}
