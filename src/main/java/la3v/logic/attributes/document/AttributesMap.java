package la3v.logic.attributes.document;

import java.util.HashMap;

/**
 * Created by Marmonth on 20.05.2017.
 * Конструкторы дублируются на русском и английском!!!!
 */
public final class AttributesMap {
        private static HashMap<String, IAttributes> attributeMap;
        public AttributesMap() {
            attributeMap = new HashMap<String, IAttributes>();
            attributeMap.put("диплом", new DiplomaAttributes());
            attributeMap.put("diploma", new DiplomaAttributes());
            attributeMap.put("coursework", new CourseWorkAttributes());
            attributeMap.put("курсовая работа", new CourseWorkAttributes());
            attributeMap.put("specialtiesplan", new SpecialtiesPlanAttributes());
            attributeMap.put("учебный план специальности", new SpecialtiesPlanAttributes());
            attributeMap.put("workingplan", new WorkingPlanAttributes());
            attributeMap.put("рабочий учебный план", new WorkingPlanAttributes());
            attributeMap.put("studentslist", new StudentsListAttributes());
            attributeMap.put("список фамилий студентов", new StudentsListAttributes());
            attributeMap.put("progressjournal", new ProgressJournalAttributes());
            attributeMap.put("журнал текущей успеваемости", new ProgressJournalAttributes());
            attributeMap.put("conferencethesis", new ConferenceThesisAttributes());
            attributeMap.put("тезисы конференций", new ConferenceThesisAttributes());
            attributeMap.put("article", new ArticleAttributes());
            attributeMap.put("статья", new ArticleAttributes());
            attributeMap.put("monograph", new MonographAttributes());
            attributeMap.put("монография", new MonographAttributes());
            attributeMap.put("tutorial", new TutorialAttributes());
            attributeMap.put("учебное пособие", new TutorialAttributes());
            attributeMap.put("educationaledition", new EducationalEditionAttributes());
            attributeMap.put("учебное издание", new EducationalEditionAttributes());
            attributeMap.put("patent", new PatentAttributes());
            attributeMap.put("патент", new PatentAttributes());
            attributeMap.put("conferenceincoming", new ConferenceIncomingAttributes());
            attributeMap.put("проводимая конференция", new ConferenceIncomingAttributes());
            attributeMap.put("researchwork", new ResearchWorkAttributes());
            attributeMap.put("нир кафедры", new ResearchWorkAttributes());
            attributeMap.put("scientificseminarprotocol", new ScientificSeminarProtocolAttributes());
            attributeMap.put("протокол научного семинара", new ScientificSeminarProtocolAttributes());
            attributeMap.put("pilpitsessionprotocol", new PilpitSessionProtocolAttributes());
            attributeMap.put("протокол заседания кафедры", new PilpitSessionProtocolAttributes());
            attributeMap.put("protocolstatements", new ProtocolStatementsAttributes());
            attributeMap.put("выписка из протокола", new ProtocolStatementsAttributes());
            attributeMap.put("positioninstructions", new PositionInstructionsAttributes());
            attributeMap.put("должностные инструкции", new PositionInstructionsAttributes());
            attributeMap.put("lecturerform", new LecturerFormAttributes());
            attributeMap.put("анкета перподавателя", new LecturerFormAttributes());
            attributeMap.put("commonshedule", new CommonScheduleAttributes());
            attributeMap.put("штатное расписание", new CommonScheduleAttributes());
            attributeMap.put("schedule", new ScheduleAttributes());
            attributeMap.put("расписание", new ScheduleAttributes());
            attributeMap.put("lecturerplan", new LecturerPlanAttributes());
            attributeMap.put("индивидуальный план преподавателя", new LecturerPlanAttributes());
            attributeMap.put("traineeship", new TraineeshipAttributes());
            attributeMap.put("стажировка", new TraineeshipAttributes());
            attributeMap.put("методические указания для студентов заочного обучения", new DisciplineExtramuralInstructionsAttributes());
            attributeMap.put("disciplineextramuralinstructions", new DisciplineExtramuralInstructionsAttributes());
            attributeMap.put("методические указания к лабораторным работам", new DisciplineLabWorkInstructionsAttributes());
            attributeMap.put("disciplinelabworkinstructions", new DisciplineLabWorkInstructionsAttributes());
            attributeMap.put("методические указания к курсовому проектуя", new DisciplineCourseWorkInstructionsAttributes());
            attributeMap.put("disciplinecourseworkinstructions", new DisciplineCourseWorkInstructionsAttributes());
            attributeMap.put("конспект лекций", new DisciplineLectionConspectusAttributes());
            attributeMap.put("disciplinelectionconspectus", new DisciplineLectionConspectusAttributes());
            attributeMap.put("рабочая программа дисциплины", new DisciplineWorkingProgramAttributes());
            attributeMap.put("disciplineworkingprogram", new DisciplineWorkingProgramAttributes());
        }

        public static HashMap<String, IAttributes> getAttributeMap() {
            return attributeMap;
        }
}
