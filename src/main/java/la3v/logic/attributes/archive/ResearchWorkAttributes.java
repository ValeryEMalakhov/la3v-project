package la3v.logic.attributes.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * НИР кафедры
 */
public class ResearchWorkAttributes implements IAttributes {

    private String name, number, startDate, endDate, plan, stagePlan, report, finalReport;
    private String process, docType;
    private static Gson gson = new Gson();

    public ResearchWorkAttributes() {
    }

    public ResearchWorkAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ResearchWorkAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Процесс: " + this.process);
        output.add("Тип документа: " + this.docType);
        output.add("__________________________________");
        output.add("Название: " + this.name);
        output.add("Номер: " + this.number);
        output.add("Начало НИР: " + this.startDate);
        output.add("Конец НИР: " + this.endDate);
        output.add("План: " + this.plan);
        output.add("Планы этапов: " + this.stagePlan);
        output.add("Аннотированные отчёты о выполнении этапов НИР: " + this.report);
        output.add("Итоговый очтёт: " + this.finalReport);
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getStagePlan() {
        return stagePlan;
    }

    public void setStagePlan(String stagePlan) {
        this.stagePlan = stagePlan;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getFinalReport() {
        return finalReport;
    }

    public void setFinalReport(String finalReport) {
        this.finalReport = finalReport;
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
        ResearchWorkAttributes.gson = gson;
    }
}
