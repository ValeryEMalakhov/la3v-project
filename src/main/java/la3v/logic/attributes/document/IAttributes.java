package la3v.logic.attributes.document;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by Marmonth on 05.05.2017.
 */
public interface IAttributes {

    List<String> toListString();
    IAttributes getFromJson(JsonObject jsonObject);
}
