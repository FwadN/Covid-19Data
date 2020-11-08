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

import java.io.IOException;
import java.util.ArrayList;
import student.TestCase;

/**
 * This is the test class for all the methods in output
 * 
 * @author Foad Nachabe (foadn)
 * @author Jack Layfield (jackml)
 * @version 9/21/2020
 */
public class OutputTest extends TestCase {
    private Output output;
    private State state;
    private State state2;
    private ArrayList<State> arr;


    /**
     * This is the setup for the test class
     */
    public void setUp() {
        state = new State(20200818, "ca", 110, 50, 123, 11, 19, 65, "A+", 15);

        state2 = new State(20200818, "wV", 72, 43, 79, 12, 17, 33, "B", 9);

        arr = new ArrayList<State>();

        arr.add(state);
        arr.add(state2);

        output = new Output(arr, "summarydata");
    }


    /**
     * This will test the dumpdata method
     * 
     * @throws IOException
     *             throws an exception if it does not happen
     */
    public void testDumpData() throws IOException {

        output.dumpData("head_100_random_30.csv");

        assertEquals(systemOut().getHistory(),
            "2 records saved in the head_100_random_30.csv data file\r\n");
    }


    /**
     * This will test the search date method
     */
    public void testSearchDate() {
        output.searchDate("08/18/2020");
        assertEquals(systemOut().getHistory(),
            "There are 2 records on 08/18/2020\r\n"
                + "state positive  negative    hospitalized   "
                + "onVentilatorCurrently    "
                + "onVentilatorCumulative   recovered   dataQualityGrade   "
                + "death\r\n" + "CA    110       50          123            "
                + "11                       "
                + "19                       65          A+                 "
                + "15\r\n" + "WV    72        43          79            "
                + " 12                       "
                + "17                       33          B                 "
                + " 9\r\n");
        systemOut().clearHistory();
        output.searchDate("02/10/1999");
        assertEquals(systemOut().getHistory(),
            "There are no records on 02/10/1999\r\n");
        systemOut().clearHistory();
    }


    /**
     * This will test the search state method
     */
    public void testSearchState() {
        output.searchState("garbage", "1");
        assertEquals(systemOut().getHistory(),
            "State of garbage does not exist!\r\n");
        systemOut().clearHistory();
        output.searchState("Ohio", "1");
        assertEquals(systemOut().getHistory(),
            "There are no records from Ohio\r\n");
        systemOut().clearHistory();
        output.searchState("NJ", "1");
        assertEquals(systemOut().getHistory(),
            "There are no records from NJ\r\n");
        systemOut().clearHistory();
        output.searchState("CALIFORNIA", "1");
        assertEquals(systemOut().getHistory(),
            "1 records are printed out for the state of CALIFORNIA\r\n"
                + "date       positive  negative  hospitalized   "
                + "onVentilatorCurrently    onVentilatorCumulative   "
                + "recovered   dataQualityGrade   death\r\n"
                + "08/18/2020 110       50        123            "
                + "11                       19                       "
                + "65          A+                 15\r\n");
    }


    /**
     * This will test the summary data method
     */
    public void testSummaryData() {
        output.summaryData();
        assertEquals(systemOut().getHistory(), "Data Summary for 2 states:\r\n"
            + "State   Total Case  Total Death   Total Hospitalized\r\n"
            + "CA      110         15            123 \r\n"
            + "WV      72          9             79 \r\n"
            + "Total Cases: 182\r\n" + "Total Deaths: 24\r\n"
            + "Total Hospitalized: 202\r\n");
    }


    /**
     * This will test the load data method
     */
    public void testLoadData() {
        output.loadData(arr, "head_100_random_30.csv");

        assertEquals(systemOut().getHistory(),
            "Finished loading head_100_random_30.csv file\r\n"
                + "2 records have been loaded\r\n");

        systemOut().clearHistory();

        State statey = new State(20200818, "KS", 51992, 536268, 3562, 108, 460,
            44905, "B", 600);

        State statey2 = new State(20200818, "KS", 51992, 536268, 3562, 108, 460,
            44905, "C", 600);

        arr.add(statey);
        arr.add(statey2);

        output.loadData(arr, "head_100_random_30.csv");
    }


