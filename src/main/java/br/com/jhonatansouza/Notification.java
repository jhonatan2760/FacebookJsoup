package br.com.jhonatansouza;

import java.io.Serializable;

/**
 * @author Jhonatan S. Souza
 */

public class Notification implements Serializable {
    
    public Notification(String body, String when){
        this.body = body;
        this.when = when;
    }
    
    public Notification(){}
    
    private String body;
    private String when;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }
    
    
}
