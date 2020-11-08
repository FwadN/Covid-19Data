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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class will output the correct information in a certain format so that
 * the data is easy to read
 * 
 * @author Foad Nachabe (foadn) and Jack Layfield (jackml)
 * @version 9/12/2020
 */
public class Output {
    private ArrayList<State> list;
    private String command;
    private Output outputA;
    private int prevListSize = 0;


    /**
     * The Constructor for the output class that will print all the information
     * that is in the excel file
     * 
     * @param listA
     *            the state list that has the information being passed
     * @param commandA
     *            the exact command being currently used
     */
    public Output(ArrayList<State> listA, String commandA) {
        this.list = listA;
        this.command = commandA;
    }


    /**
     * This is the getter for the list
     * 
     * @return the list
     */
    public ArrayList<State> getList() {
        return list;
    }


    /**
     * This is the method for the command dumpData writes it out to text file
     * called Dumpdata1.txt
     * 
     * @param file
     *            the file being used in order to dump the data
     * @throws IOException
     *             if the reader has nothing to read it throws an IOexception
     */
    public void dumpData(String file) throws IOException {
        outputA = new Output(list, command);
        ArrayList<State> list1 = new ArrayList<State>();
        for (State p : list1) {
            list1.add(p.copy());
        }
        // Creates the items needed in order to print to another file
        list1 = outputA.fixNum();
        File file1 = new File("Dumpdata1.txt");
        FileWriter fw = new FileWriter(file1);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(
            "date      state positive negative hospital onVentilatorCurrently "
                + "onVentilatorCumulative recovered dataQualityGrade death");
        for (int j = 0; j < list1.size(); j++) {
            int date = list1.get(j).getDate();
            String state = list1.get(j).getStateName().toUpperCase();
            int pos = list1.get(j).getPositiveCases();
            int neg = list1.get(j).getNegativeCases();
            int hos = list1.get(j).getInHospital();
            int onVent = list1.get(j).getOnVentilator();
            int cumVent = list1.get(j).getCumulativeVentilator();
            int rec = list1.get(j).getRecovered();
            String gra = list1.get(j).getGradeQuality();
            int dea = list1.get(j).getDeaths();
            pw.printf("%-9d %-5s %-8d %-8d %-8d %-21d %-22d %-9d %-16s %d\n",
                date, state, pos, neg, hos, onVent, cumVent, rec, gra, dea);
        }
        fw.close();
        pw.close();
        System.out.printf("%d records saved in the %s data file\n", list1
            .size(), file);
    }


    /**
     * This will search the data by the date in order to print out the data
     * 
     * @param date
     *            the date being searched by
     */
    public void searchDate(String date) {
        outputA = new Output(list, command);
        ArrayList<State> list1 = new ArrayList<State>();
        for (State p : list) {
            list1.add(p.copy());
        }
        list1 = outputA.fixNum();
        // Make sure the
        String someDate = date.replaceAll("/", "");
        int newDate = 0;
        try {
            if (someDate.length() != 8) {
                System.out.println("Discard invalid command name");
            }
            else {
                newDate = Integer.parseInt(someDate);

                int records = 0;
                for (int i = 0; i < list1.size(); i++) {
                    int date1 = outputA.fixDate(list1.get(i).getDate());
                    if (newDate == date1) {
                        records++;
                    }
                }
                if (records == 0) {
                    System.out.printf("There are no records on %s\n", date);
                }
                else {
                    System.out.printf("There are %d records on %s\n", records,
                        date);
                    System.out.printf(
                        "state positive  negative    hospitalized   "
                            + "onVentilatorCurrently    "
                            + "onVentilatorCumulative   recovered   "
                            + "dataQualityGrade   death\n");
                    ArrayList<State> stateList = new ArrayList<State>();
                    for (State p : list1) {
                        if (newDate == outputA.fixDate(p.getDate())) {
                            stateList.add(p.copy());
                        }
                    }
                    stateList = outputA.sortListState(stateList);
                    for (int j = 0; j < stateList.size(); j++) {
                        String stateName = stateList.get(j).getStateName()
                            .toUpperCase();
                        int pos = stateList.get(j).getPositiveCases();
                        int neg = stateList.get(j).getNegativeCases();
                        int hos = stateList.get(j).getInHospital();
                        int onVent = stateList.get(j).getOnVentilator();
                        int cumVent = stateList.get(j)
                            .getCumulativeVentilator();
                        int rec = stateList.get(j).getRecovered();
                        String gra = stateList.get(j).getGradeQuality();
                        int dea = stateList.get(j).getDeaths();
                        System.out.printf(
                            "%-5s %,-9d %,-11d %,-14d %,-24d %-24d "
                                + "%,-11d %-18s " + "%,d\n", stateName, pos,
                            neg, hos, onVent, cumVent, rec, gra, dea);
                    }
                }
            }
        }
        catch (NumberFormatException nfe) {
            System.out.println("Discard invalid command name");
        }
    }


