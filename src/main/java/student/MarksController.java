package student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/marks")
public class MarksController {

	@GetMapping("/current")
	public String markForm() {
		return "design";
	}
}
