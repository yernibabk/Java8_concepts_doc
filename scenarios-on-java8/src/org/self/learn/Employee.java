package org.self.learn;

public class Employee implements Comparable {
	
	private int id;
	private String name;
	private int salary;
	
	public Employee(int id, String name, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Employee e1 = (Employee) o;
		int isSalCompare = Integer.compare(this.salary, e1.salary);
		if(isSalCompare == 0) {
			return name.compareTo(e1.name);
		}
		return isSalCompare;
	}
	
	
	
}
