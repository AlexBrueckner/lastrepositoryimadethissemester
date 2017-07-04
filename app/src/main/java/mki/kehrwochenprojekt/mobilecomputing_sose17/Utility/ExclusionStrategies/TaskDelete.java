package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;


/**
 * Created by Alex on 25.06.2017.
 */

public final class TaskDelete extends KehrwochenDataRequest {
    private static final List<String> excludeFields = new ArrayList<String>();
    //I only need this ONCE PER TYPE! Hence the static block.
    static{
        excludeFields.add("name");
        excludeFields.add("creationDate");
        excludeFields.add("deadline");
        excludeFields.add("state");
        excludeFields.add("comments");
        excludeFields.add("guideline");
        excludeFields.add("images");
    }
    private static final Gson mySerializer = ExclusionStrategyFactory.build(excludeFields,null);


    public TaskDelete(){
        super();
    }

    public static String getRequest(Task t){
        if (t != null){
            try{
                JSONObject o = new JSONObject(mySerializer.toJson(t));
                return "{ \"taskId\" : \"" + o.getString("_id") + "\" }";
            }
            catch(JSONException e){
                e.printStackTrace();
                return "{ \"thisApisucks\" : \"realHard\"}";

            }

        }
        else{
            throw new IllegalArgumentException("Expected valid Task object!");
        }
    }


}
