// On my honor:

//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction. Foad Nachabe and Jack Layfield

import student.TestCase;

/**
 * This is the test class for all the methods in state
 * 
 * @author Foad Nachabe (foadn)
 * @version 9/21/2020
 */
public class StateTest extends TestCase {
    private State state1;


    /**
     * This will create the setup for the test class
     */
    public void setUp() {
        int date = 20200818;
        String stateName = "ca";
        int posCases = 110;
        int negCases = 50;
        int hospital = 123;
        int onVent = 11;
        int cumVent = 19;
        int recovered = 65;
        String grade = "A+";
        int deaths = 15;
        state1 = new State(date, stateName, posCases, negCases, hospital,
            onVent, cumVent, recovered, grade, deaths);
    }


    /**
     * THis will test the get date method
     */
    public void testGetDate() {
        assertEquals(state1.getDate(), 20200818);
    }


    /**
     * This will test the get state name method
     */
    public void testGetStateName() {
        assertEquals(state1.getStateName(), "ca");
    }


    /**
     * This will test the get positive cases method
     */
    public void testGetPositiveCases() {
        assertEquals(state1.getPositiveCases(), 110);
    }


    /**
     * This will test the get negative cases method
     */
    public void testGetNegativeCases() {
        assertEquals(state1.getNegativeCases(), 50);
    }


    /**
     * This will test the get in hospital method
     */
    public void testGetInHospital() {
        assertEquals(state1.getInHospital(), 123);
    }


    /**
     * This will test the get on vent method
     */
    public void testGetOnVentilator() {
        assertEquals(state1.getOnVentilator(), 11);
    }


    /**
     * This will test the get cumulative vent method
     */
    public void testGetCumulativeVentilator() {
        assertEquals(state1.getCumulativeVentilator(), 19);
    }


    /**
     * This will test the get recovered method
     */
    public void testGetRecovered() {
        assertEquals(state1.getRecovered(), 65);
    }


    /**
     * This will test the get grade quality method
     */
    public void testGetGradeQuality() {
        assertEquals(state1.getGradeQuality(), "A+");
    }


    /**
     * This will test the get deaths method
     */
    public void testGetDeaths() {
        assertEquals(state1.getDeaths(), 15);
    }


    /**
     * This will test the set positive cases method
     */
    public void testSetPosCases() {
        assertEquals(state1.getPositiveCases(), 110);
        state1.setPosCases(15);
        assertEquals(state1.getPositiveCases(), 15);
    }


    /**
     * This will test the set negative cases method
     */
    public void testSetNegCases() {
        assertEquals(state1.getNegativeCases(), 50);
        state1.setNegCases(100);
        assertEquals(state1.getNegativeCases(), 100);
    }


    /**
     * This will test the set hospital cases method
     */
    public void testSetHosCases() {
        assertEquals(state1.getInHospital(), 123);
        state1.setHosCases(20);
        assertEquals(state1.getInHospital(), 20);
    }


    /**
     * This will test the set deaths cases methods
     */
    public void testSetDeathCases() {
        assertEquals(state1.getDeaths(), 15);
        state1.setDeathCases(25);
        assertEquals(state1.getDeaths(), 25);
    }


    /**
     * This will test the set recovered cases
     */
    public void testSetRecCases() {
        assertEquals(state1.getRecovered(), 65);
        state1.setRecCases(11);
        assertEquals(state1.getRecovered(), 11);
    }


    /**
     * This will test the set cumulative cases
     */
    public void testSetCumVent() {
        assertEquals(state1.getCumulativeVentilator(), 19);
        state1.setCumVent(10);
        assertEquals(state1.getCumulativeVentilator(), 10);
    }


    /**
     * This will test the on vent cases
     */
    public void testSetOnVent() {
        assertEquals(state1.getOnVentilator(), 11);
        state1.setOnVent(10);
        assertEquals(state1.getOnVentilator(), 10);
    }


    /**
     * This will tst the copy method
     */
    public void testCopy() {
        State state2 = null;
        state2 = state1.copy();
        assertEquals(state2.getPositiveCases(), state1.getPositiveCases());
    }
}
