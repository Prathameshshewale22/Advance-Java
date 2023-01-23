package Tester;
import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import Dao.EmployeeDao;
import pojos.Department;
import pojos.Employee;


public class TestEmployeeHibenate {

	public static void main(String[] args) {
		
		try( SessionFactory sf=getFactory(); Scanner sc=new Scanner(System.in)){
			/*
			 * private int empid;
	private String firstName;
	private String lastName;
	private Department department;
	private double salary;
	private LocalDate dob;
	private boolean isPermanent;*/
			
			EmployeeDao empdao=new EmployeeDao();
			System.out.println("enter firstname,lastname,dept,sal,dob,permanent");
			String str=empdao.SaveUserDetails(new Employee(sc.next(),sc.next(),Department.valueOf(sc.next().toUpperCase()),sc.nextDouble(),LocalDate.parse(sc.next()),sc.nextBoolean()));
			System.out.println(str);
			
		}catch(RuntimeException e) {
			e.printStackTrace();
		}

	}

}
