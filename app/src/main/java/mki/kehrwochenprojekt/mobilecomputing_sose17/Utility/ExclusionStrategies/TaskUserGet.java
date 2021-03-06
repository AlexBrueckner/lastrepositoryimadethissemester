package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;

/**
 * Created by Alex on 25.06.2017.
 * Constructs a JSON Object for a Request, or a request parameter, implementation dependent
 */
public class TaskUserGet extends KehrwochenDataRequest {


    public TaskUserGet() {
        super();
    }

    public static String getRequest(User u) {

        if (u == null || u.getUserName().length() <= 0) {
            throw new IllegalArgumentException("Invalid user specified, check arguments");
        }

        return "?userName=" + u.getUserName();

    }


}
