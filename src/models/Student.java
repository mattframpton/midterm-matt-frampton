package models;

import java.util.Enumeration;
import java.util.Hashtable;



public class Student 
{
	String RUID;
	public String firstName;
	public String lastName;
	public double gpa = 0.0;
	//Keeps track of how many classes the student is enrolled in that has a grade recorded for it.
	public int numOfGrades = 0;
	Hashtable enrollments = new Hashtable();
	
	public Student(String r, String f, String l)
	{
		this.RUID = r;
		this.firstName = f;
		this.lastName = l;
	}
	
	//Get student ID
	public String getId()
	{
		return this.RUID;
	}
	
	// Get student first name
	public String getFirstName()
	{
		return this.firstName;
	}
	
	//Get student last name
	public String getLastName()
	{
		return this.lastName;
	}
	
	//Enroll student in a course
	public Enrollment addCourse(Enrollment e)
	{
		//Check if student is already enrolled in course.
		Enumeration elements = enrollments.elements();
		while(elements.hasMoreElements())
		{
			Enrollment en = (Enrollment)elements.nextElement();
			if(en.course.index == e.course.index)
			{
				return (Enrollment) en;
			}
		}
		enrollments.put(e.course.index, e);
		e.setStudent(this);
		return e;
	}
	
	//Drop student from a course
	public Enrollment dropCourse(Enrollment e)
	{
		return (Enrollment)enrollments.remove(e.course.index);
	}

	//Print one students transcript (course index, course name, course grade)
	public String printTranscript()
	{
		String s = "";
		String id = this.getId();
		Enumeration keys = enrollments.keys();
		while(keys.hasMoreElements())
		{
			Object key = keys.nextElement();
			Object value = enrollments.get(key);
			Enrollment co = (Enrollment) (enrollments.get(key));
			s += "\n\n" + "  Index Number: " + co.course.index +"\n" + "  Course Name: " + co.course.name + "\n" + "  Grade: " + co.grade;
		}
		return s;
	}
	
	//Calculate the students GPA
	public void calcGpa(String grade, String index)
	{
		//First check if grade already exists for this enrollment. If so, reset it to 0 and not being counted.
		Enrollment en = (Enrollment) (enrollments.get(index));
		double gra = 0.0;
		if(en.grade != "N/A")
		{
			String currentGr = en.grade;
			if(grade.equalsIgnoreCase("a"))
				gra = 4.0;
			else if(grade.equalsIgnoreCase("b"))
				gra = 3.0;
			else if(grade.equalsIgnoreCase("c"))
				gra = 2.0;
			else if(grade.equalsIgnoreCase("d"))
				gra = 1.0;
			else if(grade.equalsIgnoreCase("f"))
				gra = 0.0;
			this.gpa = ((this.gpa * this.numOfGrades) - gra) / (this.numOfGrades - 1);
			this.numOfGrades--;
		}
		//Now add new grade
		if(grade.equalsIgnoreCase("a"))
			gra = 4.0;
		else if(grade.equalsIgnoreCase("b"))
			gra = 3.0;
		else if(grade.equalsIgnoreCase("c"))
			gra = 2.0;
		else if(grade.equalsIgnoreCase("d"))
			gra = 1.0;
		else if(grade.equalsIgnoreCase("f"))
			gra = 0.0;
		this.gpa = ((this.gpa * this.numOfGrades) + gra) / (this.numOfGrades + 1);
		this.numOfGrades++;
		return;
	}
	
	//Check if the student is eligible for the deans list
	public String checkDeans()
	{
		if(this.gpa == 4.0)
			return (this.firstName +" " +this.lastName +"\n");
		else
			return "";
	}
}
