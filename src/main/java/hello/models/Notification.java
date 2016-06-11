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
}
