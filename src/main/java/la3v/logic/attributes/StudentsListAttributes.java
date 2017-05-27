package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 05.05.2017.
 * Список фамилий студентов
 */
public class StudentsListAttributes implements IAttributes {

    private String studyYear, specialty, course, group, docType, process;
    private static Gson gson = new Gson();

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Учебный год: " + this.studyYear);
        output.add("Специальность: " + this.specialty);
        output.add("Курс: " + this.course);
        output.add("Группа: " + this.group);
        return output;
    }

    public StudentsListAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), StudentsListAttributes.class);
    }

    public StudentsListAttributes() {
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
