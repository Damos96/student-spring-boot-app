package student.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import student.model.Semester;
import student.model.Student;

@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {

	Page<Student> findById(String studentId, Pageable pageable);
	
	List<Student> findBySemester(Semester semester);
}
