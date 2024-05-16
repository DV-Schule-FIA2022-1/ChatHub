package chat.users;

import java.util.ArrayList;
import java.util.Date;

public class Role
{
    private int roleID;
    private String roleName;
    private Permission permissions;
    private String description;
    private ArrayList<User> users;
    private Date createDate;
    private Date updateDate;
    private boolean isActive;

    public Role()
    {

    }
}
