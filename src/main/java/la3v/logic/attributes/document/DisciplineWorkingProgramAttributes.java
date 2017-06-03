package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 23.05.2017.
 * Рабочая программа дисциплины
 */
public class DisciplineWorkingProgramAttributes implements IAttributes {

    private String specialty, lecturer, studyYear;
    private static Gson gson = new Gson();

    public DisciplineWorkingProgramAttributes() {
    }

    public DisciplineWorkingProgramAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), DisciplineWorkingProgramAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
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

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        DisciplineWorkingProgramAttributes.gson = gson;
    }
}
