package mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Alex on 25.06.2017.
 */

public class TasksConverter {

    public static Map<String,Task> convertTasks(JSONObject tasksJSON){

        try {
            HashMap<String, Task> tasks = new HashMap<String, Task>();
            Iterator<String> taskIterator = tasksJSON.keys();
            while (taskIterator.hasNext()) {
                String key = taskIterator.next();
                Task t = new Task();
              //  t = (Task)t.toObject(tasksJSON.getJSONObject(key));
                tasks.put(key, t);
            }

            return tasks;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }



}
