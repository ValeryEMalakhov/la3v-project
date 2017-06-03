package la3v.logic.attributes.document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Выписки из протоколов
 */
public class ProtocolStatementsAttributes implements IAttributes {

    private String data, protocolNumber, statementsPurpose;
    private static Gson gson = new Gson();

    public ProtocolStatementsAttributes() {
    }

    public ProtocolStatementsAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ProtocolStatementsAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Дата: " + this.data);
        output.add("Номер: " + this.protocolNumber);
        output.add("Назначение выписки: " + this.statementsPurpose);
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

    public String getStatementsPurpose() {
        return statementsPurpose;
    }

    public void setStatementsPurpose(String statementsPurpose) {
        this.statementsPurpose = statementsPurpose;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson) {
        ProtocolStatementsAttributes.gson = gson;
    }
}
