import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class SelectionSort {
    
    private ArrayList<Student> inputArray = new ArrayList<Student>();
    
    //Just To fetch display purpose
    public ArrayList<Student> getSortedArray() {
        return inputArray;
    }
 
    public SelectionSort(ArrayList<Student> inputArray){
        this.inputArray = inputArray;
    }   
    
    public void sortGivenArray(){
        
        double smallInt = 0.0;   //Student
        int j=0;
        int smallIntIndex = 0;      
        
        for(int i=1;i<inputArray.size();i++){
            
            smallInt = inputArray.get(i-1).getTotalMarks(); //Student
            smallIntIndex = i-1;
            
            for(j= i; j < inputArray.size() ; j++){
                
                if(inputArray.get(j).getTotalMarks() < smallInt){
                    
                    smallInt = inputArray.get(j).getTotalMarks();
                    smallIntIndex = j;
                }
            }
        
            //Swap the smallest element with the first element of unsorted subarray
            swap(i-1, smallIntIndex);           
        }
    }
    public void sortArray(){
        
        double smallInt = 0.0;   //Student
        int j=0;
        int smallIntIndex = 0;      
        
        for(int i=1;i<inputArray.size();i++){
            
            smallInt = inputArray.get(i-1).getTotalMarks(); //Student
            smallIntIndex = i-1;
            
            for(j= i; j < inputArray.size() ; j++){
                
                if(inputArray.get(j).getTotalMarks() > smallInt){
                    
                    smallInt = inputArray.get(j).getTotalMarks();
                    smallIntIndex = j;
                }
            }
        
            //Swap the smallest element with the first element of unsorted subarray
            swap(i-1, smallIntIndex);           
        }
    }
    
    public void swap(int sourceIndex,int destIndex){        
        Student temp = inputArray.get(destIndex);
        inputArray.set(destIndex, inputArray.get(sourceIndex));
        inputArray.set(sourceIndex, temp);
    }
 
}
public class Student {

    private ArrayList<Student> list;
    private String fullName;
    private int id;
    private double a1, a2, a3;

    Student(String first_name, String last_name, String id, String a1, String a2, String a3) {

        this.fullName = first_name + " " + last_name;
        this.id = Integer.parseInt(id);

        //Handling possinle error with conversion from string to double
        if (!a1.contains(".")) {
            this.a1 = Double.parseDouble(a1 + ".0");

        } else {
            this.a1 = Double.parseDouble(a1);
        }
        if (!a2.contains(".")) {
            this.a2 = Double.parseDouble(a2 + ".0");
        } else {
            this.a2 = Double.parseDouble(a2);
        }
        if (!a3.contains(".")) {
            this.a3 = Double.parseDouble(a3 + ".0");
        } else {
            this.a3 = Double.parseDouble(a3);
        }
    }
    
    public static ArrayList<Student> readFile() {
        String line = "";
        String splitBy = ",";
        ArrayList<Student> marks = new ArrayList<Student>();
        try {
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("prog5001_students_grade_2022.csv"));
            int counter = -1;   //To skip first two header lines
            while (((line = br.readLine()) != null)) //returns a Boolean value  
            {
                if (counter > 0) {
                    String[] student = line.split(splitBy);    // use comma as separator 
                    if (student.length == 6) {      //Ignores the last section(Remarks) in the file
                        Student student_data = new Student(student[0], student[1], student[2], student[3], student[4], student[5]);
                        marks.add(student_data);
                    }
                    else if(student.length == 4){//If a2, a3 are zero
                        Student student_data = new Student(student[0], student[1], student[2], student[3], "0.0", "0.0");
                        marks.add(student_data);
                    }
                    else if(student.length == 5){//If a3 is zero
                        Student student_data = new Student(student[0], student[1], student[2], student[3], student[4], "0.0");
                        marks.add(student_data);
                    }

                } else {
                    counter++;
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }//File read complete
        return marks;
    }

    public double getTotalMarks() {
        return a1 + a2 + a3;
    }

    public static void displayTotalMarks(ArrayList<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).fullName + ", " + list.get(i).getTotalMarks());
        }
    }

    public static void printTop10Students(ArrayList<Student> list) {   
        //System.out.println(".....Printing first Entry of Top10.....");
        //Collections.sort(list, new MarksComparator());
        SelectionSort sort = new SelectionSort(list);
        sort.sortGivenArray();
        ArrayList<Student> sortedArray = sort.getSortedArray();
        System.out.println("......First 10 Students.......");
        int j = sortedArray.size()-1;
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + ". " + list.get(j).fullName + ", Total marks = " + list.get(j).getTotalMarks());
            j--;
        }
        sort.sortArray();
        ArrayList<Student> newList = sort.getSortedArray();
        System.out.println(".........Last 10 Students.........");
        int k = newList.size()-1;
        for(int i = 0;i < 10; i++){
            //System.out.println(i + ". " + sortedArray.get(i).fullName + ", Total marks = " + list.get(i).getTotalMarks());
            System.out.println(i+1 + ". " + newList.get(k).fullName + ", Total marks = " + newList.get(k).getTotalMarks());
            k--;
        }
        
        
    }

    public static void printStudentDetails(ArrayList<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[Name=" + list.get(i).fullName + ", ID=" + list.get(i).id + ", a1=" + list.get(i).a1 + ", a2=" + list.get(i).a2 + ", a3= " + list.get(i).a2 + "]");
        }
    }//PrintStudentDetailsEnded

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Student> student_list = readFile();
        //sortList(student_list);
        boolean appRunning = true;
        while (appRunning) {
            System.out.println("Welcome to selection menu\n"
                    + "1. Enter 1 to student information and assignment marks\n"
                    + "2. Enter 2 to display total marks of all students\n"
                    + "3. Enter 3 to display student list with less than a threshold\n"
                    + "4. Enter 4 to display First 10 and Last 10 Students\n"
                    + "5. Enter 5 to exit the menu\n");
            int option = input.nextInt();
            switch (option) {
                case 1: {
                    printStudentDetails(student_list);
                    break;
                }
                case 2: {
                    displayTotalMarks(student_list);
                    break;
                }
                case 3: {
                    System.out.println("Enter threshold:");
                    int threshold = input.nextInt();
                    if (threshold > 0) {
                        System.out.println("........Printing students with marks less than " + threshold + " ......\n");
                        for (int i = 0; i < student_list.size(); i++) {
                            Student student = student_list.get(i);
                            if (threshold > student.getTotalMarks()) {
                                System.out.println(student.fullName + ", total marks = " + student.getTotalMarks());
                            }
                            //displayTotalMarks(student_list.get(i));
                        }

                    } else {
                        System.out.println("Invalid threshold!");
                    }
                    break;
                }
                case 4: {
                    printTop10Students(student_list);
                    break;
                }
                case 5: {
                    System.out.println("Exiting the program.....");
                    appRunning = false;
                    break;
                }

            }
        }
    }
    
}
