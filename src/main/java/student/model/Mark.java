package student.model;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import org.springframework.data.elasticsearch.annotations.Field;


public class Mark {

	@Field(type = Text)
	private Semester semester;
	
	@Field(type = Text)
	private Subject subject;

	@Field(type = Text)
	private Integer mark;

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
}
