public class MergeSort{
	public static int comparisons = 0;
	public static int swaps = 0;
	
	public static void mergeSort(int[] arr){
		mergeSort(arr, 0, arr.length-1);
	}
	
	public static void mergeSort(int[] arr, int left, int right){
		if (right <= left){
			return;
		}
		int middle = left + (right-left)/2;
		mergeSort(arr, left, middle);
		mergeSort(arr, middle + 1, right);
		merge(arr, left, middle, right);
	}

	public static void merge(int[] arr, int left, int middle, int right){
		int[] temp = new int[arr.length];
		for (int i = 0; i < arr.length; i++){
			temp[i] = arr[i];
		}

		int indexOne = left;
		int indexTwo = middle + 1;

		for (int i = left; i <= right; i++){
			comparisons++;
			swaps++;
			if (indexOne > middle){
				arr[i] = temp[indexTwo++];
			}
			else if (indexTwo > right){
				arr[i] = temp[indexOne++];
			}
			else if (temp[indexTwo] < temp[indexOne]){
				arr[i] = temp[indexTwo++];
			}
			else{
				arr[i] = temp[indexOne++];
			}
		}
	}

	public static void main(String[] args){
		int[] test = new int[] {1, 4, 7, 3, 1};
		mergeSort(test);
		for (int i = 0; i < test.length; i++){
			System.out.println(test[i]);
		}
		System.out.println("Comparisons: " + comparisons);
		System.out.println("Swaps: " + swaps);
	}
}