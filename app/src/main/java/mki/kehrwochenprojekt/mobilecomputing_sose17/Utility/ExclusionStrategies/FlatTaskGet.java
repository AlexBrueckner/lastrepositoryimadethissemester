package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;


/**
 * Created by Alex on 25.06.2017.
 */

public final class FlatTaskGet extends KehrwochenDataRequest {
    private static final List<String> excludeFields = new ArrayList<String>();
    //I only need this ONCE PER TYPE! Hence the static block.
    static{
        excludeFields.add("name");
        excludeFields.add("residents");
        excludeFields.add("penalty");
        excludeFields.add("creator");
    }
    private static final Gson mySerializer = ExclusionStrategyFactory.build(excludeFields,null);


    public FlatTaskGet(){
        super();
    }

    public static String getRequest(Flat f){
        if (f != null){
            return "?flatId="+f.getID();
        }
        else{
            throw new IllegalArgumentException("Expected valid Flat object!");
        }
    }


}
