package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.*;

/**
 * Created by Marmonth on 05.05.2017.
 * Дипломы
 */
public class DiplomaAttributes implements IAttributes {

    private String author, supervisor, topic, studyYear, annotation;
    private String process, docType;
    private static Gson gson = new Gson();

    public DiplomaAttributes() {
    }

    public static DiplomaAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), DiplomaAttributes.class);
    }


    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Тема диплома: " + this.topic);
        output.add("Автор: " + this.author);
        output.add("Научный руководитель: " + this.supervisor);
        output.add("Учебный год: " + this.studyYear);
        output.add("Аннотация: " + this.annotation);
        return output;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Override
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        DiplomaAttributes.gson = gson;
    }
}
