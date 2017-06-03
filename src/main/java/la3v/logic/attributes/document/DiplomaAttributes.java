package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 05.05.2017.
 * Дипломы
 */
public class DiplomaAttributes implements IAttributes {

    private String supervisor, topic, studyYear, annotation;
    private static Gson gson = new Gson();

    public DiplomaAttributes() {
    }

    @Override
    public DiplomaAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), DiplomaAttributes.class);
    }


    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Тема диплома: " + this.topic);
        output.add("Научный руководитель: " + this.supervisor);
        output.add("Учебный год: " + this.studyYear);
        output.add("Аннотация: " + this.annotation);
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

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        DiplomaAttributes.gson = gson;
    }
}
