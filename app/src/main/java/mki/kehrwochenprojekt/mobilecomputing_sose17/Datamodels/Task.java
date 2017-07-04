package mki.kehrwochenprojekt.mobilecomputing_sose17.Datamodels;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Task
 * <p>
 */
public class Task {

    //TODO: Implement parsing of Dates
    @SerializedName("_id")
    @Expose
    private String taskId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("creationDate")
    @Expose
    private Date creationDate;
    @SerializedName("deadline")
    @Expose
    private Date deadline;
    @SerializedName("state")
    @Expose
    private Task.State state = Task.State.fromValue("RED");

    @SerializedName("images")
    @Expose
    private List<Object> images = new ArrayList<Object>();

    @SerializedName("comments")
    @Expose
    private List<String> comments = new ArrayList<String>();
    @SerializedName("guideline")
    @Expose
    private String guideline;


    public Task() {
        comments = new ArrayList<String>();
    }

    public void addComment(String s) {
        comments.add(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Task.State getState() {
        return state;
    }

    public void setState(Task.State state) {
        this.state = state;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Task - ID = " + taskId + " | " + name + " | " + creationDate + " | " + deadline +
                " | " + guideline + " | " + state;
    }

    public enum State {

        @SerializedName("RED")
        RED("RED"),
        @SerializedName("YELLOW")
        YELLOW("YELLOW"),
        @SerializedName("GREEN")
        GREEN("GREEN");
        private final String value;
        private final static Map<String, Task.State> CONSTANTS = new HashMap<String, Task.State>();

        static {
            for (Task.State c : values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private State(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Task.State fromValue(String value) {
            Task.State constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}