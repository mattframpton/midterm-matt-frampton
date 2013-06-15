package models;

import java.util.Enumeration;
import java.util.Hashtable;

public class CourseOfferings 
{
	Hashtable co = new Hashtable();

	//Add a course
	public Course addCourse(String index, String name)
	{
		//Checks if the index number or course name is already being used.
		if(co.get(index) != null)
			return (Course) (co.get(index));
		Course course = new Course(index, name);
		return (Course) co.put(index, course);
	}
	
	//Get a course
	public Course getCourse(String index)
	{
		return (Course) (co.get(index));
	}
}
