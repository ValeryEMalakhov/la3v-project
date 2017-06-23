package la3v.logic.attributes.archive;

import java.util.HashMap;

/**
 * Created by Marmonth on 07.06.2017.
 */
public final class ProcessTypeTranslate {
    private static HashMap<String, String> processRusEng;
    private static HashMap<String, String> processEngRus;

    public ProcessTypeTranslate() {
        //RUS-ENG
        processRusEng = new HashMap<String, String>();

        processRusEng.put("не определено", "undefined");
        processRusEng.put("учебный процесс", "educational");
        processRusEng.put("научный процесс", "scientific");
        processRusEng.put("организационный процесс", "organizational");
        processRusEng.put("мат обеспечение", "mat_support");

        //ENG-RUS
        processEngRus = new HashMap<String, String>();

        processEngRus.put("undefined", "Не определено");
        processEngRus.put("educational", "Учебный процесс");
        processEngRus.put("scientific", "Научный процесс");
        processEngRus.put("organizational", "Организационный процесс");
        processEngRus.put("mat_support", "Мат обеспечение");
    }

    public static HashMap<String, String> getProcessRusEng() {
        return processRusEng;
    }

    public static HashMap<String, String> getProcessEngRus() {
        return processEngRus;
    }
}
