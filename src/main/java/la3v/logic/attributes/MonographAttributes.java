package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Монографии
 */
public class MonographAttributes implements IAttributes {

    private String authors, monographName, editionName, year;
    private String process, docType;
    private static Gson gson = new Gson();

    public MonographAttributes() {
    }

    public MonographAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), MonographAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Авторы: " + this.authors);
        output.add("Название монографии: " + this.monographName);
        output.add("Наименование издания: " + this.editionName);
        output.add("Год: " + this.year);
        return output;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getMonographName() {
        return monographName;
    }

    public void setMonographName(String monographName) {
        this.monographName = monographName;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
        MonographAttributes.gson = gson;
    }
}
