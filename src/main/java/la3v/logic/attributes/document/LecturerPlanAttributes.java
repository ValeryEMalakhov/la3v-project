package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Индивидуальный план преподавателя
 */
public class LecturerPlanAttributes implements IAttributes {

    private String lecturerName, position, studyYear;
    private static Gson gson = new Gson();

    public LecturerPlanAttributes() {
    }

    public LecturerPlanAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), LecturerPlanAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("ФИО: " + this.lecturerName);
        output.add("Должность: " + this.position);
        output.add("Учебный год: " + this.studyYear);
        return output;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        LecturerPlanAttributes.gson = gson;
    }
}
