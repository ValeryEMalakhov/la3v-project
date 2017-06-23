package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 01.06.2017.
 * Журнал о выполнении аудиторной нагрузки
 */
public class ClassroomLoadJournalAttributes implements IAttributes {

    private String process, docType;
    private static Gson gson = new Gson();

    public ClassroomLoadJournalAttributes() {
    }

    @Override
    public ClassroomLoadJournalAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ClassroomLoadJournalAttributes.class);
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
        ClassroomLoadJournalAttributes.gson = gson;
    }
}