    /**
     * This is the function that will search for the cases by the statename
     * 
     * @param stateName
     *            the state being searched for
     * @param number
     *            the amount of times the info needs to be printed
     */
    public void searchState(String stateName, String number) {
        outputA = new Output(list, command);
        ArrayList<State> list1 = new ArrayList<State>();
        for (State p : list) {
            list1.add(p.copy());
        }
        list1 = outputA.fixNum();
        String abbr = stateName.toLowerCase();
        if (abbr.length() > 2) {
            abbr = outputA.convertState(stateName);
        }
        abbr = abbr.toUpperCase();
        int records = 0;
        for (int i = 0; i < list1.size(); i++) {
            if (abbr.equals(list1.get(i).getStateName().toUpperCase())) {
                records++;
                if (records == Integer.parseInt(number)) {
                    break;
                }
            }
        }
        if (outputA.convertAbbr(abbr).equals("")) {
            System.out.printf("State of %s does not exist!\n", stateName);
        }
        else if (records == 0) {
            System.out.printf("There are no records from %s\n", stateName);
        }
        else {
            System.out.printf(
                "%d records are printed out for the state of %s\n", records,
                stateName);
            System.out.printf("date       positive  negative  hospitalized   "
                + "onVentilatorCurrently    "
                + "onVentilatorCumulative   recovered   "
                + "dataQualityGrade   death\n");
            // Records check will make sure that no more than indicated amount
            // of dates will be printed out
            int recordsCheck = 0;
            ArrayList<State> dateList = new ArrayList<State>();
            for (State p : list1) {
                if (abbr.equals(p.getStateName().toUpperCase())) {
                    dateList.add(p.copy());
                }
            }
            dateList = outputA.sortListDate(dateList);
            for (int j = 0; j < dateList.size(); j++) {
                if ((recordsCheck != records)) {
                    int date = outputA.fixDate(dateList.get(j).getDate());
                    int month = date / 1000000;
                    int day = (date / 10000) % 100;
                    int year = date % 10000;
                    int pos = dateList.get(j).getPositiveCases();
                    int neg = dateList.get(j).getNegativeCases();
                    int hos = dateList.get(j).getInHospital();
                    int onVent = dateList.get(j).getOnVentilator();
                    int cumVent = dateList.get(j).getCumulativeVentilator();
                    int rec = dateList.get(j).getRecovered();
                    String gra = dateList.get(j).getGradeQuality();
                    int dea = dateList.get(j).getDeaths();
                    recordsCheck++;
                    System.out.printf(
                        "%02d/%02d/%-3d %,-9d %,-9d %,-14d %,-24d %-24d "
                            + "%,-11d " + "%-18s " + "%,d\n", month, day, year,
                        pos, neg, hos, onVent, cumVent, rec, gra, dea);
                }
                else {
                    break;
                }
            }
        }
    }


