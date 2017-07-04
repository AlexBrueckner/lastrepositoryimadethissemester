package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;


/**
 * Created by Alex on 25.06.2017.
 * Constructs a JSON Object for a Request, or a request parameter, implementation dependent
 */

public final class FlatGet extends KehrwochenDataRequest {

    public FlatGet() {
        super();
    }

    public static String getRequest(User u) {
        if (u == null || u.getUserName().length() <= 0) {
            throw new IllegalArgumentException("Invalid user specified - check arguments");
        }

        //USE THIS IN THE URL!!!!!!!!!!!
        return "?userName=" + u.getUserName();
    }


}
