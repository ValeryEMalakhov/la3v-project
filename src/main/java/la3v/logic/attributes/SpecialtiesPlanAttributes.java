package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Учебные планы специальностей
 */
public class SpecialtiesPlanAttributes implements IAttributes {

    private String approvalYear, specialtyName, level, studyForm;
    private String process, docType;
    private static Gson gson = new Gson();

    public SpecialtiesPlanAttributes() {
    }

    public static SpecialtiesPlanAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), SpecialtiesPlanAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Год утверждения: " + this.approvalYear);
        output.add("Название специальности: " + this.specialtyName);
        output.add("Уровень: " + this.level);
        output.add("Форма обучения: " + this.studyForm);
        return output;
    }

    public String getApprovalYear() {
        return approvalYear;
    }

    public void setApprovalYear(String approvalYear) {
        this.approvalYear = approvalYear;
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
        SpecialtiesPlanAttributes.gson = gson;
    }
}
