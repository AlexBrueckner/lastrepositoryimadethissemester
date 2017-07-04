package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.ExclusionStrategies;


import mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels.JSONConvertible;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.KehrwochenUtility;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.PayloadType;
import mki.kehrwochenprojekt.mobilecomputing_sose17.Utility.RequestType;

/**
 * Created by Alex on 22.06.2017.
 */

public class KehrwochenRequest extends KehrwochenUtility {
    private String apiEndpoint;
    private String apiURL;
    private RequestType requestType;
    private String payloadType;
    private JSONConvertible response;
    private JSONConvertible payload;


    public KehrwochenRequest(String apiURL, String apiEndpoint, RequestType requestType,
                             String payloadType, JSONConvertible payload){

        this.apiURL = apiURL;
        this.apiEndpoint = apiEndpoint;
        this.requestType = requestType;
        this.payloadType = payloadType.toLowerCase().matches("json") ? PayloadType.TYPE_JSON : PayloadType.TYPE_HTML;
        this.payload = payload;
    }

    public JSONConvertible getResponse(){
        return response;
    }
}
