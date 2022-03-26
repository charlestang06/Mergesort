/**
* Runs the GPA Calculator program
* Charles Tang + Divyarith Shivashok
* 3/29/22 - Algorithms project
	*/

import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("First name?");
		String fName = sc.nextLine().trim();
		System.out.println("Last name?");
		String lName = sc.nextLine().trim();
		System.out.println("Grade");
		int grade = sc.nextInt();
		System.out.println("Number of classes");
		int numClasses = sc.nextInt();
		
		GPACalculator Student = new GPACalculator(fName, lName, grade, numClasses);
		
		Student.setGrades();
		Student.getGrades();
		System.out.println(Math.round(Student.getGPA() * 100) / 100);
		System.out.println("Comparisons: " + Student.getComparisons() + ", Swaps: " + Student.getSwaps());
	}
}