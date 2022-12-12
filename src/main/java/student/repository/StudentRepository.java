package student.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import student.model.Student;

@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, String> {

	Page<Student> findById(String studentId, Pageable pageable);
	
	List<Student> findBySemester(Semester semester);
}
