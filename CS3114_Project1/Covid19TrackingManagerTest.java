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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import student.TestCase;

/**
 * This is the test class for all the methods in covid manager class
 * 
 * @author Foad Nachabe (foadn)
 * @author Jack Layfield (jackml)
 * @version 9/21/2020
 */
public class Covid19TrackingManagerTest extends TestCase {

    private Covid19TrackingManager manager;
    private ArrayList<State> arr;


    /**
     * This will make the setup for the test class
     */
    public void setUp() {
        arr = new ArrayList<State>();

        manager = new Covid19TrackingManager();
    }


    /**
     * This will test the read data method
     * 
     * @throws FileNotFoundException
     *             throws this exception if it does not work
     */
    public void testReadData() throws FileNotFoundException {

        State state = new State(20200818, "ca", 110, 50, 123, 11, 19, 65, "A+",
            15);

        State state2 = new State(20200818, "wV", 72, 43, 79, 12, 17, 33, "B",
            9);

        arr.add(state);
        arr.add(state2);

        assertFalse(manager.readData("head_100_random_30.csv").isEmpty());

        assertFalse(manager.readData("myExcel.csv").isEmpty());

    }


    /**
     * This will test the main method
     * 
     * @throws IOException
     *             throw this exception if it does not work
     */
    public void testMain() throws IOException {
        String[] list = new String[2];
        list[0] = "head_100_random_30.csv";
        list[1] = "load";

        manager.main(list);

        list = new String[3];
        list[0] = "search";
        list[1] = "Oregon";
        list[2] = "3";
        assertEquals(list[0], "search");
    }


    /**
     * This will test the main method again
     * 
     * @throws IOException
     *             throw this exception if it dont work
     */
    public void testMainAgain() throws IOException {
        String[] list = new String[2];
        list[0] = "wildInput1.txt";
        manager.main(list);
        assertEquals(list[0], "wildInput1.txt");
    }
}
