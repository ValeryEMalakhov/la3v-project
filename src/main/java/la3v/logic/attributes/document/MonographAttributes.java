package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Монографии
 */
public class MonographAttributes implements IAttributes {

    private String monographName, editionName;
    private static Gson gson = new Gson();

    public MonographAttributes() {
    }

    public MonographAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), MonographAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Название монографии: " + this.monographName);
        output.add("Наименование издания: " + this.editionName);
        return output;
    }

    public String getMonographName() {
        return monographName;
    }

    public void setMonographName(String monographName) {
        this.monographName = monographName;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        MonographAttributes.gson = gson;
    }
}
