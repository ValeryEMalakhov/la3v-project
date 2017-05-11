package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Учебные издания
 */
public class EducationalEditionAttributes implements IAttributes {

    private String type, editionName, editionType;
    private String process, docType;
    private static Gson gson = new Gson();

    public EducationalEditionAttributes() {
    }

    public static EducationalEditionAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), EducationalEditionAttributes.class);
    }


    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Тип: " + this.type);
        output.add("Название издания: " + this.editionName);
        output.add("Тип издания: " + this.editionType);
        return output;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public String getEditionType() {
        return editionType;
    }

    public void setEditionType(String editionType) {
        this.editionType = editionType;
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
        EducationalEditionAttributes.gson = gson;
    }
}
