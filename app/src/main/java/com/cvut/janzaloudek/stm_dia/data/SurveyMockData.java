package com.cvut.janzaloudek.stm_dia.data;

import com.cvut.janzaloudek.stm_dia.model.entity.Field;
import com.cvut.janzaloudek.stm_dia.model.entity.Question;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janzaloudek on 25/04/16.
 */
public class SurveyMockData {
    public static List<Field> fields = new ArrayList<Field>() {{
        add(new Field(1, "Benzinka", "Tohle je benzinka"));
        add(new Field(2, "Pekarna", "Pekarny jsou vsude v nasem okoli. Zapiste do deniku zkusenost s pekarnami ve vasem okoli."));
        add(new Field(3, "Supermarket", "V supermarketu se nakupuje spousta zajimavych veci. Meli byste to vyzkouset a neco si tam koupit."));
    }};

    public static List<Question> questions = new ArrayList<Question>(){{
        add(new Question(1, "Libilo se vam ovoce?"));
        add(new Question(2, "Libila se vam zelenina?"));
        add(new Question(3, "Bylo maso cerstve?"));
        add(new Question(4, "Jelita byla zalita kremovou omackou?"));
        add(new Question(5, "Kvalita obsluhy na stupnici 1-10?"));
        add(new Question(6, "Veprove koleno na smetane bylo uchazejici. Toto tvrzeni ohodnotte."));
        add(new Question(7, "Jelita byla zalita kremovou omackou?"));
        add(new Question(8, "Jelita byla zalita kremovou omackou?"));
    }};

    public static List<SurveyResponse> surveyResponses = new ArrayList<SurveyResponse>() {{
        add(new SurveyResponse());
        add(new SurveyResponse());
        add(new SurveyResponse());
        add(new SurveyResponse());
        add(new SurveyResponse());
        add(new SurveyResponse());
        add(new SurveyResponse());
        add(new SurveyResponse());
        add(new SurveyResponse());
    }};

    private static SurveyMockData ourInstance = new SurveyMockData();

    public static SurveyMockData getInstance() {
        return ourInstance;
    }

//    protected List surveys = new ArrayList<SurveyItem>() {{
//        add(new SurveyItem(
//                new ArrayList<Question>(){{
//                    add(new Question(1, "Hello"));
//                    add(new Question(1, "Hello2"));
//                }}
//        ))
//    }};

    private SurveyMockData() {

    }
}
