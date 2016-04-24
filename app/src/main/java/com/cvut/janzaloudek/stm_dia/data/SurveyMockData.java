package com.cvut.janzaloudek.stm_dia.data;

import com.cvut.janzaloudek.stm_dia.model.entity.Question;
import com.cvut.janzaloudek.stm_dia.model.entity.Survey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janzaloudek on 25/04/16.
 */
public class SurveyMockData {
    private static SurveyMockData ourInstance = new SurveyMockData();

    public static SurveyMockData getInstance() {
        return ourInstance;
    }

//    protected List surveys = new ArrayList<Survey>() {{
//        add(new Survey(
//                new ArrayList<Question>(){{
//                    add(new Question(1, "Hello"));
//                    add(new Question(1, "Hello2"));
//                }}
//        ))
//    }};

    private SurveyMockData() {

    }
}
