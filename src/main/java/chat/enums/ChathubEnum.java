package chat.enums;

import lombok.Getter;

public enum ChathubEnum
{
    DatabasePath("jdbc:ucanaccess://src/main/resources/database/ChatDB.accdb");

    private ChathubEnum(String path)
    {
        this.path = path;
    }

    @Getter
    private String path;
}