package mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * User
 * <p>
 */
public class User {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("foreName")
    @Expose
    private String foreName;
    @SerializedName("surName")
    @Expose
    private String surName;
    @SerializedName("tasks")
    @Expose
    private List<Task> tasks = null;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForeName() {
        return foreName;
    }

    public User() {
        tasks = new ArrayList<Task>();
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

}