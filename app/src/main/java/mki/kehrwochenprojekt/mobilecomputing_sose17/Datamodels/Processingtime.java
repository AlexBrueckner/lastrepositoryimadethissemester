package mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels;

import java.util.Date;

import org.json.JSONObject;

/**
 * Created by Alex on 22.06.2017.
 */

public class Processingtime extends KehrwochenData {


    private Date starttime;
    private Date deadline;

    public Processingtime(Date start, Date end) {
        starttime = start;
        deadline = end;
    }

    public Date getStarttime() {
        return starttime;
    }

    public Date getDeadline() {
        return deadline;
    }


    public boolean updateDeadline(Date newdead) {
        Date olddead = deadline;
        deadline = newdead;
        return !olddead.equals(newdead);
    }

    @Override
    public JSONObject toJSON(Object o) {
        return null;
    }

    @Override
    public Object toObject(JSONObject jo) {
        return null;
    }
}
