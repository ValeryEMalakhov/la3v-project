package la3v.logic.attributes;

import java.util.HashMap;

/**
 * Created by Marmonth on 25.05.2017.
 */
public final class AttributesRusEng{
    private static HashMap<String, String> attributeMap;
    public AttributesRusEng() {
        attributeMap = new HashMap<String, String>();
        attributeMap.put("диплом", "diploma");
        attributeMap.put("курсовая работа", "coursework");
        attributeMap.put("учебный план специальности", "specialtiesplan");
        attributeMap.put("рабочий учебный план", "workingplan");
        attributeMap.put("список фамилий студентов", "studentslist");
        attributeMap.put("журнал текущей успеваемости", "progressjournal");
        attributeMap.put("тезис конференций", "conferencethesis");
        attributeMap.put("статья", "article");
        attributeMap.put("монография", "monograph");
        attributeMap.put("учебное пособие", "tutorial");
        attributeMap.put("учебное издание", "educationaledition");
        attributeMap.put("патент", "patent");
        attributeMap.put("проводимая конференция", "conferenceincoming");
        attributeMap.put("нир кафедры", "researchwork");
        attributeMap.put("отчёт нир", "researchworkreport");
        attributeMap.put("протокол научного семинара", "scientificseminarprotocol");
        attributeMap.put("протокол заседания кафедры", "pilpitsessionprotocol");
        attributeMap.put("выписка из протокола", "protocolstatements");
        attributeMap.put("должностные инструкции", "positioninstructions");
        attributeMap.put("анкета перподавателя", "lecturerform");
        attributeMap.put("штатное расписание", "commonshedule");
        attributeMap.put("план работы кафедры", "pilpitworkingplan");
        attributeMap.put("отчёт работы кафедры", "pilpitworkingreport");
        attributeMap.put("расписание", "schedule");
        attributeMap.put("индивидуальный план преподавателя", "lecturerplan");
        attributeMap.put("стажировка", "traineeship");
        attributeMap.put("объявление", "advert");
        attributeMap.put("методические указания для студентов заочного обучения", "disciplineextramuralinstructions");
        attributeMap.put("методические указания к лабораторным работам", "disciplinelabworkinstructions");
        attributeMap.put("методические указания к курсовому проектуя", "disciplinecourseworkinstructions");
        attributeMap.put("конспект лекций", "disciplinelectionconspectus");
        attributeMap.put("рабочая программа дисциплины", "disciplineworkingprogram");
        attributeMap.put("заявка на нир", "researchworkrequest");
        attributeMap.put("программа конференции", "conferenceprogram");
        attributeMap.put("сборник тезисов", "conferencethesiscollection");
    }

    public static HashMap<String, String> getAttributesRusEng() {
        return attributeMap;
    }
}
