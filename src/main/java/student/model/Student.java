package student.model;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "class_report")
public class Student {
	
    @Id
    private String id;

    @Field(type = Text)
    private String name;
    
    @Field(type = FieldType.Text)
    private Subject subject;
    
    @Field(type = FieldType.Nested)
    private List<Mark> marks = new ArrayList<Mark>();
    
    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
    
    public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

    @Override
    public String toString() {
        return "Article{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", marks=" + marks  + '}';
    }
}