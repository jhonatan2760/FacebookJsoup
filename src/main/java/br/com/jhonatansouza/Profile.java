package br.com.jhonatansouza;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Jhonatan S. Souza
 * 
 * Profile example response
 */
public class Profile implements Serializable {
    
    private String name;
    private int notificationCount;
    private List<Notification> notifications;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
    }

    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    
    
    
}
