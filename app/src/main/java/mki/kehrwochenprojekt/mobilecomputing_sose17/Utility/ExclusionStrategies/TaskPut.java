package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;


/**
 * Created by Alex on 25.06.2017.
 */

public final class TaskPut extends KehrwochenDataRequest {
    private static final List<String> excludeFields = new ArrayList<String>();
    //I only need this ONCE PER TYPE! Hence the static block.
    static{
        excludeFields.add("name");
        excludeFields.add("comments");
        excludeFields.add("deadline");
        excludeFields.add("creationDate");
        excludeFields.add("guideline");
    }
    private static final Gson mySerializer = ExclusionStrategyFactory.build(excludeFields,null);


    public TaskPut(){
        super();
    }

    public static String getRequest(Task t){
        if (t != null){
            return mySerializer.toJson(t);
        }
        else{
            throw new IllegalArgumentException("Expected valid user object!");
        }
    }


}
