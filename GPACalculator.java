/**
* GPA Calculator class
* Charles Tang + Divyarith Shivashok
* 3/29/22 - Algorithms project
	*/

import java.util.Scanner;

public class GPACalculator{
	/**
	* instance fields
	*/
	private String firstName;
	private String lastName;
	private int grade; 
	private int numClasses;
	
	private double GPA;
	private String[] classes;
	private String[] levels;
	private double[] grades;

	private double[] CPscale;
	private double[] Hscale;
	private double[] APscale;
	private double[] scale;

	private int comparisons;
	private int swaps;

	/**
	* Constructor for GPACalcualtor
* @param first name of student
* @param last name of student
* @param number grade of student - ex. 9, 10, 11, 12
* @param number of classes teh student takes currently
* sets the default GPA scales, and initializes all instance fields
	*/
	public GPACalculator(String firstName, String lastName, int grade, int numClasses){
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade; 
		this.numClasses = numClasses;

		this.GPA = 0.0;
		this.classes = new String[this.numClasses];
		this.levels = new String[this.numClasses];
		this.grades = new double[this.numClasses];
		
		this.CPscale = new double[] {0.0, 1.3, 1.5, 1.8, 2.0, 2.3, 2.5, 2.8, 3.0, 3.3, 3.5, 3.8, 4.0};
		this.Hscale = new double[] {0.0, 1.8, 2.0, 2.3, 2.5, 2.8, 3.0, 3.3, 3.5, 3.8, 4.0, 4.3, 4.5};
		this.APscale = new double[] {0.0, 2.3, 2.5, 2.8, 3.0 ,3.3, 3.5, 3.8, 4.0, 4.3, 4.5, 4.8, 5.0};
		this.scale = new double[] {0, 60, 63, 67, 70, 73, 77, 80, 83, 87, 90, 93, 97, Double.MAX_VALUE};
		
		this.comparisons = 0;
		this.swaps = 0;
	}

	/**
	* Method to receive student input and to input classes and grades
	*/
	
	public void setGrades(){
		for (int i = 0; i < this.numClasses; i++){
			Scanner sc = new Scanner(System.in);
			System.out.println("Name of Class " + (i+1) + ": ");
			this.classes[i] = sc.nextLine();
			System.out.println("Level of " + this.classes[i] + ": (AP, H, or CP)");
			this.levels[i] = sc.nextLine().trim();
			System.out.println("Percentage grade in " + this.classes[i] + ": (express as a number)");
			this.grades[i] = sc.nextDouble();
			System.out.println("-------------------");
		}
		mergeSort(this.grades, this.classes, this.levels);
	}

	/**
	* Prints grades of the student sorted from lowest grade to highest grade
	*/
	
	public void getGrades(){
		System.out.println("Sorted by grade from least to highest");
		System.out.println("-------------------");
		
		for (int i = 0; i < this.numClasses; i++){
			System.out.println("(" + this.levels[i] + ")" + this.classes[i] + ": " + this.grades[i] + "%");
			}
		System.out.println("-------------------");
	}

	/**
	* Getter method for name of student
	*/
	public String getName(){
		return firstName + " " + lastName;
	}

	/**
	* Getter method for grade of student
	*/
	public int getGrade(){
		return grade;
	}

	/**
	* Getter method for number of classes of student
	*/
	public int getNumClasses(){
		return numClasses;
	}

	/**
	* Getter method for number of compaarisons made by Merge Sort
	*/
	public int getComparisons(){
		return comparisons;
	}

	/**
	* Getter method for number of swaps made by Merge Sort
	*/
	public int getSwaps(){
		return swaps;
	}

