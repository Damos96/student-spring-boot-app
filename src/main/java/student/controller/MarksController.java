package student.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import student.model.Mark;
import student.model.Semester;
import student.model.Student;
import student.model.Subject;
import student.repository.StudentRepository;

@Controller 
@RequestMapping("/marks")
public class MarksController {
	
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentRepository studentRepository;
    
    @GetMapping("/id/{studentId}")
    public String getUser(@PathVariable String studentId, Model model) {
    	Student student = studentRepository.findById(studentId).get();
    	student.setMarks(new ArrayList());
    	addIngredientsToModel(student.getMarks(), model);
    	model.addAttribute("student", student);
		return "setMarks";
	}
    
    @RequestMapping(value = "/id/{studentId}", method = RequestMethod.POST)
    public String addStudent(@PathVariable String studentId, Student newMarks) {

    	Student student = studentRepository.findById(studentId).get();
        LOG.info("Adding user : {}", student);
        student.setMarks(newMarks.getMarks());
    	studentRepository.save(student);
        LOG.info("Added user : {}", student);
        return "succesful";
    }
    
	public void addIngredientsToModel(List<Mark> marks, Model model) {
    	
		for (Semester semester : Semester.values()) {
			List<Mark> semesterMarks = new ArrayList<>();
			for (Subject subject : Subject.values()) {
				Mark mark = new Mark();
				mark.setSemester(semester);
				mark.setSubject(subject);
				marks.add(mark);
				semesterMarks.add(mark);
			}
			model.addAttribute(semester.toString().toLowerCase(), semesterMarks);
		}
		model.addAttribute("allMarks", marks);		
	}
}
