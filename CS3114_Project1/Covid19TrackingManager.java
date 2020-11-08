
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class is for taking in the .txt file and reading the data so that it can
 * be used later
 * 
 * @author Foad Nachabe (foadn) and Jack Layfield (jackml)
 * @version 9/12/2020
 */
public class Covid19TrackingManager {

    private static Scanner scannerL;
    private static Scanner inputScanner;


    /**
     * This constructor is not needed so it will be empty
     */
    public Covid19TrackingManager() {
        // Empty
    }


    /**
     * This will read the excel data and pass the information into a state class
     * which will be added into a ArrayList that will store all of the state
     * classes
     * 
     * @param fileName
     *            the name of the file being read
     * @return the arraylist full of state classes
     * @throws FileNotFoundException
     */
    public static ArrayList<State> readData(String fileName)
        throws FileNotFoundException {
        ArrayList<State> stateList = new ArrayList<State>();
        File file = new File(fileName);
        scannerL = new Scanner(file);
        scannerL.useDelimiter(",");
        scannerL.nextLine();
        while (scannerL.hasNextLine()) {
            String line = scannerL.nextLine();
            if (line.replace(",", "").length() == 0) {
                continue;
            }
            else {
                ArrayList<String> array = new ArrayList<String>();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                int checker = 0;
                int deaths = 0;
                while (lineScanner.hasNextLine()) {
                    try {
                        array.add(lineScanner.next());
                        checker++;
                    }
                    catch (NoSuchElementException e) {
                        deaths = -1;
                        checker++;
                        String dead = Integer.toString(deaths);
                        array.add(dead);
                    }
                }
                lineScanner.close();
                // If doesnt have all 10 columns then we throw it away
                if (checker != 10) {
                    stateList.add(new State(-1, "", -1, -1, -1, -1, -1, -1, "",
                        -1));
                }
                else {
                    // For the Date
                    int date = 0;
                    if (array.get(0).trim().length() != 8) {
                        date = -1;
                    }
                    else {
                        // When adding a .trim() it returns null instead
                        date = Integer.parseInt(array.get(0).trim());
                    }

                    // For the State Name
                    String state = "l";
                    if (array.get(1).trim().length() != 2) {
                        state = "l";
                        if (array.get(1).trim().length() == 0) {
                            state = "l";
                        }
                        else {
                            state = array.get(1).toLowerCase().trim();
                        }
                    }
                    else {
                        state = array.get(1).toLowerCase().trim();
                    }

                    // For the Positive Cases
                    int posCases = 0;
                    if (array.get(2).trim().length() == 0) {
                        posCases = -1;
                    }
                    else {
                        posCases = (int)Double.parseDouble(array.get(2).trim());
                    }

                    // For the Negative Cases
                    int negCases = 0;
                    if (array.get(3).trim().length() == 0) {
                        negCases = -1;
                    }
                    else {
                        negCases = (int)Double.parseDouble(array.get(3).trim());
                    }

                    // For the amount in the Hospital
                    int hospital = 0;
                    if (array.get(4).trim().length() == 0) {
                        hospital = -1;
                    }
                    else {
                        hospital = (int)Double.parseDouble(array.get(4).trim());
                    }

                    // For the amount on a Ventilator Currently
                    int onVent = 0;
                    if (array.get(5).trim().length() == 0) {
                        onVent = -1;
                    }
                    else {
                        onVent = (int)Double.parseDouble(array.get(5).trim());
                    }

                    // For the amount an a Ventilator Cumulative
                    int cumVent = 0;
                    if (array.get(6).trim().length() == 0) {
                        cumVent = -1;
                    }
                    else {
                        cumVent = (int)Double.parseDouble(array.get(6).trim());
                    }

                    // For the amount of people Recovered
                    int recovered = 0;
                    if (array.get(7).trim().length() == 0) {
                        recovered = -1;
                    }
                    else {
                        recovered = (int)Double.parseDouble(array.get(7)
                            .trim());
                    }

                    // For the Grade Quality
                    String grade = " ";
                    if (array.get(8).trim().length() != 1 && array.get(8)
                        .length() != 2) {
                        grade = "";
                    }
                    else {
                        grade = array.get(8).trim();
                    }

                    // For the amount of Deaths
                    deaths = 0;
                    if (array.get(9).trim().length() == 0) {
                        deaths = -1;
                    }
                    else {
                        deaths = (int)Double.parseDouble(array.get(9).trim());
                    }
                    stateList.add(new State(date, state, posCases, negCases,
                        hospital, onVent, cumVent, recovered, grade, deaths));
                }
            }
        }
        scannerL.close();
        return stateList;
    }


