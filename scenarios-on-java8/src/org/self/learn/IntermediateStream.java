package org.self.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class IntermediateStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<>();
		List<String> num = Arrays.asList("one", "two", "threee", "four");
		Predicate<String> p = n -> n.length() > 3;
		
		num.stream().peek(System.out::println).filter(p).peek(result::add);	// It does not do anything. only lazy operation declarations.
		
	}

}
