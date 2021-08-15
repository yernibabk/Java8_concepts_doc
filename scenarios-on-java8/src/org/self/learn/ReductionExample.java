package org.self.learn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReductionExample {

	public static void main(String[] args) {
		
		/*
		 * List<Integer> list = Arrays.asList(10);
		 * 
		 * Integer red = list.stream().reduce(0, Integer::sum);
		 * 
		 * System.out.println("red = "+ red);
		 */
		
		/*
		 * List<Integer> list = Arrays.asList(-10); Integer red =
		 * list.stream().reduce(0, Integer::max);
		 * 
		 * System.out.println("red = "+ red); // output: 0 //max methods doesn't have
		 * identity value.
		 */
		
		List<Integer> list = Arrays.asList();
		Optional<Integer> red = list.stream().reduce(Integer::max);
		
		System.out.println("red = "+ red); // output: 0
		//max methods doesn't have identity value.
	}

}
