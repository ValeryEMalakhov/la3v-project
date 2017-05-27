package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Учебные пособия
 */
public class TutorialAttributes implements IAttributes {

    private String authors, topic;
    private String process, docType;
    private static Gson gson = new Gson();

    public TutorialAttributes() {
    }

    public TutorialAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), TutorialAttributes.class);
    }

    @Override
    public List<String> toListString() {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Авторы: " + this.authors);
        output.add("Тема: " + this.topic);
        return output;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
        TutorialAttributes.gson = gson;
    }
}
