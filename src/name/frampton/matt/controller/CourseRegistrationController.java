package name.frampton.matt.controller;

import name.matt.frampton.model.Course;
import name.matt.frampton.model.Enrollment;
import name.matt.frampton.model.Student;

public class CourseRegistrationController 
{
	StudentRecordController studentRecordController = StudentRecordController.getController();
	CourseOfferingController courseOfferingController = CourseOfferingController.getController();
	
	static CourseRegistrationController controller;
	public static CourseRegistrationController  getController() 
	{
		if (controller == null)
			controller = new CourseRegistrationController();
		return controller;
	}
	
	//Enroll a student in a certain course
	public Enrollment add(String RUID, String index)
	{
		Course co = courseOfferingController.getCourse(index);
		Student stu = studentRecordController.getStudent(RUID);
		Enrollment enroll = new Enrollment(stu, co);
		stu.addCourse(enroll);
		co.addEnrollment(enroll);
		return enroll;
	}
	
	//Drop a student from a certain course
	public Enrollment drop(String RUID, String index)
	{
		Student stu = studentRecordController.getStudent(RUID);
		Course co = courseOfferingController.getCourse(index);
		Enrollment enroll = new Enrollment(stu, co);
		stu.dropCourse(enroll);
		return (Enrollment) co.removeEnrollment(stu.getId());
	}
	
	//Set a students grade for a class
	public Enrollment grade(String RUID, String index, String grade)
	{
		Course co = courseOfferingController.getCourse(index);
		Student stu = studentRecordController.getStudent(RUID);
		//Calculate the gpa of the student
		stu.calcGpa(grade, index);
		return (Enrollment) co.grade(RUID, grade);
	}
}
