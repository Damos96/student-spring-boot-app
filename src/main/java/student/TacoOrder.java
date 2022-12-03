package student;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author sivachalabathid
 *
 * a domain class to encapture delivery information. It contains delivery 
 * address info, list of tacos which is ordered and card details for
 * payment information.
 * 
 */
@Data
public class TacoOrder {
	private String deliveryName;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryState;
	private String deliveryZip;
	private String ccNumber;
	private String ccExpiration;
	private String ccCCV;
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}
