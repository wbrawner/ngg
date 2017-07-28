package com.wbrawner.numberguess;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by billy on 7/28/2017.
 */

public class NumberGuessUnitTest {
    private static NumberGuess ng;

    @BeforeClass
    public static void setUp() throws Exception {
        ng = new NumberGuess();
        ng.setAnswer();
    }

    @Test
    public void setAnswer() throws Exception {
        boolean result = 0 < ng.getAnswer() && ng.getAnswer() < 101;
        assertTrue("Generated number is larger than 0 and less than 101", result);
    }

    @Test
    public void setAnswer1() throws Exception {
        int previous = ng.getAnswer();
        ng.setAnswer(74);
        assertNotEquals("setAnswer with parameter", previous, ng.getAnswer());
        ng.setAnswer(previous);
    }

    @Test
    public void getAnswer() throws Exception {
        int previous = ng.getAnswer();
        ng.setAnswer(23);
        assertEquals("getAnswer", 23, ng.getAnswer());
        ng.setAnswer(previous);
    }

    @Test
    public void checkAnswer() throws Exception {
        Map tooBig = ng.checkAnswer(ng.getAnswer() + 1);
        assertTrue("'Too big' response map contains code key", tooBig.containsKey("code"));
        assertEquals("'Too big' response code equal 'too_big'", "too_big", tooBig.get("code"));

        Map tooSmall = ng.checkAnswer(ng.getAnswer() - 1);
        assertTrue("'Too small' response map contains code key", tooSmall.containsKey("code"));
        assertEquals("'Too small' response code equals 'too_small'", "too_small", tooSmall.get("code"));

        Map correct = ng.checkAnswer(ng.getAnswer());
        assertTrue("'Correct' response map contains code key", correct.containsKey("code"));
        assertEquals("'Correct' response code equal 'correct'", "correct", correct.get("code"));
    }
}
