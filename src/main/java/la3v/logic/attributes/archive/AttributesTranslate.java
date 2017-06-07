package la3v.logic.attributes.archive;

import java.util.HashMap;

/**
 * Created by Marmonth on 25.05.2017.
 */
public final class AttributesTranslate {
    private static HashMap<String, String> attributeRusEng;
    private static HashMap<String, String> attributeEngRus;

    public AttributesTranslate() {
        //RUS-ENG
        attributeRusEng = new HashMap<String, String>();

        //Учебный процесс
        attributeRusEng.put("диплом", "diploma");
        attributeRusEng.put("курсовая работа", "coursework");
        attributeRusEng.put("учебный план специальности", "specialtiesplan");
        attributeRusEng.put("рабочий учебный план", "workingplan");
        attributeRusEng.put("рабочая программа дисциплины", "disciplineworkingprogram");
        attributeRusEng.put("конспект лекций", "disciplinelectionconspectus");
        attributeRusEng.put("методические указания для студентов заочного обучения", "disciplineextramuralinstructions");
        attributeRusEng.put("методические указания к лабораторным работам", "disciplinelabworkinstructions");
        attributeRusEng.put("методические указания к курсовому проекту", "disciplinecourseworkinstructions");
        attributeRusEng.put("список фамилий студентов", "studentslist");
        attributeRusEng.put("журнал текущей успеваемости", "progressjournal");

        //Научный процесс
        attributeRusEng.put("тезисы конференций", "conferencethesis");
        attributeRusEng.put("статья", "article");
        attributeRusEng.put("монография", "monograph");
        attributeRusEng.put("учебное пособие", "tutorial");
        attributeRusEng.put("учебное издание", "educationaledition");
        attributeRusEng.put("патент", "patent");
        attributeRusEng.put("проводимая конференция", "conferenceincoming");
        attributeRusEng.put("нир кафедры", "researchwork");
        attributeRusEng.put("заявка на нир", "researchworkrequest");
        attributeRusEng.put("отчёт о научной работе кафедры", "researchworkreport");
        attributeRusEng.put("протокол научного семинара кафедры", "scientificseminarprotocol");
        attributeRusEng.put("программа конференции", "conferenceprogram");
        attributeRusEng.put("сборник тезисов", "conferencethesiscollection");

        //Организационный процесс
        attributeRusEng.put("протокол заседания кафедры", "pilpitsessionprotocol");
        attributeRusEng.put("выписка из протокола", "protocolstatements");
        attributeRusEng.put("должностная инструкция", "positioninstructions");
        attributeRusEng.put("анкета перподавателя", "lecturerform");
        attributeRusEng.put("штатное расписание", "commonshedule");
        attributeRusEng.put("план работы кафедры", "pilpitworkingplan");
        attributeRusEng.put("отчёт работы кафедры", "pilpitworkingreport");
        attributeRusEng.put("расписание", "schedule");
        attributeRusEng.put("индивидуальный план преподавателя", "lecturerplan");
        attributeRusEng.put("стажировка", "traineeship");
        attributeRusEng.put("объявление", "advert");
        attributeRusEng.put("журнал о выполнении аудиторной нагрузки", "classroomloadjournal");

        attributeRusEng.put("не определено", "undefined");

        //ENG-RUS
        attributeEngRus = new HashMap<String, String>();

        //Учебный процесс
        attributeEngRus.put("diploma", "диплом");
        attributeEngRus.put("coursework", "курсовая работа");
        attributeEngRus.put("specialtiesplan", "учебный план специальности");
        attributeEngRus.put("workingplan", "рабочий учебный план");
        attributeEngRus.put("disciplineworkingprogram", "рабочая программа дисциплины");
        attributeEngRus.put("disciplinelectionconspectus", "конспект лекций");
        attributeEngRus.put("disciplineextramuralinstructions", "методические указания для студентов заочного обучения");
        attributeEngRus.put("disciplinelabworkinstructions", "методические указания к лабораторным работам");
        attributeEngRus.put("disciplinecourseworkinstructions", "методические указания к курсовому проекту");
        attributeEngRus.put("studentslist", "список фамилий студентов");
        attributeEngRus.put("progressjournal", "журнал текущей успеваемости");

        //Научный процесс
        attributeEngRus.put("conferencethesis", "тезисы конференций");
        attributeEngRus.put("article", "статья");
        attributeEngRus.put("monograph", "монография");
        attributeEngRus.put("tutorial", "учебное пособие");
        attributeEngRus.put("educationaledition", "учебное издание");
        attributeEngRus.put("patent", "патент");
        attributeEngRus.put("conferenceincoming", "проводимая конференция");
        attributeEngRus.put("researchwork", "нир кафедры");
        attributeEngRus.put("researchworkrequest", "заявка на нир");
        attributeEngRus.put("researchworkreport", "отчёт о научной работе кафедры");
        attributeEngRus.put("scientificseminarprotocol", "протокол научного семинара кафедры");
        attributeEngRus.put("conferenceprogram", "программа конференции");
        attributeEngRus.put("conferencethesiscollection", "сборник тезисов");

        //Организационный процесс
        attributeEngRus.put("pilpitsessionprotocol", "протокол заседания кафедры");
        attributeEngRus.put("protocolstatements", "выписка из протокола");
        attributeEngRus.put("positioninstructions", "должностная инструкция");
        attributeEngRus.put("lecturerform", "анкета перподавателя");
        attributeEngRus.put("commonshedule", "штатное расписание");
        attributeEngRus.put("pilpitworkingplan", "план работы кафедры");
        attributeEngRus.put("pilpitworkingreport", "отчёт работы кафедры");
        attributeEngRus.put("schedule", "расписание");
        attributeEngRus.put("lecturerplan", "индивидуальный план преподавателя");
        attributeEngRus.put("traineeship", "стажировка");
        attributeEngRus.put("advert", "объявление");
        attributeEngRus.put("classroomloadjournal", "журнал о выполнении аудиторной нагрузки");

        attributeEngRus.put("undefined", "не определено");

    }

    public static HashMap<String, String> getAttributesRusEng() {
        return attributeRusEng;
    }

    public static HashMap<String, String> getAttributesEngRus() {
        return attributeEngRus;
    }
}
