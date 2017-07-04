package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;

/**
 * Created by Alex on 25.06.2017.
 * Constructs a JSON Object for a Request, or a request parameter, implementation dependent
 */

public final class TaskPatch extends KehrwochenDataRequest {

    private Gson gsonGuy;

    public TaskPatch() {
        super();
        fieldsToExclude = new ArrayList<String>();
    }

    public TaskPatch(String taskID, String name, Date deadline, String guideline) {

        this();
        if (taskID == null) {
            throw new IllegalArgumentException("Invalid Task specified - check arguments!");
        }

        if (deadline == null && guideline == null && name == null) {
            throw new IllegalArgumentException("Nothing to patch! Check arguments!");
        }

        if (name == null) {
            fieldsToExclude.add("name");
        }

        if (deadline == null) {
            fieldsToExclude.add("deadline");
        }

        if (guideline == null) {
            fieldsToExclude.add("guideline");
        }

        fieldsToExclude.add("comments");
        fieldsToExclude.add("images");
        fieldsToExclude.add("creationDate");
        fieldsToExclude.add("state");


        gsonGuy = ExclusionStrategyFactory.build(fieldsToExclude, null);

    }

    public String getRequest(Task t) {
        if (t != null) {
            return gsonGuy.toJson(t);
        } else {
            throw new IllegalArgumentException("Expected valid task object!");
        }
    }


}
