package org.self.learn;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorLambda {

	public static void main(String[] args) {
//		Employee e1 = new Employee(1, "raju", 12221);
//		Employee e2 = new Employee(1, "babu", 12223);
//		Employee e3 = new Employee(1, "kapil", 11221);
//		Employee e4 = new Employee(1, "arun", 12223);
//		
//		ArrayList<Employee> list = new ArrayList();
//		list.add(e1);
//		list.add(e2);
//		list.add(e3);
//		list.add(e4);
//		
//		//list.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));
//		Collections.sort(list);
//		System.out.println(list);
		
		/*
		 * Comparator<String> comparator = new Comparator<String>() {
		 * 
		 * @Override public int compare(String s1, String s2) { // TODO Auto-generated
		 * method stub return Integer.compare(s1.length(), s2.length()); }
		 * 
		 * };
		 */
		
		Comparator<String> comparator = (String s1, String s2) -> Integer.compare(s1.length(), s2.length());
		
		List<String> list = Arrays.asList("****", "***", "***********", "*");
		Collections.sort(list, comparator);
	
		for (String s : list) {
			System.out.println(s);
		}
		
	}
}

