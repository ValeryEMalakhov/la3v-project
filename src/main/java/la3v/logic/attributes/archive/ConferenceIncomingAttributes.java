package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Проводимые конференции
 */
public class ConferenceIncomingAttributes implements IAttributes {

    private String name, data;
    private String process, docType;
    private static Gson gson = new Gson();

    public ConferenceIncomingAttributes() {
    }

    public ConferenceIncomingAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ConferenceIncomingAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Название: " + this.name);
        output.add("Дата: " + this.data);
        return output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        ConferenceIncomingAttributes.gson = gson;
    }
}
