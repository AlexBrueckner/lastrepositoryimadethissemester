package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;

/**
 * Created by Alex on 25.06.2017.
 */

public final class TaskPost extends KehrwochenDataRequest {
    private static final List<String> excludeFields = new ArrayList<String>();
    //I only need this ONCE PER TYPE! Hence the static block.
    static{
        excludeFields.add("comments");
        excludeFields.add("state");
        excludeFields.add("creationDate");
    }
    private static final Gson mySerializer = ExclusionStrategyFactory.build(excludeFields,null);


    public TaskPost(){
        super();
    }

    public static String getRequest(User u, Task t){
        if (u != null){
            String s = "{ \"userName\":\"" + u.getUserName()
                    + "\",\n\"task\":" + mySerializer.toJson(t) + "}";
            System.out.println("JSON = " + s);

            return "{ \"userName\":\"" + u.getUserName()
                    + "\",\n\"task\":" + mySerializer.toJson(t) + "}";
        }
        else{
            throw new IllegalArgumentException("Expected valid user object!");
        }
    }


}
