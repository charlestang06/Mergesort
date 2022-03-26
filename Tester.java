public class Tester{
	public static void main(String[] args){
		GPACalculator Charles = new GPACalculator("Charles", "Tang", 10, 7);
		Charles.setGrades(new double[] {100, 100, 100, 100, 100, 100, 100});
		Charles.setClasses(new String[] {"English", "Chemistry", "USH", "Spanish", "Calc", "CSA", "Microec"});
		Charles.setLevels(new String[] {"AP", "AP", "AP", "AP", "AP", "AP", "AP"});
		Charles.sortGrades();
		System.out.println(Charles.getGPA());
	}
}