    /**
     * This will test the sort list state method
     */
    public void testSortListState() {
        State statey = new State(20200819, "wV", 72, 43, 79, 12, 17, 33, "B",
            9);

        arr.add(statey);

        output = new Output(arr, "summarydata");

        output.sortListState(arr);

        ArrayList<State> list = new ArrayList<>();
        list.add(state);
        list.add(state2);
        list.add(statey);

        assertEquals(output.sortListState(arr), list);

        arr.remove(statey);

        statey = new State(20200817, "nj", 72, 43, 79, 12, 17, 33, "B", 9);

        arr.add(statey);

        list = new ArrayList<>();

        list.add(state);
        list.add(statey);
        list.add(state2);

        assertEquals(output.sortListState(arr), list);
    }


    /**
     * This will test the sort list by date method
     */
    public void testSortListDate() {
        // output.sortListDate(arr);

        State statey = new State(20200819, "nj", 72, 43, 79, 12, 17, 33, "B",
            9);

        arr.add(statey);

        ArrayList<State> list = new ArrayList<>();
        list.add(statey);
        list.add(state2);
        list.add(state);

        assertEquals(output.sortListDate(arr), list);
    }


    /**
     * This will test the dup list method
     */
    public void testDupList() {
        State statey = new State(20200818, "wV", 72, 43, 79, 12, 17, 33, "B",
            9);

        arr.add(statey);

        ArrayList<State> list = new ArrayList<>();
        list.add(state.copy());
        list.add(state2.copy());

        assertFalse(output.dupList(arr).isEmpty());
    }


    /**
     * This will test the fix date method
     */
    public void testFixDate() {
        assertEquals(output.fixDate(20200818), 8182020);
    }


    /**
     * This will test the compare grade method
     */
    public void testCompareGrade() {
        assertEquals(output.compareGrade("A+"), 9);
        assertEquals(output.compareGrade("A"), 8);
        assertEquals(output.compareGrade("B+"), 7);
        assertEquals(output.compareGrade("B"), 6);
        assertEquals(output.compareGrade("C+"), 5);
        assertEquals(output.compareGrade("C"), 4);
        assertEquals(output.compareGrade("D+"), 3);
        assertEquals(output.compareGrade("D"), 2);
        assertEquals(output.compareGrade("F"), 1);
        assertEquals(output.compareGrade("r"), -100);
    }


