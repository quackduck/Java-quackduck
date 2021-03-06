
public class FindDuplicateInt {
	public static int[] thing0 = {0, 1, 2, 3, 4, 10, 5, 6, 7, 8, 9, 10};
	public static int[] thing1 = {0, 1, 2, 2, 3};
	public static int[] thing2 = {0, 1, 1, 2, 3};
	public static int[] thing3 = {0, 0, 1, 2, 3};
	
	public static void main(String[] args) {
		System.out.println(new FindDuplicateInt().find(thing0));
	}
	
	public int find(int[] arr) {
		
		int result = 0;
		int expectedSum = 0;
		int actualSum = 0;
		for (int i = 0; i < arr.length; i++) {
			expectedSum += i;
		}
		
		for (int i = 0; i < arr.length; i++) {
			actualSum += arr[i];
		}
		result = (arr.length - 1) - (expectedSum - actualSum);
		return result;
	}

}
