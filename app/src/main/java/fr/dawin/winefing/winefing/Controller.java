package fr.dawin.winefing.winefing;

import android.os.StrictMode;

import java.util.HashMap;

/**
 * Created by Greg on 31/01/2017.
 */

// 1 méthode par requête à faire
public class Controller {

    public static final String API_URL = "http://dawin.winefing.fr/winefing/web/app_dev.php/api";
    public RequestManager monService;

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // Permet de réaliser les requêtes en Synchrone



    public String login(String email, String plainPassword){
        StrictMode.setThreadPolicy(policy); // Passage en synchrone

        monService = new RequestManager();
        String url = API_URL + "/users/tokens.json";
        HashMap<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("email",email);
        requestParams.put("plainPassword", plainPassword);

        return monService.post(url, requestParams);

    }

    public String register(String firstName, String lastName, String email, String password) {
        StrictMode.setThreadPolicy(policy);

        monService = new RequestManager();
        String url = API_URL + "/users.json";
        HashMap<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("email", email);
        requestParams.put("password", password);
        requestParams.put("roles", "ROLE_USER");

        return monService.post(url, requestParams);
    }

    public String getProperties(){
        StrictMode.setThreadPolicy(policy);

        String url = API_URL + "/domains.json";
        HashMap<String, String> requestParams = new HashMap<String,String>();

        return monService.get(url, requestParams);
    }

    public String getLocationsByProperties(String idProperty){
        StrictMode.setThreadPolicy(policy);

        String url = API_URL + "/";
        HashMap<String, String> requestParams = new HashMap<String, String>();

        return monService.get(url, requestParams);
    }
}