    /**
     * This will test the load data method
     */
    public void testLoadData3() {
        ArrayList<State> list1 = new ArrayList<State>();
        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "A", -1));
        list1.add(new State(20200819, "nj", 1, -1, -1, -1, -1, -1, "A", -1));
        output.loadData(list1, "head_100_random_30.csv");
        assertEquals(list1.size(), 1);
        list1.clear();

        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "A", -1));
        list1.add(new State(20200819, "nj", -1, 1, -1, -1, -1, -1, "A", -1));
        output.loadData(list1, "head_100_random_30.csv");
        assertEquals(list1.size(), 1);
        list1.clear();

        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "A", -1));
        list1.add(new State(20200819, "nj", -1, -1, 1, -1, -1, -1, "A", -1));
        output.loadData(list1, "head_100_random_30.csv");
        assertEquals(list1.size(), 1);
        list1.clear();

        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "A", -1));
        list1.add(new State(20200819, "nj", -1, -1, -1, 1, -1, -1, "A", -1));
        output.loadData(list1, "head_100_random_30.csv");
        assertEquals(list1.size(), 1);
        list1.clear();

        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "A", -1));
        list1.add(new State(20200819, "nj", -1, -1, -1, -1, 1, -1, "A", -1));
        output.loadData(list1, "head_100_random_30.csv");
        assertEquals(list1.size(), 1);
        list1.clear();

        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "A", -1));
        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, 1, "A", -1));
        output.loadData(list1, "head_100_random_30.csv");
        assertEquals(list1.size(), 1);
        list1.clear();

        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "A", -1));
        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "A", 1));
        output.loadData(list1, "head_100_random_30.csv");
        assertEquals(list1.size(), 1);
        list1.clear();
    }


    /**
     * This will test the fix num method
     */
    public void testFixNum() {
        ArrayList<State> list1 = new ArrayList<State>();
        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "B", -1));

        Output outputA = new Output(list1, "Summarydata");
        list1 = outputA.fixNum();
        assertEquals(list1.get(0).getPositiveCases(), 0);
        assertEquals(list1.get(0).getNegativeCases(), 0);
        assertEquals(list1.get(0).getOnVentilator(), 0);
        assertEquals(list1.get(0).getCumulativeVentilator(), 0);
        assertEquals(list1.get(0).getInHospital(), 0);
        assertEquals(list1.get(0).getRecovered(), 0);
        assertEquals(list1.get(0).getDeaths(), 0);
    }


    /**
     * This will test the load data method
     */
    public void testLoadData2() {
        ArrayList<State> list1 = new ArrayList<State>();
        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "B", -1));
        list1.add(new State(20200819, "nj", 1, -1, -1, -1, -1, -1, "A", -1));
        list1.add(new State(20200819, "nj", -1, -1, -1, -1, -1, -1, "B", -1));
        output.loadData(list1, "head_100_random_30.csv");
        assertEquals(list1.size(), 1);
    }


    /**
     * This will test the convert Abbr method
     */
    public void testConvertAbbr() {
        assertEquals(output.convertAbbr("AL"), "Alabama");
        assertEquals(output.convertAbbr("AK"), "Alaska");
        assertEquals(output.convertAbbr("AS"), "American Samoa");
        assertEquals(output.convertAbbr("AZ"), "Arizona");
        assertEquals(output.convertAbbr("AR"), "Arkansas");
        assertEquals(output.convertAbbr("CA"), "California");
        assertEquals(output.convertAbbr("CO"), "Colorado");
        assertEquals(output.convertAbbr("CT"), "Connecticut");
        assertEquals(output.convertAbbr("DE"), "Delaware");
        assertEquals(output.convertAbbr("DC"), "District Of Columbia");
        assertEquals(output.convertAbbr("FM"),
            "Federated States Of Micronesia");
        assertEquals(output.convertAbbr("FL"), "Florida");
        assertEquals(output.convertAbbr("GA"), "Georgia");
        assertEquals(output.convertAbbr("GU"), "Guam");
        assertEquals(output.convertAbbr("HI"), "Hawaii");
        assertEquals(output.convertAbbr("ID"), "Idaho");
        assertEquals(output.convertAbbr("IL"), "Illinois");
        assertEquals(output.convertAbbr("IN"), "Indiana");
        assertEquals(output.convertAbbr("IA"), "Iowa");
        assertEquals(output.convertAbbr("KS"), "Kansas");
        assertEquals(output.convertAbbr("KY"), "Kentucky");
        assertEquals(output.convertAbbr("LA"), "Louisiana");
        assertEquals(output.convertAbbr("ME"), "Maine");
        assertEquals(output.convertAbbr("MH"), "Marshall Islands");
        assertEquals(output.convertAbbr("MD"), "Maryland");
        assertEquals(output.convertAbbr("MA"), "Massachusetts");
        assertEquals(output.convertAbbr("MI"), "Michigan");
        assertEquals(output.convertAbbr("MN"), "Minnesota");
        assertEquals(output.convertAbbr("MO"), "Missouri");
        assertEquals(output.convertAbbr("MS"), "Mississippi");
        assertEquals(output.convertAbbr("MT"), "Montana");
        assertEquals(output.convertAbbr("NE"), "Nebraska");
        assertEquals(output.convertAbbr("NV"), "Nevada");
        assertEquals(output.convertAbbr("NH"), "New Hampshire");
        assertEquals(output.convertAbbr("NJ"), "New Jersey");
        assertEquals(output.convertAbbr("NM"), "New Mexico");
        assertEquals(output.convertAbbr("NY"), "New York");
        assertEquals(output.convertAbbr("NC"), "North Carolina");
        assertEquals(output.convertAbbr("ND"), "North Dakota");
        assertEquals(output.convertAbbr("MP"), "Northern Mariana Islands");
        assertEquals(output.convertAbbr("OH"), "Ohio");
        assertEquals(output.convertAbbr("OK"), "Oklahoma");
        assertEquals(output.convertAbbr("OR"), "Oregon");
        assertEquals(output.convertAbbr("PW"), "Palau");
        assertEquals(output.convertAbbr("PA"), "Pennsylvania");
        assertEquals(output.convertAbbr("PR"), "Puerto Rico");
        assertEquals(output.convertAbbr("RI"), "Rhode Island");
        assertEquals(output.convertAbbr("SC"), "South Carolina");
        assertEquals(output.convertAbbr("SD"), "South Dakota");
        assertEquals(output.convertAbbr("TN"), "Tennessee");
        assertEquals(output.convertAbbr("TX"), "Texas");
        assertEquals(output.convertAbbr("UT"), "Utah");
        assertEquals(output.convertAbbr("VT"), "Vermont");
        assertEquals(output.convertAbbr("VI"), "Virgin Islands");
        assertEquals(output.convertAbbr("VA"), "Virginia");
        assertEquals(output.convertAbbr("WA"), "Washington");
        assertEquals(output.convertAbbr("WV"), "West Virginia");
        assertEquals(output.convertAbbr("WI"), "Wisconsin");
        assertEquals(output.convertAbbr("WY"), "Wyoming");
        assertEquals(output.convertAbbr("x"), "");

    }


    /**
     * This will test the convert state method
     */
    public void testConvertState() {
        assertEquals(output.convertState("Alabama"), "AL");
        assertEquals(output.convertState("Alaska"), "AK");
        assertEquals(output.convertState("Arizona"), "AZ");
        assertEquals(output.convertState("American Samoa"), "AS");
        assertEquals(output.convertState("Arkansas"), "AR");
        assertEquals(output.convertState("California"), "CA");
        assertEquals(output.convertState("Colorado"), "CO");
        assertEquals(output.convertState("Connecticut"), "CT");
        assertEquals(output.convertState("Delaware"), "DE");
        assertEquals(output.convertState("District Of Columbia"), "DC");
        assertEquals(output.convertState("Federated States Of Micronesia"),
            "FM");
        assertEquals(output.convertState("Florida"), "FL");
        assertEquals(output.convertState("Georgia"), "GA");
        assertEquals(output.convertState("Guam"), "GU");
        assertEquals(output.convertState("Hawaii"), "HI");
        assertEquals(output.convertState("Idaho"), "ID");
        assertEquals(output.convertState("Illinois"), "IL");
        assertEquals(output.convertState("Indiana"), "IN");
        assertEquals(output.convertState("Iowa"), "IA");
        assertEquals(output.convertState("Kansas"), "KS");
        assertEquals(output.convertState("Kentucky"), "KY");
        assertEquals(output.convertState("Louisiana"), "LA");
        assertEquals(output.convertState("Maine"), "ME");
        assertEquals(output.convertState("Marshall Islands"), "MH");
        assertEquals(output.convertState("Maryland"), "MD");
        assertEquals(output.convertState("Massachusetts"), "MA");
        assertEquals(output.convertState("Michigan"), "MI");
        assertEquals(output.convertState("Minnesota"), "MN");
        assertEquals(output.convertState("Missouri"), "MO");
        assertEquals(output.convertState("Mississippi"), "MS");
        assertEquals(output.convertState("Montana"), "MT");
        assertEquals(output.convertState("Nebraska"), "NE");
        assertEquals(output.convertState("Nevada"), "NV");
        assertEquals(output.convertState("New Hampshire"), "NH");
        assertEquals(output.convertState("New Jersey"), "NJ");
        assertEquals(output.convertState("New Mexico"), "NM");
        assertEquals(output.convertState("New York"), "NY");
        assertEquals(output.convertState("North Carolina"), "NC");
        assertEquals(output.convertState("North Dakota"), "ND");
        assertEquals(output.convertState("Northern Mariana Islands"), "MP");
        assertEquals(output.convertState("Ohio"), "OH");
        assertEquals(output.convertState("Oklahoma"), "OK");
        assertEquals(output.convertState("Oregon"), "OR");
        assertEquals(output.convertState("Palau"), "PW");
        assertEquals(output.convertState("Pennsylvania"), "PA");
        assertEquals(output.convertState("Puerto Rico"), "PR");
        assertEquals(output.convertState("Rhode Island"), "RI");
        assertEquals(output.convertState("South Carolina"), "SC");
        assertEquals(output.convertState("South Dakota"), "SD");
        assertEquals(output.convertState("Tennessee"), "TN");
        assertEquals(output.convertState("Texas"), "TX");
        assertEquals(output.convertState("Utah"), "UT");
        assertEquals(output.convertState("Vermont"), "VT");
        assertEquals(output.convertState("Virgin Islands"), "VI");
        assertEquals(output.convertState("Virginia"), "VA");
        assertEquals(output.convertState("Washington"), "WA");
        assertEquals(output.convertState("West Virginia"), "WV");
        assertEquals(output.convertState("Wisconsin"), "WI");
        assertEquals(output.convertState("Wyoming"), "WY");
        assertEquals(output.convertState("x"), "");
    }
}
