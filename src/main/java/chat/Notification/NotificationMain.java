package chat.Notification;

import javafx.collections.ObservableArray;

import java.util.ArrayList;
import java.util.List;

public class NotificationMain
{
    //vllt mit observable arraylist versuchen
    private ArrayList<String> messagelist;

    public  NotificationMain()
    {
        messagelist = new ArrayList<>();
        messagelist.add("hi");
        messagelist.add("hi2");

    }

    public ArrayList<String> getMessagelist()
    {
        return messagelist;
    }
}
