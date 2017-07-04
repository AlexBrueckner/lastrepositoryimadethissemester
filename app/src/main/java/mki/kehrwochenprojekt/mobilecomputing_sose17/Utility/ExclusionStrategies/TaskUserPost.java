package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;


import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Task;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;

/**
 * Created by Alex on 26.06.2017.
 */

public class TaskUserPost extends KehrwochenDataRequest{


    public TaskUserPost(){
        super();
    }

    public static String getRequest(Task t, User oldUser, User newUser){

        if(t == null){
            throw new IllegalArgumentException("Invalid task specified - check arguments");
        }
        if(oldUser == null){
            throw new IllegalArgumentException("oldUSer invalid - check arguments!");
        }
        if(newUser == null){
            throw new IllegalArgumentException("newUser invalid - check arguments");
        }

        return "{\n\"taskId:\""+t.getTaskId()+"\",\n" +
                "\"oldUserName\":\""+oldUser.getUserName()+"\",\n" +
                "\"newUserName\":\""+newUser.getUserName()+"\"\n}";


    }











}
