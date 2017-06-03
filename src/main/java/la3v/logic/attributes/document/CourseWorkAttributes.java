package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Курсовые работы
 */
public class CourseWorkAttributes implements IAttributes {

    private String supervisor, topic, studyYear;
    private static Gson gson = new Gson();

    public CourseWorkAttributes() {
    }

    public CourseWorkAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), CourseWorkAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Тема курсовой работы: " + this.topic);
        output.add("Научный руководитель: " + this.supervisor);
        output.add("Учебный год: " + this.studyYear);
        return output;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        CourseWorkAttributes.gson = gson;
    }
}
