package chat.users.role;

import chat.users.User;
import chat.users.permission.Permission;
import chat.users.permission.PermissionEnum;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Role
{
    @Getter
    private String roleName;
    @Getter @Setter
    private String description;
    @Getter
    private Set<PermissionEnum> permissions;
    private int priortiyLevel;
    private Date creationDate;
    @Getter
    private LocalDateTime createAt;
    @Getter @Setter
    private LocalDateTime updatedAt;
    @Getter @Setter
    private boolean isActive;

    public Role(String roleName, String description, int priortiyLevel)
    {
        this.roleName = roleName;
        this.description = description;
        this.priortiyLevel = priortiyLevel;
        this.permissions = new HashSet<>();
        this.creationDate = new Date();
    }

    public Role(String roleName, String description, LocalDateTime createAt, LocalDateTime updatedAt, boolean isActive)
    {
        this.roleName = roleName;
        this.description = description;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
        this.permissions = new HashSet<>();
    }

    //Rolen Übersicht
    //Administrator:

    //- Hat vollständige Kontrolle über das System.
    //- Kann Benutzer hinzufügen, bearbeiten und löschen.
    //- Kann Chatrooms erstellen, bearbeiten und löschen.
    //- Kann Nachrichten moderieren (löschen, bearbeiten).

    //Moderator:

    //- Kann Benutzer verwarnen oder sperren.
    ///- Kann Nachrichten moderieren (löschen, bearbeiten).
    //- Kann Chatrooms überwachen und moderieren.

    // Registrierter Benutzer:

    //- Kann Nachrichten senden und empfangen.
    //- Kann private Nachrichten senden und empfangen.
    //- Kann Chatrooms betreten und verlassen.
    //- Kann Chatrooms erstellen (optional, abhängig von der Implementierung).

    //Gast:

    //- Kann Nachrichten lesen (öffentlich zugängliche Chatrooms).
    //-Kann eingeschränkten Zugriff auf bestimmte Funktionen haben.
}
