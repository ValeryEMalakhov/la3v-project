package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Тезисы конференций
 */
public class ConferenceThesisAttributes implements IAttributes {

    private String authors, supervisor, reportName;
    private static Gson gson = new Gson();

    public ConferenceThesisAttributes() {
    }

    public ConferenceThesisAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ConferenceThesisAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Научный руководитель: " + this.supervisor);
        output.add("Название доклада: " + this.reportName);
        return output;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        ConferenceThesisAttributes.gson = gson;
    }
}
