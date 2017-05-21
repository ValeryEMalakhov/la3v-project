package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Протоколы научных семинаров кафедры
 */
public class ScientificSeminarProtocolAttributes implements IAttributes {

    private String protocolNumber, data;
    private String process, docType;
    private static Gson gson = new Gson();

    public ScientificSeminarProtocolAttributes() {
    }

    public ScientificSeminarProtocolAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ScientificSeminarProtocolAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Номер протокола: " + this.protocolNumber);
        output.add("Дата: " + this.data);
        return output;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        ScientificSeminarProtocolAttributes.gson = gson;
    }
}
