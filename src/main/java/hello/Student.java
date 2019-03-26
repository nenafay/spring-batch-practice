package hello;

public class Student {
	private String classroomId;
	private Long studentId;
	private String studentFirstName;
	private String studentMidInit;
	private String studentLastName;
	
	public Student() {	
	}

	public Student(String classroomId, Long studentId, String studentFirstName, String studentMidInit, String studentLastName) {
		this.classroomId = classroomId;
		this.studentId = studentId;
		this.studentFirstName = studentFirstName;
		this.studentMidInit = studentMidInit;
		this.studentLastName = studentLastName;
	}
	
	//getters//
	public String getClassroomId() {
		return classroomId;
	}
	
	public Long getStudentId() {
		return studentId;
	}
	
	public String getStudentFirstName() {
		return studentFirstName;
	}
	
	public String getStudentMidInit() {
		return studentMidInit;
	}
	
	public String getStudentLastName(){
		return studentLastName;
	}
	
	//setters//
	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}
	
	public void setStudentId (Long studentId) {
		this.studentId = studentId;
	}
	
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	
	public void setStudentMidInit (String studentMidInit) {
		this.studentMidInit = studentMidInit;
	}
	
	public void setStudentLastName (String studentLastName) {
		this.studentLastName = studentLastName;
	}
	
	@Override
	public String toString() {
		return "classroomId:" + classroomId + ", studentId:" + studentFirstName + ", studentFirstName:" + studentMidInit + ", studentMidInit:" + studentLastName + ", studentLastName:";		
	}
}