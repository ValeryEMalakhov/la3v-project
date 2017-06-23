package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 01.06.2017.
 *
 * Неопределенный тип
 */
public class UndefinedAttributes implements IAttributes {

    private String process, docType;
    private static Gson gson = new Gson();

    public UndefinedAttributes() {
        this.process = "Не определено";
        this.docType = "Не определено";
    }

    @Override
    public UndefinedAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), UndefinedAttributes.class);
    }

    @Override
    public List<String> toListString() {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        return output;
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
        UndefinedAttributes.gson = gson;
    }
}
