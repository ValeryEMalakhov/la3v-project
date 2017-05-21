package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Курсовые работы
 */
public class CourseWorkAttributes implements IAttributes {

    private String author, supervisor, topic, studyYear;
    private String process, docType;
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
        output.add("Автор: " + this.author);
        output.add("Научный руководитель: " + this.supervisor);
        output.add("Учебный год: " + this.studyYear);
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

    @Override
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Override
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
        CourseWorkAttributes.gson = gson;
    }
}
