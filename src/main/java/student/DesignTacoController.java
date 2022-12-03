package student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import student.Ingredient.Type;

/*
 * The above annotation creates a slf4j static logger property to this class.
 */
@Controller
/*
 * The above annotation identifies the class to be identified by component scan
 * and loaded into the application context. Spring will identify and load an 
 * instance in the Spring Application Contexts.
 * 
 * But, primary job of a controller is to handle HTTP requests. They can choose 
 * to forward the request to view to create a HTML page or it can create a 
 * response by itself.
 */
@RequestMapping("/design")
/*
 * The above annotation <b> when applied at class level </b>, signifies the 
 * kind of requests the controller class handles. This one is going to handle 
 * requests from path /design 
 */
@SessionAttributes("tacoOrder")

/**
 * 
 * The following class is going to do the following :
 * 
 * - Handle GET requests for path /design
 * - Build a taco with list of ingredients
 * - pass the data to view to render a HTML file to be shown in browser
 * 
 * When a GET request is invoked on path /design, showDesignForm method is used to
 * handle the request. It populates the model with empty taco object to be populated
 * by view. addIngredientsToModel() method is invoked, to populate various ingrdients
 * data to be shown in view. Finally, when model is populated, we are returning the
 * logical name of the view "design".
 */
public class DesignTacoController {
	
	/** 
	 * Request mapping given in the class level is further refined using
	 * annotation @GetMapping. Paired with @RequestMapping("/design"),
	 * it handles GET requests for path /design
	 * 
	 *  ----------------------------------------------------------------
	 * |   Annotation                |             Request              |
	 * |----------------------------------------------------------------|
	 * | @RequestMapping             | General-purpose request handling |
     * | @GetMapping                 | Handles HTTP GET requests        |
     * | @PostMapping                | Handles HTTP POST requests       |
     * | @PutMapping                 | Handles HTTP PUT requests        |
	 * | @DeleteMapping              | Handles HTTP DELETE requests     |
	 * | @PatchMapping               | Handles HTTP PATCH requests      |
	 *  ----------------------------------------------------------------
	 * 
	 * Please look into the design.html for the view details.
	 */
	@GetMapping
	public String showDesignForm(Model model) {
		
		// model is populated with an empty slate of an object
		// on which the view is going to make changes to.
		model.addAttribute("taco", new Taco());
		
		// this is the logical name of the view. when this path is called with 
		// GET request, the view with this logical name will be returned
		return "design";
	}
	
	
	/**
	 * In the 
	 * 
	 * Same as for GetMapping, <b>@PostMapping</b> coordinates with the 
	 * class-level <b>@RequestMapping</b> to indicates that processTaco() 
	 * should handle POST requests for <b>/design</b>.
	 * 
	 * The taco object populated by user and submitted from view of 
	 * showDesignForm method is handled by this method.
	 * 
	 * @param taco
	 * @return
	 */
	@PostMapping
	public String processTacoOrder(Taco taco) {
		
//		log.info("Processing taco " + taco);
		return "return:/orders/current";
	}
	
	/**
	 * method annotated with @ModelAttribute will also be called whenever there
	 * is a request handled by some request handler methods to populate model 
	 * object.
	 * 
	 * <p>
	 * 
	 * This model object act as an intermediary between view and controller.
	 * what is put in model is put into <b> servelet request attributes</b>
	 * that will be accessible for the view.
	 * 
	 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
	 * 
	 * Observer pattern is backbone of this architectural pattern.
	 * 
	 * 
	 * In this project for view, we are going to use thymeleaf templates for
	 * rendering html pages.
	 * 
	 * This is completely decoupled from any controller framework and depends
	 * on <b>servelet request attributes</b> for rendering data. Before Spring
	 * hands off the control to view, it copies it's model data to servelet 
	 * request attributes, so thymeleaf can access it.
	 * </p>
	 *   
	 * @param model
	 */
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
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
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	private Object filterByType(List<Ingredient> ingredients, Type type) {
		
		return ingredients.stream()
				.filter(ing -> ing.getType().equals(type))
				.collect(Collectors.toList());
	}
}

