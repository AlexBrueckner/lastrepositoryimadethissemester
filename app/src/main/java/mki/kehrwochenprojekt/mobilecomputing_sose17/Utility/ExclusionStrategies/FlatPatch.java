package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;


/**
 * NOT A STATIC CLASS SINCE ARGUMENTS ARE PRONE TO CHANGE!!!!!
 * Created by Alex on 25.06.2017.
 */

public final class FlatPatch extends KehrwochenDataRequest {

    private Gson gsonGuy;
    public FlatPatch(){
        super();
        fieldsToExclude = new ArrayList<String>();
    }

    public FlatPatch(String flatID, String name, String penalty){

        this();

        if(flatID == null){
            throw new IllegalArgumentException("Invalid flat specified!");
        }

        if(name == null && penalty == null){
            throw new IllegalArgumentException("Nothing to patch! Check arguments!");
        }

        if(name == null){
            fieldsToExclude.add("name");
            System.out.println("FlatPatch Constructor: Patching name");
        }
        if(penalty == null){
            fieldsToExclude.add("penalty");
            System.out.println("FlatPatch Constructor: Patching penalty");
        }

        gsonGuy = ExclusionStrategyFactory.build(fieldsToExclude,null);

    }

    public String getRequest(Flat u){
        if (u != null){
            return gsonGuy.toJson(u);
        }
        else{
            throw new IllegalArgumentException("Expected valid user object!");
        }
    }


}
