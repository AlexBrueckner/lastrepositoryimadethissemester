package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.RequestType;


/**
 * Created by Alex on 25.06.2017.
 */

public abstract class KehrwochenDataRequest {
    protected RequestType type;
    protected String apiEndpoint;
    protected JSONObject requestData;
    protected List<String> fieldsToExclude;
    protected List<String> classesToExclude;


    public KehrwochenDataRequest(){


    }

}

