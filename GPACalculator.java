import java.util.Scanner;

public class GPACalculator{
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

	public void getGrades(){
		System.out.println("Sorted by grade from least to highest");
		System.out.println("-------------------");
		
		for (int i = 0; i < this.numClasses; i++){
			System.out.println("(" + this.levels[i] + ")" + this.classes[i] + ": " + this.grades[i] + "%");
			}
		System.out.println("-------------------");
	}
	
	public String getName(){
		return firstName + " " + lastName;
	}

	public int getGrade(){
		return grade;
	}

	public int getNumClasses(){
		return numClasses;
	}

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

	public void setClasses(String[] classes){
		assert (classes.length == numClasses);
		this.classes = classes;
	}

	public void setGrades(double[] grades){
		assert (grades.length == numClasses);
		this.grades = grades;
	}

	public void setLevels(String[] levels){
		assert (levels.length == numClasses);
		this.levels = levels;
	}

	public void sortGrades(){
		mergeSort(this.grades, this.classes, this.levels);
	}
	public void mergeSort(double[] arr, String[] classes, String[] levels){
		mergeSort(arr, classes, levels, 0, arr.length-1);
	}
	
	public void mergeSort(double[] arr, String[] classes, String[] levels, int left, int right){
		if (right <= left){
			return;
		}
		int middle = left + (right-left)/2;
		mergeSort(arr, classes, levels, left, middle);
		mergeSort(arr, classes, levels, middle + 1, right);
		merge(arr, classes, levels, left, middle, right);
	}

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