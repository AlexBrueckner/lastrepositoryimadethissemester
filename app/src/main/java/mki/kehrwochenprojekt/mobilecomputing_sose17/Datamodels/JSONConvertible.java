package mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels;
import org.json.JSONObject;
/**
 * Created by Alex on 22.06.2017.
 * Offers methods for conversion from and to JSON
 */

public interface JSONConvertible {
    public JSONObject toJSON(Object o);
    public Object toObject(JSONObject jo);
}
