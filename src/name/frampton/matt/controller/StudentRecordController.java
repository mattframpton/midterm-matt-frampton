package name.frampton.matt.controller;

import name.matt.frampton.model.Student;
import name.matt.frampton.model.StudentRecords;

public class StudentRecordController 
{
	StudentRecords StuRec = new StudentRecords();
	
	static StudentRecordController controller;
	public static StudentRecordController getController() 
	{
		if (controller == null)
			controller = new StudentRecordController();
		return controller;
	}
	
	//Add a student
	public Student addStudent(String RUID, String lastName, String firstName)
	{
		return StuRec.addStudent(RUID, lastName, firstName);
	}
	
	//Get a students information
	public Student getStudent(String RUID)
	{
		return (Student) (StuRec.getStudent(RUID));
	}
	
	//Retrieve the students transcript
	public String printTranscript(String RUID)
	{
		Student student = this.getStudent(RUID);
		return (String)("Student: " + student.lastName + ", " + student.firstName + student.printTranscript());
	}
	
	//Retrieve the deans 
	public String getDeansList()
	{
		return StuRec.deansCheck();
	}
}
