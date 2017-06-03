package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Патенты
 */
public class PatentAttributes implements IAttributes {

    private String name, number;
    private static Gson gson = new Gson();

    public PatentAttributes() {
    }

    public PatentAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), PatentAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Название: " + this.name);
        output.add("Номер: " + this.number);
        return output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        PatentAttributes.gson = gson;
    }
}
