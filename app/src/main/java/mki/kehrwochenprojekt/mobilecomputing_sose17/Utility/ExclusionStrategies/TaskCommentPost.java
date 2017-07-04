package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;


/**
 * Created by Alex on 25.06.2017.
 * Constructs a JSON Object for a Request, or a request parameter, implementation dependent
 */
public final class TaskCommentPost extends KehrwochenDataRequest {
    public TaskCommentPost() {
        super();
    }

    public static String getRequest(Task t, String comment) {
        if (t != null && comment != null && comment.length() > 0) {
            return "{ \"taskId\":\"" + t.getTaskId()
                    + "\",\n\"comment\":\"" + comment + "\"}";
        } else {
            throw new IllegalArgumentException("Expected valid user object!");
        }
    }


}
