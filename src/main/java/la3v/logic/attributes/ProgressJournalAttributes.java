package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Журнал текущей успеваемости
 */
public class ProgressJournalAttributes implements IAttributes {

    private String discipline, specialty, lecturer, group, semester;
    private String process, docType;
    private static Gson gson = new Gson();

    public ProgressJournalAttributes() {
    }

    public static ProgressJournalAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ProgressJournalAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Дисциплина: " + this.discipline);
        output.add("Специальность: " + this.specialty);
        output.add("Преподаватель: " + this.lecturer);
        output.add("Группа: " + this.group);
        output.add("Семестр: " + this.semester);
        return output;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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
        ProgressJournalAttributes.gson = gson;
    }
}
