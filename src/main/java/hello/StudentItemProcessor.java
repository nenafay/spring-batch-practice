package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class StudentItemProcessor implements ItemProcessor<Student, Student>{
	private static final Logger log = LoggerFactory.getLogger(StudentItemProcessor.class);
	
	@Override
	public Student process (final Student student) throws Exception {
		final String classroomId = student.getClassroomId().toUpperCase();
		final Long studentId = student.getStudentId();
		final String studentFirstName = student.getStudentFirstName().toUpperCase();
		final String studentMidInit = student.getStudentMidInit().toUpperCase();
		final String studentLastName = student.getStudentLastName().toUpperCase();	
	
		final Student transformedStudent = new Student(classroomId, studentId, studentFirstName, studentMidInit, studentLastName);
		
		log.info("Converting(" + student + ") into(" + transformedStudent + ")");
		
		return transformedStudent;
	}
}