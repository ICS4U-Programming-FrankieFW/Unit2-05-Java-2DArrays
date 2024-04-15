
/**
This program uses 2-D arrays to find student's marks and the assignment 
* @author Frankie Fox
* @version 1.0
* @since   2024-14-04
*/
import java.io.*;
import java.util.*;

public class TwoDArrays {

    public static void main(String[] args) {
        //This turns studentsFileName to student.txt
        String studentsFileName = "student.txt";
        //This is the assignmentsFileName which is assignments.txt
        String assignmentsFileName = "assignments.txt";

        //Reads students and assignments data from files
        ArrayList<String> studentArray = readFromFile(studentsFileName);
        ArrayList<String> assignmentsArray = readFromFile(assignmentsFileName);

        //Generates marks for students
        String[][] marks = generateMarks(studentArray, assignmentsArray);

        //Writes marks to CSV file
        writeMarksToCSV(marks);
    }
    //the function that reads the file
    public static ArrayList<String> readFromFile(String fileName) {
        //A new arraylist is created 
        ArrayList<String> data = new ArrayList<>();
       //This is the start of my try catch
       try {
            //This turn File file into a file
            File file = new File(fileName);
            //We initiate the new scanner to read the file
            Scanner scanner = new Scanner(file);
            //The while loop that checks if there is a nextline to read the code in the file
           //adds data if there is a next line 
           while (scanner.hasNextLine()) {
               data.add(scanner.nextLine());
           }
            //This closes the scanner 
            scanner.close();
        //This catchs non inputs that are found in the code 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }//This returns the data 
        return data;
    }
    //This is the random mark generator 
    public static String[][] generateMarks(ArrayList<String> students, ArrayList<String> assignments) {
        //This makes a new random 
        Random random = new Random();
        //This turns numAssignments into an int
        int numAssignments = assignments.size();
        //This turns marks into a string 
        String[][] marks = new String[students.size() + 1][numAssignments + 1];

        //Sets column headers
        marks[0][0] = "Student";
        for (int i = 0; i < numAssignments; i++) {
            marks[0][i + 1] = assignments.get(i);
        }

        //Generates marks for each student
        for (int i = 0; i < students.size(); i++) {
            marks[i + 1][0] = students.get(i);
            for (int j = 0; j < numAssignments; j++) {
                int mark = (int) (random.nextGaussian() * 10 + 75);
                marks[i + 1][j + 1] = Integer.toString(mark);
            }
        }

        return marks;
    }
    //The function that writes to the file marksCSV
    public static void writeMarksToCSV(String[][] marks) {
        //The try for the file writer 
        try {
            //This turns FileWriter into a new file writer 
            FileWriter csvWriter = new FileWriter("marks.csv");
            //this si the for loop that is used for each mark and it goes down a line once every mark is at 5 
            for (String[] row : marks) {
                csvWriter.append(String.join(",", row));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Marks saved to marks.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
