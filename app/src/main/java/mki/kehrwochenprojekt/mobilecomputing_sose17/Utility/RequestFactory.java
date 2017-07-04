package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility;


import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.JSONConvertible;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies.KehrwochenRequest;

/**
 * Created by Alex on 22.06.2017.
 */

public class RequestFactory extends KehrwochenUtility {

    public RequestFactory(){

    }

    public KehrwochenRequest createRequest(String apiURL, String apiEndpoint, RequestType type, String payloadType,
                                           JSONConvertible payload){
            return new KehrwochenRequest(apiURL,apiEndpoint,type,payloadType,payload);
    }


}
