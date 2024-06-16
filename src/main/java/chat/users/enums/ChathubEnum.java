package chat.users.enums;

import lombok.Getter;

public enum ChathubEnum
{
    DatabasePath("jdbc:ucanaccess://src/main/java/chat/database/TestDB.accdb");

    private ChathubEnum(String path)
    {
        this.path = path;
    }

    @Getter
    private String path;
}
