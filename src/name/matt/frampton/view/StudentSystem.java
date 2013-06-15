package name.matt.frampton.view;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import name.frampton.matt.controller.CourseOfferingController;
import name.frampton.matt.controller.CourseRegistrationController;
import name.frampton.matt.controller.StudentRecordController;
import name.matt.frampton.model.Course;
import name.matt.frampton.model.Student;


public class StudentSystem 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		StudentRecordController StuRecCont = StudentRecordController.getController();
		CourseRegistrationController CoReCont = CourseRegistrationController.getController();
		CourseOfferingController CoOfCont = CourseOfferingController.getController();
		
		Scanner s;
		boolean commandFromFile = false;
		if (args.length == 1) 
		{
			s = new Scanner(new FileInputStream(args[0]));
			commandFromFile = true;
		} 
		else
			s = new Scanner(System.in);
		while(true) 
		{
			if (!commandFromFile)
				System.out.print("Command: ");

			String command;
			try 
			{
				command = s.nextLine();
			} 
			catch(NoSuchElementException nsee) 
			{
				// complete processing the input from a file
				// move on to process input from keyboard
				s.close();
				s = new Scanner(System.in);
				commandFromFile = false;
				continue;
			}

			if (commandFromFile)
				System.out.println("Command: "+command);
			String[] commands = command.split(",");
			
			for(int x = 0; x < commands.length; x++)
			{
				commands[x] = commands[x].trim();
			}
			
			if ("exit".equalsIgnoreCase(commands[0])) 
			{
				// end the program
				System.out.println("Thanks for using this system. Bye!");
				break;
			}
			// Add student
			else if ("student".equalsIgnoreCase(commands[0])) 
			{
				if(commands[1].length() != 9)
				{
					System.out.println("RUID must be 9 numbers.");
				}
				else
				{
					if (commands[1] == null || commands[2] == null || commands[3] == null) 
						System.out.println("Invalid Command. Usage: student (ruid, first name, last name)");
					else
						StuRecCont.addStudent(commands[1], commands[2], commands[3]);
					
				}
			} 
			//Add a course
			else if ("course".equalsIgnoreCase(commands[0])) 
			{
				if(commands[1].length() != 5)
				{
					System.out.println("Course index must be 5 numbers.");
				}
				else
				{
					if (commands[1] == null || commands[2] == null) 
						System.out.println("Invalid Command. Usage: course (index number, course name)");
					else
						CoOfCont.addCourse(commands[1], commands[2]);
				}
			} 
			//Enroll a student in a course
			else if ("enroll".equalsIgnoreCase(commands[0])) 
			{
				if (commands[1] == null || commands[2] == null) 
					System.out.println("Invalid Command. Usage: enroll (ruid, index number)");
				else
					CoReCont.add(commands[1], commands[2]);
			} 
			//Withdraw a student from a course
			else if ("withdraw".equalsIgnoreCase(commands[0])) 
			{
				if (commands[1] == null || commands[2] == null) 
					System.out.println("Invalid Command. Usage: withdraw (ruid, index number)");
				else
					CoReCont.drop(commands[1], commands[2]);
				
			}
			//Set the grade for a student in one course
			else if ("grade".equalsIgnoreCase(commands[0])) 
			{
				if( "a".equalsIgnoreCase(commands[3]) || "b".equalsIgnoreCase(commands[3]) || "c".equalsIgnoreCase(commands[3]) || "d".equalsIgnoreCase(commands[3]) || "f".equalsIgnoreCase(commands[3]))
				{
					
					if (commands[1] == null || commands[2] == null || commands[3] == null) 
						System.out.println("Invalid Command. Usage: grade(ruid, index number, grade(A,B,C,D,F)");
					else
						CoReCont.grade(commands[1], commands[2], commands[3]);
				}
				else
				{
					System.out.println("Valid grades are A, B, C, D and F.");
				}
			} 
			//Print a students transcript
			else if ("transcript".equalsIgnoreCase(commands[0])) 
			{
				if (commands[1] == null) 
					System.out.println("Invalid Command. Usage: transcript(ruid)");
				String tran = StuRecCont.printTranscript(commands[1]);
				System.out.println(tran);
			} 
			//Print the deans list
			else if ("deanslist".equalsIgnoreCase(commands[0])) 
			{
				System.out.println(StuRecCont.getDeansList());
			} 
			//If the command is not valid, this is printed.
			else 
			{
				System.out.println("Invalid Command. Usage: exit, student (ruid/first name/last name), course (index number/course name), withdraw (ruid/index number), enroll (ruid/index number), grade(ruid/index number/grade(A,B,C), transcript(ruid), deanslist, exit");
			}
		}
	}

}