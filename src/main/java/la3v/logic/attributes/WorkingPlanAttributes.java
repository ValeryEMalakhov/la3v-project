package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Рабочие учебные планы
 */
public class WorkingPlanAttributes implements IAttributes {

    private String studyYear, specialtyName, level, studyForm, pilpitName;
    private String docType, process;
    private static Gson gson = new Gson();

    public WorkingPlanAttributes() {
    }

    public WorkingPlanAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), WorkingPlanAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Учебный год: " + this.studyYear);
        output.add("Название специальности: " + this.specialtyName);
        output.add("Уровень: " + this.level);
        output.add("Форма обучения: " + this.studyForm);
        output.add("Кафедра: " + this.pilpitName);
        return output;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(String studyForm) {
        this.studyForm = studyForm;
    }

    public String getPilpitName() {
        return pilpitName;
    }

    public void setPilpitName(String pilpitName) {
        this.pilpitName = pilpitName;
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

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        WorkingPlanAttributes.gson = gson;
    }
}
