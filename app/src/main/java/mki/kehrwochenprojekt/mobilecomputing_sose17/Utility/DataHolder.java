package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility;

import java.io.IOException;
import java.util.HashMap;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;

/**
 * Created by alexb on 03.07.2017.
 */

public final class DataHolder {

    private static User currentUser;
    private static Flat currentFlat;
    private static final RESTClient client;

    private static HashMap<String,KehrwochenArrayAdapter> adapters =
            new HashMap<String,KehrwochenArrayAdapter>();


    static {
        currentUser = new User();
        currentFlat = new Flat();
        client = new RESTClient("http://10.0.2.2:8080");
    }

    public static void setCurrentUser(User u) {
        if (u != null) {
            currentUser = u;
        }
       else{
            System.err.println("WARNING: Argument was null! User was not updated");
        }
    }

    public static void setCurrentFlat(Flat f) {
        if (f != null) {
            currentFlat = f;
        }
       else{
            System.err.println("WARNING: Argument was null! Flat was not updated!!");
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Flat getCurrentFlat() {
        return currentFlat;
    }

    public static String executeRequest(String endpoint, String payload, String requestVerb) {
        System.out.println("Inside executeRequest. ARguments: " + endpoint + " | " + payload
                + " | " + requestVerb);
        String response;
        try {
            System.out.println("Sending request, writing response");
            response = client.sendRequest(endpoint, payload, requestVerb);
            System.out.println("Response written!");
        } catch (IOException e) {
            response = "ERROR -> " + e.getMessage();
            e.printStackTrace();
        }
        return response;
    }

    public static HashMap<String,KehrwochenArrayAdapter> getKehrwochenAdapters(){
        return adapters;
    }

}
