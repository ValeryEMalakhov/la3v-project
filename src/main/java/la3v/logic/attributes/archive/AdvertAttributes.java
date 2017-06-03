package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Объявления
 */
public class AdvertAttributes implements IAttributes {

    private String author;
    private String process, docType;
    private static Gson gson = new Gson();

    public AdvertAttributes() {
    }

    public AdvertAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), AdvertAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Автор: " + this.author);
        return output;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        AdvertAttributes.gson = gson;
    }
}
