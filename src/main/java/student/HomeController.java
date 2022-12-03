package student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * this annotation denotes this class is a controller that handles requests.
 * it is also a equivalent to @Component annotation, for identifying components
 * while component scanning. This class is identified as component in the 
 * scanning phase and an instance is added to application context.
 * */
@Controller
public class HomeController {

	/**
	 * <pre>
	 * Handles request for root path '/'.
	 *  
	 *  
	 * GetMapping represents this method is responding to get request for path
	 * '/'.
	 * 
	 * The response is a sting value, whose name is same as the Thymeleaf 
	 * template you have in your resources. So, it will send the corresponding
	 * html page as response.
	 * </pre>
	 * */
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
}
