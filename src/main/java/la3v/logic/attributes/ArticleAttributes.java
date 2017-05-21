package la3v.logic.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marmonth on 07.05.2017.
 * Статьи
 */
public class ArticleAttributes implements IAttributes{

    private String authors, articleName, editionName, year, editionCategory, index;
    private String process, docType;
    private static Gson gson = new Gson();

    public ArticleAttributes() {
    }

    public ArticleAttributes getFromJson(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.toString(), ArticleAttributes.class);
    }

    @Override
    public List<String> toListString()
    {
        List<String> output = new ArrayList<>();
        output.add("Авторы: " + this.authors);
        output.add("Название доклада: " + this.articleName);
        output.add("Наименование издания: " + this.editionName);
        output.add("Год: " + this.year);
        output.add("Категория издания: " + this.editionCategory);
        output.add("Индексируемость: " + this.index);
        return output;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEditionCategory() {
        return editionCategory;
    }

    public void setEditionCategory(String editionCategory) {
        this.editionCategory = editionCategory;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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
        ArticleAttributes.gson = gson;
    }
}
