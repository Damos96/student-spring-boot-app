package student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import student.Ingredient.Type;
import student.model.Semester;
import student.model.Student;
import student.model.Subject;
import student.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentRepository studentRepository;
    
    @GetMapping
	public String showDesignForm(Model model) {
		
		// model is populated with an empty slate of an object
		// on which the view is going to make changes to.
		model.addAttribute("student", new Student());
		
		// this is the logical name of the view. when this path is called with 
		// GET request, the view with this logical name will be returned
		return "addStudent";
	}
    
    @RequestMapping(method = RequestMethod.POST)
    public String addStudent(Student student) {
    	
        LOG.info("Adding user : {}", student);
    	studentRepository.save(student);
        LOG.info("Added user : {}", student);
        return "succesful";
    }
    
	@ModelAttribute
	public void addSubjectsAndSemestersToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), 
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), 
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE), 
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE), 
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		Type[] types = Ingredient.Type.values();
		
		// filters the list of ingredients based on type and populate the 
		// model
		model.addAttribute("semester", Semester.values());
		model.addAttribute("subjects", Subject.values());
	}

	private Object filterByType(List<Ingredient> ingredients, Type type) {
		
		return ingredients.stream()
				.filter(ing -> ing.getType().equals(type))
				.collect(Collectors.toList());
	}
}
