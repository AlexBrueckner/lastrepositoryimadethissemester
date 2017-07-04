package mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Flat {
    @SerializedName("_id")
    @Expose
    private String flatId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("residents")
    @Expose
    private List<String> residents = new ArrayList<String>();
    @SerializedName("penalty")
    @Expose
    private String penalty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public String getPenalty() {
        return penalty;
    }

    public String getCreator() {
        return creator;
    }

    public void setID(String id) {
        this.flatId = id;
    }

    public String getID() {
        return flatId;
    }

    public void setCreator(String creator) {
        if (creator != null && creator.length() > 0) {
            this.creator = creator;
        } else {
            throw new IllegalArgumentException("Expected valid username");
        }
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        String s = "Flat: " + flatId + " | " + name + " | " + creator + " | ";
        for (String sforeach : residents) {
            s += " - " +sforeach+" - ";
        }
        return s;
    }

}