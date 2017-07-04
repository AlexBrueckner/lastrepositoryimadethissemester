package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.User;


/**
 * Created by Alex on 25.06.2017.
 */

public final class FlatUserDelete extends KehrwochenDataRequest {

    public FlatUserDelete(){
        super();
    }

    public static String getRequest(Flat f, User u){
            if(f == null){
                throw new IllegalArgumentException("Invalid flat specified - check arguments");
            }

            if(u == null){
                throw new IllegalArgumentException("Invalid User specified - check arguments");
            }

            return "{\"userName\":\""+u.getUserName()+"\",\n\"flatId\":\""+f.getID()+"\"\n}";
    }


}
