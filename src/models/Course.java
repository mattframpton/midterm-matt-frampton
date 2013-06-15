package models;

import java.util.Enumeration;
import java.util.Hashtable;

public class Course 
{
	String index;
	String name;
	Hashtable enrollments = new Hashtable();
	
	//Constructor
	public Course (String i, String c)
	{
		this.index = i;
		this.name = c;
	}
	
	//Get course index number
	public String getIndex()
	{
		return this.index;
	}
	
	//Get course name
	public String getName()
	{
		return this.name;
	}
	
	//Enroll a student in this class.
	public Enrollment addEnrollment(Enrollment e)
	{
		//Check if student is already enrolled.
		Enumeration elements = enrollments.elements();
		while(elements.hasMoreElements())
		{
			Enrollment en = (Enrollment)elements.nextElement();
			if(en.student.RUID == e.student.RUID)
			{
				return (Enrollment) en;
			}
		}
		enrollments.put(e.student.RUID, e);
		e.setCourse(this);
		return e;
	}
	
	//Drop a student from this class
	public Enrollment removeEnrollment(String RUID)
	{
		return (Enrollment) this.enrollments.remove(RUID);
	}
	
	//Set the grade for a student in this class
	public Enrollment grade(String RUID, String grade)
	{
		Enrollment e = (Enrollment) enrollments.get(RUID);
		e.setGrade(grade);
		return (Enrollment) enrollments.put(RUID, e);
	}
}
