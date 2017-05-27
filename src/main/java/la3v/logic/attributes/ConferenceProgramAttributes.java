package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 23.05.2017.
 * Программа конференции
 */
public class ConferenceProgramAttributes implements IAttributes {

    private String docType, process;
    private static Gson gson = new Gson();

    public ConferenceProgramAttributes() {
    }

    public ConferenceProgramAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ConferenceProgramAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        return output;
    }

    @Override
    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @Override
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
