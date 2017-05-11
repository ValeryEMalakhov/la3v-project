package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Протоколы заседаний кафедры
 */
public class PilpitSessionProtocolAttributes implements IAttributes {

    private String data, protocolNumber;
    private String process, docType;
    private static Gson gson = new Gson();

    public PilpitSessionProtocolAttributes() {
    }

    public static PilpitSessionProtocolAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), PilpitSessionProtocolAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Дата: " + this.data);
        output.add("Номер: " + this.protocolNumber);
        return output;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
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
        PilpitSessionProtocolAttributes.gson = gson;
    }
}