    /**
     * This will print out the data for the command of summaryData
     */
    public void summaryData() {
        outputA = new Output(list, command);
        ArrayList<State> list1 = new ArrayList<State>();
        for (State p : list) {
            list1.add(p.copy());
        }
        list1 = outputA.fixNum();
        list1 = outputA.sortListState(list1);
        list1 = outputA.dupList(list1);
        // This will print the total amount of positive, death, and hospital
        // cases
        int totalDeaths = 0;
        int totalPositive = 0;
        int totalHospital = 0;
        for (int j = 0; j < list1.size(); j++) {
            totalDeaths += list1.get(j).getDeaths();
            totalPositive += list1.get(j).getPositiveCases();
            totalHospital += list1.get(j).getInHospital();
        }
        System.out.printf("Data Summary for %d states:\n", list1.size());
        System.out.println(
            "State   Total Case  Total Death   Total Hospitalized");
        // This for loop will add the values together for the total cases,
        // deaths, and hospitalized and print them out to the terminal
        for (int i = 0; i < list1.size(); i++) {
            System.out.printf("%-7s %,-11d %,-13d %,d \n", list1.get(i)
                .getStateName().toUpperCase(), list1.get(i).getPositiveCases(),
                list1.get(i).getDeaths(), list1.get(i).getInHospital());
        }
        System.out.printf(
            "Total Cases: %,d\nTotal Deaths: %,d\nTotal Hospitalized: %,d\n",
            totalPositive, totalDeaths, totalHospital);
    }


    /**
     * This will load the data and make a new list with said data
     * 
     * @param stList
     *            the statelist
     * @param file
     *            the file being read to make the data
     */
    public void loadData(ArrayList<State> stList, String file) {
        outputA = new Output(list, command);
        ArrayList<State> list1 = new ArrayList<State>();
        for (State p : stList) {
            list1.add(p.copy());
        }
        int removed = 0;

        for (int i = 0; i < stList.size(); i++) {
            String blankState = stList.get(i).getStateName();
            String state = outputA.convertAbbr(stList.get(i).getStateName());
            int grade = outputA.compareGrade(stList.get(i).getGradeQuality());
            int date = stList.get(i).getDate();
            String sDate = Integer.toString(date);
            if (blankState.equals("l") || grade == -100 || date == -1 || sDate
                .length() != 8) {
                System.out.println("Discard invalid record");
                stList.remove(i);
                removed++;
                i--;
            }
            else if (state.equals("") || blankState.equals("q")) {
                System.out.printf("State of %s does not exist!\n", blankState
                    .toUpperCase());
                stList.remove(i);
                removed++;
                i--;
            }

            else {

                for (int j = 0; j < i; j++) {

                    String state1 = stList.get(i).getStateName().toUpperCase();
                    String state2 = stList.get(j).getStateName().toUpperCase();
                    int date1 = stList.get(i).getDate();
                    int date2 = stList.get(j).getDate();
                    // 0528 2020
                    int fixedDate = outputA.fixDate(date1);
                    int month = fixedDate / 1000000;
                    int day = (fixedDate / 10000) % 100;
                    int year = fixedDate % 10000;

                    if (state1.equals(state2) && date1 == date2) {
                        int grade1 = outputA.compareGrade(stList.get(i)
                            .getGradeQuality());
                        int grade2 = outputA.compareGrade(stList.get(j)
                            .getGradeQuality());

                        if (grade1 - grade2 > 0) {
                            System.out.printf(
                                "Data has been updated for %s %02d/%d/%d\n",
                                state1.toUpperCase(), month, day, year);
                            stList.remove(j);
                            i--;
                            removed++;
                        }
                        else {
                            boolean updated = false;
                            // J is 1 and I is 2 for positive cases
                            if (stList.get(j).getPositiveCases() == -1 && stList
                                .get(i).getPositiveCases() != -1) {
                                stList.get(j).setPosCases(stList.get(i)
                                    .getPositiveCases());
                                updated = true;
                                stList.remove(i);
                                i--;
                            }
                            if (stList.get(j).getNegativeCases() == -1 && stList
                                .get(i).getNegativeCases() != -1) {
                                stList.get(j).setNegCases(stList.get(i)
                                    .getNegativeCases());
                                updated = true;
                                stList.remove(i);
                                i--;
                            }
                            if (stList.get(j).getInHospital() == -1 && stList
                                .get(i).getInHospital() != -1) {
                                stList.get(j).setHosCases(stList.get(i)
                                    .getInHospital());
                                updated = true;
                                stList.remove(i);
                                i--;
                            }
                            if (stList.get(j).getDeaths() == -1 && stList.get(i)
                                .getDeaths() != -1) {
                                stList.get(j).setDeathCases(stList.get(i)
                                    .getDeaths());
                                updated = true;
                                stList.remove(i);
                                i--;
                            }
                            if (stList.get(j).getOnVentilator() == -1 && stList
                                .get(i).getOnVentilator() != -1) {
                                stList.get(j).setOnVent(stList.get(i)
                                    .getOnVentilator());
                                updated = true;
                                stList.remove(i);
                                i--;
                            }
                            if (stList.get(j).getCumulativeVentilator() == -1
                                && stList.get(i)
                                    .getCumulativeVentilator() != -1) {
                                stList.get(j).setCumVent(stList.get(i)
                                    .getCumulativeVentilator());
                                updated = true;
                                stList.remove(i);
                                i--;
                            }
                            if (stList.get(j).getRecovered() == -1 && stList
                                .get(i).getRecovered() != -1) {
                                stList.get(j).setRecCases(stList.get(i)
                                    .getRecovered());
                                updated = true;
                                stList.remove(i);
                                i--;
                            }
                            if (updated) {
                                System.out.printf(
                                    "Data has been updated for the missing "
                                        + "data in %s\n", state1);
                            }
                            else {
                                System.out.printf(
                                    "Low quality data rejected for %s\n",
                                    state1);
                                stList.remove(j);
                                i--;
                                removed++;
                            }
                        }
                    }
                }
            }
        }
        System.out.printf("Finished loading %s file\n", file);
        int size = 0;
        if (list1.size() == stList.size()) {
            System.out.printf("%d records have been loaded\n", stList.size());
        }
        else {
            size = list1.size() - removed - prevListSize;
            System.out.printf("%d records have been loaded\n", size);
        }
        list = stList;
        prevListSize = list.size();
    }