    /**
     * This will launch the java application
     * 
     * @param args
     *            the input.txt file that will be read
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String[] dateHolder = new String[1000];
        ArrayList<State> currList = new ArrayList<State>();
        ArrayList<State> newList = new ArrayList<State>();
        int dateIndex = 0;
        int filesIndex = 0;
        inputScanner = new Scanner(new File(args[0]));
        String file = " "; // Use to have head_100_random_30.csv
        String command = " ";
        // Use to have regular intalization
        Output output = new Output(newList, file);
        while (inputScanner.hasNextLine()) {
            if (!inputScanner.hasNext()) {
                break;
            }
            // Makes sure that the input scanner does not keep reading nothing
            else {
                command = inputScanner.next().toLowerCase();
            }
            String date;
            String number;
            // This is the load case
            if (command.equals("load")) {
                String[] someArray = new String[7];
                int l = 0;
                String cLine = "";
                if (inputScanner.hasNextLine()) {
                    cLine = inputScanner.nextLine();
                }
                Scanner lineScanner3 = new Scanner(cLine);
                while (lineScanner3.hasNext()) {
                    someArray[l] = lineScanner3.next().trim();
                    l++;
                }
                lineScanner3.close();
                // For load there can only be one arguement after it 
                if (l == 1) {
                    file = someArray[0];
                    if (readData(file) == null) {
                        break;
                    }
                    else {
                        if (filesIndex > 0) {
                            currList = output.getList();
                        }
                        else {
                            currList = readData(file);
                            output = new Output(currList, command);
                        }
                        filesIndex++;
                    }
                    if (filesIndex > 1) {
                        newList = new ArrayList<State>();
                        ArrayList<State> holderList = readData(file);
                        int i = 0;
                        for (; i < currList.size(); i++) {
                            newList.add(currList.get(i));
                        }
                        for (int j = 0; j < holderList.size(); j++) {
                            newList.add(i, holderList.get(j));
                            i++;
                        }

                        output.loadData(newList, file);
                    }
                    else {
                        output.loadData(currList, file);
                    }
                }
                else {
                    System.out.println("Discard invalid command name");
                }
            }
            // This is the summary data case
            else if (command.equals("summarydata")) {
                String[] anotherArray = new String[7];
                int k = 0;
                String bLine = "";
                if (inputScanner.hasNextLine()) {
                    bLine = inputScanner.nextLine();
                }
                Scanner lineScanner2 = new Scanner(bLine);
                while (lineScanner2.hasNext()) {
                    anotherArray[k] = lineScanner2.next().trim();
                    k++;
                }
                lineScanner2.close();
                if (k == 0) {
                    output.summaryData();
                }
                else {
                    System.out.println("Discard invalid command name");
                }
            }
            // This is the dump data case
            else if (command.equals("dumpdata")) {
                String[] commandArray = new String[7];
                int j = 0;
                String aLine = "";
                if (inputScanner.hasNextLine()) {
                    aLine = inputScanner.nextLine();
                }
                Scanner lineScanner1 = new Scanner(aLine);
                while (lineScanner1.hasNext()) {
                    commandArray[j] = lineScanner1.next().trim();
                    j++;
                }
                lineScanner1.close();
                if (j == 1) {
                    file = commandArray[0];
                    output.dumpData(file);
                }
                else {
                    System.out.println("Discard invalid command name");
                }
            }
            // This is the search case
            else if (command.equals("search")) {
                String[] array = new String[7];
                int i = 0;
                String line = "";
                if (inputScanner.hasNextLine()) {
                    line = inputScanner.nextLine();
                }
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNext()) {
                    array[i] = lineScanner.next().trim();
                    i++;
                }
                lineScanner.close();
                if (i == 0) {
                    ArrayList<State> mostRecent = new ArrayList<State>();
                    for (State p : output.getList()) {
                        mostRecent.add(p.copy());
                    }
                    mostRecent = output.sortListDate(mostRecent);
                    try {
                        int date1 = output.fixDate(mostRecent.get(0).getDate());
                        String month = Integer.toString(date1 / 1000000);
                        String day = Integer.toString((date1 / 10000) % 100);
                        String year = Integer.toString(date1 % 10000);
                        if (month.length() == 1) {
                            month = "0" + month;
                        }
                        if (day.length() == 1) {
                            day = "0" + day;
                        }
                        String tDate = month + "/" + day + "/" + year;
                        output.searchDate(tDate);
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("No available data");
                    }
                }
                // This will handle the case where searching by the date
                // 08/17/2020
                else if (i == 1) {
                    date = array[0];
                    if (date.length() != 10 || date.charAt(2) != '/' || date
                        .charAt(5) != '/') {
                        System.out.println("Discard invalid command name");
                    }
                    else if (output.convertAbbr(date).equals("") || output
                        .convertState(date).equals("")) {
                        output.searchDate(date);
                        dateHolder[dateIndex] = date;
                        dateIndex++;
                    }
                }
                // This will handle the case where the state is only one name
                else if (i >= 2) {
                    int indexB = 0;
                    String wholeState = array[0];
                    for (int w = 0; w < array.length; w++) {
                        if (array[w] != null) {
                            indexB++;
                        }
                    }
                    for (int e = 1; e < indexB - 1; e++) {
                        wholeState += " " + array[e];
                    }
                    number = array[indexB - 1];
                    try {
                        if (Integer.parseInt(number) <= 0) {
                            System.out.printf(
                                "Invalid command. # of records has to be "
                                    + "positive\n", number);
                        }
                        else {
                            output.searchState(wholeState, number);
                        }
                    }
                    catch (NumberFormatException nfe) {
                        System.out.println("Discard invalid command name");
                    }
                }
            }
            else {
                System.out.println("Discard Invalid command name");
            }
        }
        inputScanner.close();
    }
}
