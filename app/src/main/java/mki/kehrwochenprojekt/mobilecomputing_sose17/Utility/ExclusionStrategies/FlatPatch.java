package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;


/**
 * Created by Alex on 25.06.2017.
 * Constructs a JSON Object for a Request, or a request parameter, implementation dependent
 */

public final class FlatPatch extends KehrwochenDataRequest {

    private Gson gsonGuy;
    private ArrayList<String> fieldsToExclude;

    public FlatPatch() {
        super();
        fieldsToExclude = new ArrayList<String>();
        fieldsToExclude.add("residents");
        fieldsToExclude.add("creator");
    }

    public FlatPatch(String flatID, String name, String penalty) {

        this();

        if (flatID == null) {
            throw new IllegalArgumentException("Invalid flat specified!");
        }

        if (name == null && penalty == null) {
            throw new IllegalArgumentException("Nothing to patch! Check arguments!");
        }

        if (name == null) {
            fieldsToExclude.add("name");
            System.out.println("FlatPatch Constructor: Patching name");
        }
        if (penalty == null) {
            fieldsToExclude.add("penalty");
            System.out.println("FlatPatch Constructor: Patching penalty");
        }

        gsonGuy = ExclusionStrategyFactory.build(fieldsToExclude, null);

    }

    public String getRequest(Flat u) {
        if (u != null) {
            System.out.println("Flat : " + u);
            System.out.println("Gson: " + gsonGuy);
            String s = gsonGuy.toJson(u);
            s = s.replace("_id","flatId");
            return s;



        } else {
            throw new IllegalArgumentException("Expected valid user object!");
        }
    }


}
