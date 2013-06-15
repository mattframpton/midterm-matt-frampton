package controllers;

import models.Course;
import models.CourseOfferings;


public class CourseOfferingController 
{
	CourseOfferings courseOfferings = new CourseOfferings();
	
	static CourseOfferingController controller;
	public static CourseOfferingController getController() 
	{
		if (controller == null)
			controller = new CourseOfferingController();
		return controller;
	}
	
	//Add a course
	public Course addCourse(String index, String name)
	{
		return (Course) (courseOfferings.addCourse(index, name));
	}
	
	//Retrieve a course's information
	public Course getCourse(String index)
	{
		return (Course) (courseOfferings.getCourse(index));
	}
}
