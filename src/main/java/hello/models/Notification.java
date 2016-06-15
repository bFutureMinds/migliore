package hello.models;

import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
public class Notification {

    private NotificationType type;

    private String description;

    public Notification(NotificationType type, String description) {
        this.type = type;
        this.description = description;
    }

    public Notification() {
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String discription) {
        this.description = discription;
    }
}
