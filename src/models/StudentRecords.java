package models;

import java.util.Enumeration;
import java.util.Hashtable;

import controllers.StudentRecordController;


public class StudentRecords 
{	
	Hashtable stu = new Hashtable();
	
	//Add a student
	public Student addStudent(String r, String f, String l)
	{
		//Check if student or RUID is already in the system.
		if(stu.get(r) != null)
			return (Student) stu.get(r);
		else
		{
			Student student = new Student(r, f, l);
			return (Student) stu.put(r, student);
		}
	}
	
	//Retrieve a students information
	public Student getStudent(String r)
	{
		return (Student) (stu.get(r));
	}
	
	//Check if the student is eligible for the deans list
	public String deansCheck()
	{
		String deans = "Deans List: \n";
		Enumeration elements = stu.elements();
		while(elements.hasMoreElements())
		{
			Student s = (Student)elements.nextElement();
			deans += s.checkDeans();
		}
		return deans;
	}
}
