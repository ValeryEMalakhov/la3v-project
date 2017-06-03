package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Стажировка
 */
public class TraineeshipAttributes implements IAttributes {

    private String lecturerName, dateStart, dateEnd;
    private String process, docType;
    private static Gson gson = new Gson();

    public TraineeshipAttributes() {
    }

    public TraineeshipAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), TraineeshipAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Преподаватель: " + this.lecturerName);
        output.add("Начало прохождения: " + this.dateStart);
        output.add("Конец прохождения: " + this.dateEnd);
        return output;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
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
        TraineeshipAttributes.gson = gson;
    }
}
