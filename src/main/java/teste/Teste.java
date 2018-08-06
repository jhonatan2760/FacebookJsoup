package teste;

import br.com.jhonatansouza.FacebookConnect;
import br.com.jhonatansouza.Profile;
import java.io.IOException;


/**
 * @author Jhonatan S. Souza
 */
public class Teste {

    public static void main(String[] args) {
        FacebookConnect fb = new FacebookConnect("your_login", "your_password");
        
        try {
            Profile pf = fb.connectAndRetrieveProfile();
            
            System.out.println("User: ");
            System.out.println(pf.getName());
            
            System.out.println("Notifications : "+pf.getNotificationCount());
            
            System.out.println("Notifications description: ");
            pf.getNotifications().forEach( n -> {
                System.out.println(n.getBody());
                System.out.println(n.getWhen());
            });
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
