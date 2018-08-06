package br.com.jhonatansouza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Jhonatan S. Souza
 */
public class FacebookConnect {

    private final String login;
    private final String password;

    public FacebookConnect(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Profile connectAndRetrieveProfile() throws IOException {

        /*
            Assumes the http protocol in all scope
            Response class have a unique resposability 
            to assumes the http request response
            See more: https://jsoup.org/apidocs/org/jsoup/Connection.Response.html
         */
        Connection.Response req;

        /*
            In computing, a user agent is software (a software agent) that is acting on behalf of a user. 
            One common use of the term refers to a web browser telling a website information about the browser and 
            operating system
            
            Wikypedia.
         */
        final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";

        /*
                Initializing and configuring the requisition using a lill trick (the facebook mobile link)
                
                Basic request setup:
                
                -UserAgent
                -Method (post)
                -FormData (email, password) *required* -tks, captain obsivously
                -AllowRedirects
            
                
         */
        req = Jsoup.connect("https://m.facebook.com/login/async/?refsrc=https%3A%2F%2Fm.facebook.com%2F&lwv=101")
                .userAgent(userAgent)
                .method(Connection.Method.POST).data("email", this.login).data("pass", this.password)
                .followRedirects(true).execute();

        /*
                Receiving the response.
                basicaly we i'll receive the session cookies 
                and by-pass thought the facebook /home.php
         */
        Document d = Jsoup.connect("https://m.facebook.com/home.php")
                .userAgent(userAgent)
                .cookies(req.cookies()).get();

        /*
            Getting the user data
            and instancianting the Profile.
         */
        Profile profile = new Profile();
        Elements els = d.body().getElementsByAttribute("data-store");
        List<Notification> notifications = new ArrayList<>();
        
        /*
            Manipulating Facebook dom.
        */
//        profile.setName(d.body().getElementsByClass("_52ja").get(0).text());
        els.get(6).children().forEach(children -> {
            children.getElementsByClass("c").forEach(n -> {
                Notification not = new Notification();
                not.setWhen(n.getElementsByClass("mfss").get(0).text());
                notifications.add(not);
                not.setBody(n.text());
            });
        });
        
        profile.setNotifications(notifications);

        return profile;
    }

}
