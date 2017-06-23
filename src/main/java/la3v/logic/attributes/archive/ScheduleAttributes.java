package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Расписание
 */
public class ScheduleAttributes implements IAttributes {

    private String semester, lecturer, dayOfWeek, week, classes;
    private String process, docType;
    private static Gson gson = new Gson();

    public ScheduleAttributes() {
    }

    @Override
    public ScheduleAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ScheduleAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Семестр: " + this.semester);
        output.add("Преподаватель: " + this.lecturer);
        output.add("День недели: " + this.dayOfWeek);
        output.add("Неделя: " + this.week);
        output.add("Пара: " + this.classes);
        return output;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
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
        ScheduleAttributes.gson = gson;
    }
}