    /**
     * For getting rid of the duplicate state names
     * 
     * @param listA
     *            the needs to be duplicated
     * @return a new list that is duplicated and condensed
     */
    public ArrayList<State> dupList(ArrayList<State> listA) {
        ArrayList<State> newList = new ArrayList<State>();
        for (State p : listA) {
            newList.add(p.copy());
        }
        for (int k = 0; k < 1000; k++) {
            for (int i = 0; i < newList.size(); i++) {
                if (i < newList.size() - 1) {
                    if (newList.get(i).getStateName().equals(newList.get(i + 1)
                        .getStateName())) {
                        int firstPos = newList.get(i).getPositiveCases();
                        int secondPos = newList.get(i + 1).getPositiveCases();
                        int firstDeath = newList.get(i).getDeaths();
                        int secondDeath = newList.get(i + 1).getDeaths();
                        int firstHos = newList.get(i).getInHospital();
                        int secondHos = newList.get(i + 1).getInHospital();
                        newList.get(i).setPosCases(firstPos + secondPos);
                        newList.get(i).setDeathCases(firstDeath + secondDeath);
                        newList.get(i).setHosCases(firstHos + secondHos);
                        newList.remove(i + 1);
                    }
                }
            }
        }
        return newList;
    }


    /**
     * This will list the date by date from most recent to least
     * 
     * @param listA
     *            the list being sorted
     * @return the list sorted by date
     */
    public ArrayList<State> sortListDate(ArrayList<State> listA) {
        outputA = new Output(listA, command);
        for (int i = 0; i < listA.size(); i++) {
            if (i < listA.size() - 1) {
                int firstDate = listA.get(i).getDate();
                int secondDate = listA.get(i + 1).getDate();
                if (firstDate - secondDate < 0) {
                    State state = listA.get(i);
                    listA.remove(i);
                    listA.add(i, listA.get(i));
                    listA.remove(i + 1);
                    listA.add(state);
                    i = -1;
                }
            }
        }
        return listA;
    }


    /**
     * This will sort the arraylist by the state name
     * 
     * @param listA
     *            the list of State s with the information in them
     * @return the sorted list
     */
    public ArrayList<State> sortListState(ArrayList<State> listA) {
        outputA = new Output(listA, command);
        for (int i = 0; i < listA.size(); i++) {
            if (i < listA.size() - 1) {
                String firstStr = outputA.convertAbbr(listA.get(i)
                    .getStateName());
                String secondStr = outputA.convertAbbr(listA.get(i + 1)
                    .getStateName());
                if (firstStr.compareTo(secondStr) > 0) {
                    State state = listA.get(i);
                    listA.remove(i);
                    listA.add(i, listA.get(i));
                    listA.remove(i + 1);
                    listA.add(state);
                    i = -1;
                }
            }
        }
        return listA;
    }


