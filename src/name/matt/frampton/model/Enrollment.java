package name.matt.frampton.model;

public class Enrollment 
{
	Student student = new Student(null, null, null);
	Course course = new Course(null, null);
	String grade = "N/A";
	
	//Constructor
	public Enrollment (Student s, Course c)
	{
		this.student = s;
		this.course = c;
	}
	
	//Set a student to this enrollment
	public void setStudent(Student student)
	{
		this.student = student;
	}
	
	//set a course to this enrollment
	public void setCourse(Course course)
	{
		this.course = course;
	}
	
	//Set the grade for this enrollment
	public void setGrade (String grade)
	{
		grade = grade.toUpperCase();
		this.grade = grade;
	}
	
	//Get the students information
	public Student getStudent()
	{
		return this.student;
	}
	
	//Get the students information
	public Course getCourse()
	{
		return this.course;
	}
	
	//Get the students grade
	public String getGrade()
	{
		return this.grade;
	}
}
