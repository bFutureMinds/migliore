package hello.models;

import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
public class Notification {

    private NotificationType type;

    private String discription;

    public Notification(NotificationType type, String discription) {
        this.type = type;
        this.discription = discription;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
