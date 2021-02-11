import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class RadixSort {
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}

	public static void radixSort(int[] array, int maxPowerOf10) {

		List<ArrayDeque<Integer>> queueArray = new ArrayList<ArrayDeque<Integer>>();

		for (int queueNum = 0; queueNum < 10; queueNum++) {
			queueArray.add(new ArrayDeque<Integer>());
		}

		for (int powerOf10 = 1; powerOf10 <= maxPowerOf10; powerOf10 = powerOf10 * 10) {
			for (int item = 0; item < array.length; item++) {
				int digit = getDigit(array[item], powerOf10);
				queueArray.get(digit).add(array[item]);
			}
			int item = 0;
			for (int queueNum = 0; queueNum < 10; queueNum++) {
				while (!queueArray.get(queueNum).isEmpty()) {
					array[item] = ((Integer) queueArray.get(queueNum).pop()).intValue();
					item++;
				}
			}
		}
	}

	public static int getDigit(int number, int powerOf10) {
		return (number / powerOf10) % 10;
	}

	public static void main(String[] args) {
		int[] array = {1000, 4, 25, 319, 88, 51, 3430, 8471, 701, 1, 2989, 657, 713};
		printArray(array);
		int max = array[0], p = 1;
		for (int i = 0; i < array.length; i++)
			if(array[i] > max)
				max = array[i];
		while(max != 0) {
			p *= 10; max /= 10;
		}
		p /= 10;
		radixSort(array, p);
		printArray(array);

	}

}