	/**
	* Calculates the GPA of the student
* weights the grades accordingly to CP, H, and AP scale
* sums the point values and averages it for GPA
	*/
	public double getGPA(){
		for (int i = 0; i < this.classes.length; i++){
			if (this.levels[i].equals("CP")){
				for (int j = 0; j < this.scale.length; j++){
					if ((int) (this.grades[i] + 0.5) <= this.scale[j]){
						this.GPA += this.CPscale[j-1];
						break;
					}
				}
			}
			else if (this.levels[i].equals("H")){
				for (int j = 0; j < this.scale.length; j++){
					if ((int) (this.grades[i] + 0.5) <= this.scale[j]){
						this.GPA += this.Hscale[j-1];
						break;
					}
				}
			}
			else if (this.levels[i].equals("AP")){
				for (int j = 0; j < this.scale.length; j++){
					if ((int) (this.grades[i] + 0.5) <= this.scale[j]){
						this.GPA += this.APscale[j-1];
						break;
					}
				}
			}
		}
		return GPA / numClasses;
	}

	/**
	* Setter method for classes student takes
* @param classes array
	*/ 
	public void setClasses(String[] classes){
		assert (classes.length == numClasses);
		this.classes = classes;
	}

	/**
	* Setter method for grades of student's classes
* @param grades array
	*/ 
	public void setGrades(double[] grades){
		assert (grades.length == numClasses);
		this.grades = grades;
	}

	/**
	* Setter method for level of class (CP, H, AP) of student's classes
* @param levels array
	*/ 
	public void setLevels(String[] levels){
		assert (levels.length == numClasses);
		this.levels = levels;
	}

	/**
	* Helper method to sortGrades of student
	*/ 
	public void sortGrades(){
		mergeSort(this.grades, this.classes, this.levels);
	}

	/**
	* Helper method for mergeSort to make parameters easier
* @param array of grades
* @param array of classes
* @param array of levels of classes
	*/ 
	public void mergeSort(double[] arr, String[] classes, String[] levels){
		mergeSort(arr, classes, levels, 0, arr.length-1);
	}

	/**
	* Merge Sort implementation
* @param array of grades
* @param classes student takes
* @param array of levels of classes
* @param left index to sort in grades array
* @param right index to sort in grades array
	*/ 
	public void mergeSort(double[] arr, String[] classes, String[] levels, int left, int right){
		if (right <= left){
			return;
		}
		int middle = left + (right-left)/2;
		mergeSort(arr, classes, levels, left, middle);
		mergeSort(arr, classes, levels, middle + 1, right);
		merge(arr, classes, levels, left, middle, right);
	}

	/**
	* Merge subroutine implementation
* Uses for loop and compariosns
* sorts classes, levels, according to the sorting of arr
* @param array of grades
* @param array of classes
* @param levels of classes
* @param left index to sort in grades array
* @param middle index to sort in grades array
* @param right index to sort in grades array
	*/ 
	public void merge(double[] arr, String[] classes, String[] levels, int left, int middle, int right){
		double[] temp = new double[arr.length];
		String[] temp2 = new String[arr.length];
		String[] temp3 = new String[arr.length];
		for (int i = 0; i < arr.length; i++){
			temp[i] = arr[i];
			temp2[i] = classes[i];
			temp3[i] = levels[i];
		}

		int indexOne = left;
		int indexTwo = middle + 1;

		for (int i = left; i <= right; i++){
			this.comparisons++;
			this.swaps++;
			if (indexOne > middle){
				arr[i] = temp[indexTwo];
				classes[i] = temp2[indexTwo];
				levels[i] = temp3[indexTwo];
				indexTwo++;
			}
			else if (indexTwo > right){
				arr[i] = temp[indexOne];
				classes[i] = temp2[indexOne];
				levels[i] = temp3[indexOne];
				indexOne++;
			}
			else if (temp[indexTwo] < temp[indexOne]){
				arr[i] = temp[indexTwo];
				classes[i] = temp2[indexTwo];
				levels[i] = temp3[indexTwo];
				indexTwo++;
			}
			else{
				arr[i] = temp[indexOne];
				classes[i] = temp2[indexOne];
				levels[i] = temp3[indexOne];
				indexOne++;
			}
		}
	}

	
	
}