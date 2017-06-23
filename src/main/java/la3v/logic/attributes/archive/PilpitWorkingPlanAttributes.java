package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * План работы кафедры
 */
public class PilpitWorkingPlanAttributes implements IAttributes {

    private String year;
    private String process, docType;
    private static Gson gson = new Gson();

    public PilpitWorkingPlanAttributes() {
    }

    @Override
    public PilpitWorkingPlanAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), PilpitWorkingPlanAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Календарный год: " + this.year);
        return output;
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
        PilpitWorkingPlanAttributes.gson = gson;
    }
}
