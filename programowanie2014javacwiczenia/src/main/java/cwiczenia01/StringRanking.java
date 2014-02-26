package cwiczenia01;

import java.util.Random;

public class StringRanking {
	private String[] array;
	private int[] priority;
	private int capacity;
	private int size;
	
	StringRanking() {
		capacity = 4;
		array = new String[capacity];
		priority = new int[capacity];
		size = 0;
	}
	
	private void swap (String a, String b) {
		String t = a;
		a = b;
		b = t;
	}
	
	private void realloc () {
		capacity *= 2;
		
		String[] oldArray = array;
		int[] oldPriority = priority;
		
		array = new String[capacity];
		priority = new int[capacity];
		
		System.arraycopy(oldArray, 0, array, 0, size);
		System.arraycopy(oldPriority, 0, priority, 0, size);
	}
	
	public void push (String s, int i) {
		int freePos = -1;
		for (int j = 0; j < size; ++j) {
			if (array[j].equals(s))
				return;
			
			if (array[j] == null)
				freePos = j;
		}
		
		if (freePos != -1) {
			array[freePos] = s;
			priority[freePos] = i;
			for (int j = freePos; j < size; ++j) {
				if (i == priority[j])
					swap(s,array[j]);
			}
		}
		else if (size == capacity) {
			realloc();
			array[size] = s;
			priority[size++] = i;
		}
		else {
			array[size] = s;
			priority[size++] = i;
		}
	}
	
	public String pop () {
		String result = null;
		int highestPriority = -1;
		int index = -1;
		
		for (int i = 0; i < size; ++i) {
			if (priority[i] > highestPriority) {
				highestPriority = priority[i];
				result = array[i];
				index = i;
			}
		}
		
		if (highestPriority != -1) {
			array[index] = null;
			priority[index] = -1;
		}
		
		return result;
	}
	
	public String popRandom () {
		int indexArraySize = 0;
		int indexArray[] = new int[size];
		
		for (int i = 0; i < size; ++i) {
			if (array[i] != null)
				indexArray[indexArraySize++] = i;
		}
		
		if (indexArraySize == 0) return null;
		else {
			Random generator = new Random();
			return array[indexArray[generator.nextInt(indexArraySize)]];
		}
	}
	
	public static void main(String[] args) {
        StringRanking temp = new StringRanking();
        temp.push("?",0);
        temp.push("if",750);
        temp.push(new String("wood"), 1);
        temp.push("would", 1000);
        temp.push("a woodchuck", 1000);
        temp.push("chuck", 999);
        temp.push("could", 500);
        temp.push(new String("a woodchuck"), 625);
        temp.push("How much", 2000);
        temp.push(new String("chuck"), 100);
        temp.push("wood", 1500);
        temp.push("could", 500);
        temp.push("would", 100);
        System.out.println(temp.popRandom());
        /*String tempStr;
        for(tempStr = temp.pop(); tempStr != null; tempStr = temp.pop())
            System.out.println(tempStr);*/
    }
}
