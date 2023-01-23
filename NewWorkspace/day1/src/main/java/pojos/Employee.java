package pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

@Entity
public class Employee {
	/*
	 * id (auto_increment : PK) , first name ,last name , department (enum : RND,FINANCE,MARKETING,HR,BILLING), 
	 * salary, dob(LocalDate) ,isPermanent(boolean)
Annotations*/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Emp_id")
	private int empid;
	
	@Column(name="firstname",length = 20)
	private String firstName;
	
	@Column(name="lastname",length = 20)
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="dept", length = 20)
	private Department department;
	
	@Column(name="Sal")
	private double salary;
	private LocalDate dob;
	private boolean isPermanent;
	
	public Employee(){
		
	}
	
	public Employee( String firstName, String lastName, Department department, double salary, LocalDate dob,
			boolean isPermanent) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.salary = salary;
		this.dob = dob;
		this.isPermanent = isPermanent;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public boolean isPermanent() {
		return isPermanent;
	}

	public void setPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", firstName=" + firstName + ", lastName=" + lastName + ", department="
				+ department + ", salary=" + salary + ", dob=" + dob + ", isPermanent=" + isPermanent + "]";
	} 
	
	
	
	

}
