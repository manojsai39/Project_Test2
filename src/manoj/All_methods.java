package manoj;

import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.math.BigDecimal;
/**
 * We are creating all the required methods for the program in All_Methods class
 * @author Manoj
 *
 */
public class All_methods 
{
     String firstName;
     String lastName;
    String id;
     List<String> courses;
     BigDecimal tuition;
    Scanner sc = new Scanner(System.in);
    /**
     * We are setting a paramaterized constructor for the firstname and lastname 
     * @param fName
     * @param lastName
     */
     All_methods(String fName, String lastName) 
    {
        this.firstName = fName;
        this.lastName = lastName;
    }

    //We are using the getters and setters methods for acquiring all the details of the declared attributes
    public BigDecimal getTuition() 
    { 
    	return tuition; 
    }

    public void setTuition(BigDecimal money) 
    {
        this.tuition = money;
    }

    public String getName() 
    { 
    	return firstName + " " +  lastName; 
    }


    public void setFirstName(String firstName) 
    { 
    	this.firstName = firstName; 
    }

    public void setLastName(String lastName) 
    { 
    	this.lastName = lastName; 
    }

    public String getId() 
    { 
    	return id; 
    }

    public void setId(String id) 
    { 
    	this.id = id; 
    }

    public List<String> getCourses() 
    { 
    	return courses; 
    }

    public void setCourses(List<String> courses) 
    { 
    	this.courses = courses; 
    }
    
    /**
     * This will now return a randomly generated 4 character string that will combined with a number entered by the user to make the student id.
     *
     * @return The four character random string
     */
    public String randomString()
    {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int great = AB.length();
        int temp;
        String random_char = "";
        for (int i = 0; i < 4; i++)
        {
            temp = (int) (random.nextFloat() * great);
            random_char = random_char.concat(Character.toString(AB.charAt(temp)));
        }
        return random_char;
    }

    /**
     * This method will generate an id using a number from 1 - 4 given by the user and a random string as an ID.
     */
    public void makeID()
    {
        String grade;
        boolean checked = false;

        while (!checked)
        {
            System.out.println("Choose the type of course that you want to do:");
            System.out.println("1. B.Tech");
            System.out.println("2. M.Tech");
            System.out.println("3.PG"); 
            System.out.println("4.MS");
            grade = sc.nextLine();
            if (grade.length() == 1 && Integer.parseInt(grade) > 0 && Integer.parseInt(grade) < 5)
            {
                setId(grade.concat(randomString()));
                checked = true;
                
            } 
            else 
            {
                System.out.println("Please enter the input between 1 and 4");
            }
        }

    }



    /**
     * A payment system that allows the user to make multiple payments on their tuition
     */
    public void payForCourses()
    {
        String answer;
        BigDecimal payment;
        BigDecimal moneyLeftOver;

        while (getTuition().compareTo(BigDecimal.ZERO) > 0)
        {
            System.out.println("Your current balance for the course is" + getTuition()+"$");
            System.out.println("Do you want pay the balance right now?");
            System.out.println("Please enter YES if you wish to pay or else please enter NO");
            answer = sc.nextLine();

            if (answer.toLowerCase().equals("yes"))
            {
                System.out.println("How much would you like to pay now?");

                if (sc.hasNextBigDecimal())
                {
                    payment = sc.nextBigDecimal();
                    payment = payment.setScale(2, RoundingMode.HALF_UP);
                    sc.nextLine();
                    if ((payment.compareTo(BigDecimal.ZERO) > 0) && payment.compareTo(getTuition()) <= 0)
                    {
                        moneyLeftOver = getTuition().subtract(payment);
                        setTuition(moneyLeftOver);
                    } else if (payment.compareTo(getTuition()) > 0) {
                        System.out.println("You cannot pay more than your tution fee!!");
                        System.out.println("Please re-Enter the Amount");
                    } else if (payment.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("Please provide a valid amount to pay as we detect a -ve value in the amount you entered");
                    }

                } 
                else 
                {
                    sc.nextLine();
                    System.out.println("Please enter a valid inout Next Time!!");
                }

            } 
            else if (answer.toLowerCase().equals("no")) 
            {
                break;
            } 
            else 
            {
                System.out.println("You gave the wrong input either enter yes or no");
            }
        }
    }

