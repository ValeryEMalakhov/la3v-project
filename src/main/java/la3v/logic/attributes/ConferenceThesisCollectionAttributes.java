package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 23.05.2017.
 * Сборник тезисов
 */
public class ConferenceThesisCollectionAttributes implements IAttributes{

    private String docType, process;
    private static Gson gson = new Gson();

    public ConferenceThesisCollectionAttributes() {
    }

    public ConferenceThesisCollectionAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ConferenceThesisCollectionAttributes.class);
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
