package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Анкеты преподавателей
 */
public class LecturerFormAttributes implements IAttributes {

    private String lecturerName, dateForming;
    private static Gson gson = new Gson();

    public LecturerFormAttributes() {
    }

    public LecturerFormAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), LecturerFormAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("ФИО преподавателя: " + this.lecturerName);
        output.add("Дата формирования анкеты: " + this.dateForming);
        return output;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getDateForming() {
        return dateForming;
    }

    public void setDateForming(String dateForming) {
        this.dateForming = dateForming;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        LecturerFormAttributes.gson = gson;
    }
}