    /**
     * This will take the date that was parsed into the state class and make it
     * a
     * use-able date
     * 
     * @param stateDate
     *            is the int that needs to be converted
     * @return the fixed date
     */
    public int fixDate(int stateDate) {
        // 20200818
        // year, month, day
        String yearS = Integer.toString(stateDate / 10000).trim();
        String dayS = Integer.toString(stateDate % 100).trim();
        String monthS = Integer.toString((stateDate % 10000) / 100).trim();
        if (dayS.length() == 1) {
            dayS = "0" + dayS;
        }
        try {
            int date = Integer.parseInt(monthS + dayS + yearS);
            return date;
        }
        catch (NumberFormatException nfe) {
            return 0;
        }
    }


    /**
     * This will set the -1 ones to 0's
     * 
     * @return the new list
     */
    public ArrayList<State> fixNum() {
        ArrayList<State> newList = new ArrayList<State>();
        for (State p : list) {
            newList.add(p.copy());
        }
        for (int i = 0; i < newList.size(); i++) {
            int pos = newList.get(i).getPositiveCases();
            int neg = newList.get(i).getNegativeCases();
            int onVent = newList.get(i).getOnVentilator();
            int cumVent = newList.get(i).getCumulativeVentilator();
            int rec = newList.get(i).getRecovered();
            int dea = newList.get(i).getDeaths();
            int hos = newList.get(i).getInHospital();
            if (pos == -1) {
                newList.get(i).setPosCases(0);
            }
            if (neg == -1) {
                newList.get(i).setNegCases(0);
            }
            if (onVent == -1) {
                newList.get(i).setOnVent(0);
            }
            if (cumVent == -1) {
                newList.get(i).setCumVent(0);
            }
            if (rec == -1) {
                newList.get(i).setRecCases(0);
            }
            if (dea == -1) {
                newList.get(i).setDeathCases(0);
            }
            if (hos == -1) {
                newList.get(i).setHosCases(0);
            }
        }
        return newList;
    }


    /**
     * This is how we will compare grade by giving them value so that when the
     * difference it will either be negative or positive
     * 
     * @param grade
     *            the grade
     * @return a number representing the grade
     */
    public int compareGrade(String grade) {
        switch (grade) {
            case "A+":
                return 9;
            case "A":
                return 8;
            case "B+":
                return 7;
            case "B":
                return 6;
            case "C+":
                return 5;
            case "C":
                return 4;
            case "D+":
                return 3;
            case "D":
                return 2;
            case "F":
                return 1;
            default:
                return -100;
        }
    }


    /**
     * This will convert the state Abbreviation to the state full name
     * 
     * @param stateAbr
     *            the state abbreviation
     * @return the states full name
     */
    public String convertAbbr(String stateAbr) {
        switch (stateAbr.toUpperCase()) {
            case "AL":
                return "Alabama";

            case "AK":
                return "Alaska";

            case "AS":
                return "American Samoa";

            case "AZ":
                return "Arizona";

            case "AR":
                return "Arkansas";

            case "CA":
                return "California";

            case "CO":
                return "Colorado";

            case "CT":
                return "Connecticut";

            case "DE":
                return "Delaware";

            case "DC":
                return "District Of Columbia";

            case "FM":
                return "Federated States Of Micronesia";

            case "FL":
                return "Florida";

            case "GA":
                return "Georgia";

            case "GU":
                return "Guam";

            case "HI":
                return "Hawaii";

            case "ID":
                return "Idaho";

            case "IL":
                return "Illinois";

            case "IN":
                return "Indiana";

            case "IA":
                return "Iowa";

            case "KS":
                return "Kansas";

            case "KY":
                return "Kentucky";

            case "LA":
                return "Louisiana";

            case "ME":
                return "Maine";

            case "MH":
                return "Marshall Islands";

            case "MD":
                return "Maryland";

            case "MA":
                return "Massachusetts";

            case "MI":
                return "Michigan";

            case "MN":
                return "Minnesota";

            case "MS":
                return "Mississippi";

            case "MO":
                return "Missouri";

            case "MT":
                return "Montana";

            case "NE":
                return "Nebraska";

            case "NV":
                return "Nevada";

            case "NH":
                return "New Hampshire";

            case "NJ":
                return "New Jersey";

            case "NM":
                return "New Mexico";

            case "NY":
                return "New York";

            case "NC":
                return "North Carolina";

            case "ND":
                return "North Dakota";

            case "MP":
                return "Northern Mariana Islands";

            case "OH":
                return "Ohio";

            case "OK":
                return "Oklahoma";

            case "OR":
                return "Oregon";

            case "PW":
                return "Palau";

            case "PA":
                return "Pennsylvania";

            case "PR":
                return "Puerto Rico";

            case "RI":
                return "Rhode Island";

            case "SC":
                return "South Carolina";

            case "SD":
                return "South Dakota";

            case "TN":
                return "Tennessee";

            case "TX":
                return "Texas";

            case "UT":
                return "Utah";

            case "VT":
                return "Vermont";

            case "VI":
                return "Virgin Islands";

            case "VA":
                return "Virginia";

            case "WA":
                return "Washington";

            case "WV":
                return "West Virginia";

            case "WI":
                return "Wisconsin";

            case "WY":
                return "Wyoming";
            default:
                return "";
        }
    }


