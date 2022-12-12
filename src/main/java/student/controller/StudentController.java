package student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import student.model.Student;
import student.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping
	public String showStudentForm(Model model) {

		// model is populated with an empty slate of an object
		// on which the view is going to make changes to.
		model.addAttribute("student", new Student());

		// this is the logical name of the view. when this path is called with
		// GET request, the view with this logical name will be returned
		return "addStudent";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addStudent(Student student) {

		LOG.info("Adding student : {}", student);
		studentRepository.save(student);
		LOG.info("Added student : {}", student);
		return "succesful";
	}

}
