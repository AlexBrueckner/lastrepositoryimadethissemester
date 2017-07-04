package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;

/**
 * Created by Alex on 25.06.2017.
 */

public final class FlatDelete extends KehrwochenDataRequest {
    public FlatDelete(){
        super();
    }

    public static String getRequest(Flat f){
        if(f == null || (f.getID()==null || f.getID().length() <= 0)){
            throw new IllegalArgumentException("Invalid flat specified - check ARguments");
        }
        return "{\n\"flatID\" : \"" + f.getID() +"\"\n}";
    }


}
