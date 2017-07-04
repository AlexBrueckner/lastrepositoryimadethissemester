package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.Flat;


/**
 * Created by Alex on 25.06.2017.
 * Constructs a JSON Object for a Request, or a request parameter, implementation dependent
 */

public final class FlatPost extends KehrwochenDataRequest {
    private static final List<String> excludeFields = new ArrayList<String>();

    //I only need this ONCE PER TYPE! Hence the static block.
    static {

        excludeFields.add("residents");
    }

    private static final Gson mySerializer = ExclusionStrategyFactory.build(excludeFields, null);


    public FlatPost() {
        super();
    }

    public static String getRequest(Flat f) {
        if (f != null) {
            return mySerializer.toJson(f);
        } else {
            throw new IllegalArgumentException("Expected valid user object!");
        }
    }


}
