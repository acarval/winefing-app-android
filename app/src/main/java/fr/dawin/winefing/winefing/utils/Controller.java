package fr.dawin.winefing.winefing.utils;

import android.os.StrictMode;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Greg on 31/01/2017.
 */

public class Controller {

    public static final String API_URL = "https://dev.winefing.fr/winefing/web/app_dev.php/api";
    public RequestManager monService;

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // Permet de réaliser les requêtes en Synchrone
    public static final String LANGUE = Locale.getDefault().getLanguage();


    public String login(String email, String plainPassword){
        StrictMode.setThreadPolicy(policy); // Passage en synchrone

        monService = new RequestManager();
        String url = API_URL + "/users/tokens.json";
        HashMap<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("email",email);
        requestParams.put("plainPassword", plainPassword);

        return monService.post(url, requestParams);

    }

    public String register(String firstName, String lastName, String email, String password, String birthDate) {
        StrictMode.setThreadPolicy(policy);

        monService = new RequestManager();
        String url = API_URL + "/users.json";
        HashMap<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("email", email);
        requestParams.put("password", password);
        requestParams.put("birth_date", birthDate);
        requestParams.put("roles", "ROLE_USER");

        return monService.post(url, requestParams);
    }

    public String getProperties(){
        StrictMode.setThreadPolicy(policy);

        monService = new RequestManager();
        String url = API_URL + "/mobile/properties/"+ LANGUE +".json";
        return monService.get(url);
    }

    public String getLocationsFromProperty(int idProperty){
        StrictMode.setThreadPolicy(policy);

        monService = new RequestManager();
        String url = API_URL + "/mobile/rentals/property/" + idProperty + "/" + LANGUE +".json";
        return monService.get(url);
    }

    /*public String getPropertyImagesPath(){
        StrictMode.setThreadPolicy(policy);

        monService = new RequestManager();
        String url = API_URL + "/property/media/path.json";
        return monService.get(url);
    }
    */
}
