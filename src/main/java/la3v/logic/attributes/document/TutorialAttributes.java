package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Учебные пособия
 */
public class TutorialAttributes implements IAttributes {

    private String topic;
    private static Gson gson = new Gson();

    public TutorialAttributes() {
    }

    public TutorialAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), TutorialAttributes.class);
    }

    @Override
    public List<String> toListString() {
        List<String> output = new ArrayList<>();
        output.add("Тема: " + this.topic);
        return output;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        TutorialAttributes.gson = gson;
    }
}
