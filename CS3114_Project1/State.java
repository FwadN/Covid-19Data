// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Foad Nachabe (foadn)

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

/**
 * This is the state class that will hold all the information concerning the
 * states
 * 
 * @author Foad Nachabe (foadn) and Jack Layfield (jackml)
 * @version 9/12/2020
 */
public class State {
    private int date;
    private String stateName;
    private int posCases;
    private int negCases;
    private int hospital;
    private int onVent;
    private int cumVent;
    private int recovered;
    private String grade;
    private int deaths;


    /**
     * The constructor of the state class
     * 
     * @param date
     *            the date
     * @param stateName
     *            the state name
     * @param posCases
     *            the amount of positive cases
     * @param negCases
     *            the amount of negative cases
     * @param hospital
     *            the amount in the hospital
     * @param onVent
     *            the amount current on a vent
     * @param cumVent
     *            the amount cumulatively on a vent
     * @param recovered
     *            the amount recovered
     * @param grade
     *            the grade quality for the data
     * @param deaths
     *            the amount of deaths
     */
    public State(
        int date,
        String stateName,
        int posCases,
        int negCases,
        int hospital,
        int onVent,
        int cumVent,
        int recovered,
        String grade,
        int deaths) {
        this.date = date;
        this.stateName = stateName;
        this.posCases = posCases;
        this.negCases = negCases;
        this.hospital = hospital;
        this.onVent = onVent;
        this.cumVent = cumVent;
        this.recovered = recovered;
        this.grade = grade;
        this.deaths = deaths;
    }


    /**
     * Getter for the date of the State
     * 
     * @return the date
     */
    public int getDate() {
        return date;
    }


    /**
     * Getter for the StateName
     * 
     * @return the state name
     */
    public String getStateName() {
        return stateName;
    }


    /**
     * Getter for the amount of positive cases
     * 
     * @return the positive cases
     */
    public int getPositiveCases() {
        return posCases;
    }


    /**
     * Getter for the amount of negative cases
     * 
     * @return the negative cases
     */
    public int getNegativeCases() {
        return negCases;
    }


    /**
     * Getter for the amount of people in the hospital currently
     * 
     * @return the amount in a hospital
     */
    public int getInHospital() {
        return hospital;
    }


    /**
     * Getter for the amount of people on a vent
     * 
     * @return the amount on a vent
     */
    public int getOnVentilator() {
        return onVent;
    }


    /**
     * Getter for the amount of cumulative vents
     * 
     * @return cumulative vents
     */
    public int getCumulativeVentilator() {
        return cumVent;
    }


    /**
     * Getter for the amount of people that have recovered
     * 
     * @return recovered people
     */
    public int getRecovered() {
        return recovered;
    }


    /**
     * Getter for the grade quality of the data
     * 
     * @return the grade quality
     */
    public String getGradeQuality() {
        return grade;
    }


    /**
     * Getter for the amount of deaths
     * 
     * @return amount of deaths
     */
    public int getDeaths() {
        return deaths;
    }


    /**
     * This will change the amount of positive cases
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of positive cases
     */
    public int setPosCases(int newCases) {
        posCases = newCases;
        return posCases;
    }


    /**
     * This will change the amount negative cases
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of negative cases
     */
    public int setNegCases(int newCases) {
        negCases = newCases;
        return negCases;
    }


    /**
     * This will change the amount of people in a hospital
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of hospital cases
     */
    public int setHosCases(int newCases) {
        hospital = newCases;
        return hospital;
    }


    /**
     * This will change the amount of deaths
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of deaths
     */
    public int setDeathCases(int newCases) {
        deaths = newCases;
        return deaths;
    }


    /**
     * This will change the amount of on Vent
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of vents
     */
    public int setOnVent(int newCases) {
        onVent = newCases;
        return onVent;
    }


    /**
     * This will change the amount of cumVent
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of cumul vents
     */
    public int setCumVent(int newCases) {
        cumVent = newCases;
        return cumVent;
    }


    /**
     * This will change the amount of recovered
     * 
     * @param newCases
     *            the cases being added
     * @return the new amount of recovered cases;
     */
    public int setRecCases(int newCases) {
        recovered = newCases;
        return recovered;
    }


    /**
     * This will make a copy of the state class
     * 
     * @return the same state class with all the data
     */
    public State copy() {
        State newState = new State(date, stateName, posCases, negCases,
            hospital, onVent, cumVent, recovered, grade, deaths);
        return newState;
    }
}
