package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Штатное расписание
 */
public class CommonScheduleAttributes implements IAttributes {

    private String studyYear;
    private static Gson gson = new Gson();

    public CommonScheduleAttributes() {
    }

    public CommonScheduleAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), CommonScheduleAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Учебный год: " + this.studyYear);
        return output;
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
        CommonScheduleAttributes.gson = gson;
    }
}