    /**
     * Gives the student the class they entered the corresponding number for a class
     *
     * @param classes - A list that contains the classes a student has at the moment.
     * @param courseNumber - A number that represent a particular class.
     */
    public void chooseCourses(List<String> classes, int courseNumber)
    {
        switch (courseNumber)
        {
            case 1:
                if (Checking_the_enrolled_course_list(classes, "Mobile application Devolopment (MAD)"))
                {	
                    classes.add("Mobile Application devolopment");
                }   
                break;
            case 2:
                if (Checking_the_enrolled_course_list(classes, "Transportation and Logistics Management (TLM)"))
                {
                    classes.add("Transportation and Logistics Management");
                }   
                break;
            case 3:
                if (Checking_the_enrolled_course_list(classes, "Early childhood Education (ECE)"))
                {
                    classes.add("Early childhood Education");
                }    
                break;
            case 4:
                if (Checking_the_enrolled_course_list(classes, "Computer Software Testing (CST)"))
                {
                    classes.add("Computer Software Testing");
                }    
                break;
            case 5:
                if (Checking_the_enrolled_course_list(classes, "Project Management Techniques (PMT)"))
                {
                    classes.add("Project Management Techniques");
                }
                break;
            default:
                System.out.println("You gave the wrong input");
                break;
        }
    }

    /**
     * Allows the user to add classes keeping track of classes they already added and setting the new tuition the user has.
     */
    public void addCourses()
    {
        List<String> classes = new LinkedList<>();
        setCourses(classes);

        String answer;
        int nextCourse;
        BigDecimal size;
        BigDecimal cost;

        System.out.println("Do you want to add any courses? yes or no");
        answer = sc.nextLine();
        while (!answer.toLowerCase().equals("no"))
        {
            if (answer.toLowerCase().equals("yes"))
            {
                System.out.println("Please select the course from the avaialbe options below:");
                System.out.println("1. Mobile Application devolopment");
                System.out.println("2. Transportation and Logistics Management");
                System.out.println("3. Early childhood Education");
                System.out.println("4. Computer Software Testing");
                System.out.println("5. Project Management Techniques");

                if (sc.hasNextInt())
                {
                    nextCourse = sc.nextInt();
                    sc.nextLine();
                    chooseCourses(classes, nextCourse);

                } 
                else 
                {
                    System.out.println("Please eneter the input between 1 and 5");
                    sc.nextLine();
                }

            } 
            else 
            {
                System.out.println("YOu have entered the incorrect input please Enter either yes or no next time");
            }

            System.out.println("Do you wish to select any other courses");
            answer = sc.nextLine();
        }
        size = new BigDecimal(classes.size());
        cost = new BigDecimal(600);

        cost = cost.multiply(size);
        setTuition(cost);
    }

    /**
     * Make sure every class in a given list in unique.
     *
     * @param list - The list containing the student's current classes
     * @param word - The string that being checked to see if it is unique in the list
     * @return Whether or not the string is already in the list
     */
    public boolean Checking_the_enrolled_course_list(List<String> list, String word)
    {
        for (String temp : list)
        {
            if (word.equals(temp))
            {
                System.out.println("You have already enrolled for that course!");
                return false;
            }
        }
        return true;
    }

    /**
     * Prints out each student's name, id, courses, and the current balance for tuition
     *
     * @param studentList - All the students enrolled and in the list
     */
    public void displayInfo(All_methods[] studentList)
    {
        for (All_methods student : studentList)
        {
            System.out.println("Student Name: " + getName());
            System.out.println("Student ID: " + student.getId());

            if (student.getCourses().size() > 0) 
            {
                System.out.println("Student's Current Courses:" + student.getCourses());
            } 
            else 
            {
                System.out.println("Student is not enrolled into any courses");
            }
            System.out.println("Current balance of student is: $" + student.getTuition());
            System.out.println("*********************************************************");
        }

    }

    	public static void main(String args[]) 
    	{
        
            int no_of_students;
            Scanner sc = new Scanner(System.in);
            System.out.println("How many students do you want to add to database?");
            no_of_students = sc.nextInt();
            sc.nextLine();

            All_methods students[] = new All_methods[no_of_students];
            All_methods student;
            String firstName = "";
            String lastName = "";

            for (int i = 0; i < no_of_students; i++)
            {
                student = new All_methods(firstName, lastName);
                students[i] = student;

                System.out.println("Please enter the first name of Student ");
                firstName = sc.nextLine();
                student.setFirstName(firstName);

                System.out.println("Please enter the Last name of Student");
                lastName = sc.nextLine();
                student.setLastName(lastName);
                System.out.println("Thank you for entering the details");
                System.out.println("Please wait a minute while the ID is getting generated");
                student.makeID();
                student.addCourses();
                student.payForCourses();

                if (i == no_of_students - 1)
                {	
                  student.displayInfo(students);
                } 
           }
    }
}

