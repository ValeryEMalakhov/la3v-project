package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 23.05.2017.
 * Методические указания к курсовому проекту
 */
public class DisciplineCourseWorkInstructionsAttributes implements IAttributes {

    private String specialty, lecturer, studyYear;
    private String process, docType;
    private static Gson gson = new Gson();

    public DisciplineCourseWorkInstructionsAttributes() {
    }

    @Override
    public DisciplineCourseWorkInstructionsAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), DisciplineCourseWorkInstructionsAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Специальность: " + this.specialty);
        output.add("Преподаватель: " + this.lecturer);
        output.add("Учебный год: " + this.studyYear);
        return output;
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

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
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
}
