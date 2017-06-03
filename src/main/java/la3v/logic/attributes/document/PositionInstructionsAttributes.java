package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Должностные инструкции
 */
public class PositionInstructionsAttributes implements IAttributes {

    private String position, lecturerName;
    private static Gson gson = new Gson();

    public PositionInstructionsAttributes() {
    }

    public PositionInstructionsAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), PositionInstructionsAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Должность: " + this.position);
        output.add("ФИО преподавателя: " + this.lecturerName);
        return output;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        PositionInstructionsAttributes.gson = gson;
    }
}
