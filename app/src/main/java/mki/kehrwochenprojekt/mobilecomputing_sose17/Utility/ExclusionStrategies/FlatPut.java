package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;


/**
 * Created by Alex on 25.06.2017.
 * Constructs a JSON Object for a Request, or a request parameter, implementation dependent
 */
public final class FlatPut extends KehrwochenDataRequest {
    public FlatPut() {
        super();
    }

    public static String getRequest(User u, Flat f) {

        if (u.getUserName() == null || u.getUserName().length() <= 0) {
            throw new IllegalArgumentException("Invalid user specified - check ARguments");
        } else if (f == null) {
            throw new IllegalArgumentException("Invalid flat specified - check ARguments");
        }

        String s = "{\"flatId\":\"" + f.getID() + "\",\n\"userName\":\"" + u.getUserName() + "\"\n}";
        System.out.println("DEBUG: Created request String");
        System.out.println(s);
        System.out.println("-----");
        return s;
    }


}
