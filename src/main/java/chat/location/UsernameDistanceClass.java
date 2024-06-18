package chat.location;

public class UsernameDistanceClass
{
    public String username;
    public float distance;

    public UsernameDistanceClass()
    {

    }

    public UsernameDistanceClass(String username, float distance)
    {
        this.username = username;
        this.distance = distance;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