    /**
     * This will convert the state names into abbreviations
     * 
     * @param stateName
     *            the full state name
     * @return the abbreviation
     */
    public String convertState(String stateName) {
        switch (stateName.toUpperCase()) {
            case "ALABAMA":
                return "AL";

            case "ALASKA":
                return "AK";

            case "AMERICAN SAMOA":
                return "AS";

            case "ARIZONA":
                return "AZ";

            case "ARKANSAS":
                return "AR";

            case "CALIFORNIA":
                return "CA";

            case "COLORADO":
                return "CO";

            case "CONNECTICUT":
                return "CT";

            case "DELAWARE":
                return "DE";

            case "DISTRICT OF COLUMBIA":
                return "DC";

            case "FEDERATED STATES OF MICRONESIA":
                return "FM";

            case "FLORIDA":
                return "FL";

            case "GEORGIA":
                return "GA";

            case "GUAM":
                return "GU";

            case "HAWAII":
                return "HI";

            case "IDAHO":
                return "ID";

            case "ILLINOIS":
                return "IL";

            case "INDIANA":
                return "IN";

            case "IOWA":
                return "IA";

            case "KANSAS":
                return "KS";

            case "KENTUCKY":
                return "KY";

            case "LOUISIANA":
                return "LA";

            case "MAINE":
                return "ME";

            case "MARSHALL ISLANDS":
                return "MH";

            case "MARYLAND":
                return "MD";

            case "MASSACHUSETTS":
                return "MA";

            case "MICHIGAN":
                return "MI";

            case "MINNESOTA":
                return "MN";

            case "MISSISSIPPI":
                return "MS";

            case "MISSOURI":
                return "MO";

            case "MONTANA":
                return "MT";

            case "NEBRASKA":
                return "NE";

            case "NEVADA":
                return "NV";

            case "NEW HAMPSHIRE":
                return "NH";

            case "NEW JERSEY":
                return "NJ";

            case "NEW MEXICO":
                return "NM";

            case "NEW YORK":
                return "NY";

            case "NORTH CAROLINA":
                return "NC";

            case "NORTH DAKOTA":
                return "ND";

            case "NORTHERN MARIANA ISLANDS":
                return "MP";

            case "OHIO":
                return "OH";

            case "OKLAHOMA":
                return "OK";

            case "OREGON":
                return "OR";

            case "PALAU":
                return "PW";

            case "PENNSYLVANIA":
                return "PA";

            case "PUERTO RICO":
                return "PR";

            case "RHODE ISLAND":
                return "RI";

            case "SOUTH CAROLINA":
                return "SC";

            case "SOUTH DAKOTA":
                return "SD";

            case "TENNESSEE":
                return "TN";

            case "TEXAS":
                return "TX";

            case "UTAH":
                return "UT";

            case "VERMONT":
                return "VT";

            case "VIRGIN ISLANDS":
                return "VI";

            case "VIRGINIA":
                return "VA";

            case "WASHINGTON":
                return "WA";

            case "WEST VIRGINIA":
                return "WV";

            case "WISCONSIN":
                return "WI";

            case "WYOMING":
                return "WY";
            default:
                return "";
        }
    }
}
