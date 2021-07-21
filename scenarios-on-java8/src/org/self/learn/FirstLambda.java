package org.self.learn;

import java.io.File;
import java.io.FileFilter;

public class FirstLambda {

	public static void main(String[] args) {

		/*
		 * FileFilter filter = new FileFilter() {
		 * 
		 * @Override public boolean accept(File pathname) { // TODO Auto-generated
		 * method stub return pathname.getName().endsWith(".java"); } };
		 */	
		
		FileFilter filter = (File pathname) -> pathname.getName().endsWith(".java"); 
		
		File dir = new File("c:/temp");
		File[] listFiles = dir.listFiles(filter);
		
		for (File file : listFiles) {
			System.out.println(file);
		}
		
		
		

	}

}
