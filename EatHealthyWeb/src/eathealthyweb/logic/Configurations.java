/**
 * 
 */
package eathealthyweb.logic;

/**
 * Stores Values From Json Configuration File
 * @author mosopeogundipe
 */
public class Configurations {

	private String usdaDatabaseAPIKey;
	private String foodSearchUrl;
	private String nutrientSearchUrl;	
	
	public Configurations()
	{
		
	}
	public String getFoodDBKey() {
		return usdaDatabaseAPIKey;
	}
	public void setFoodDBKey(String foodDBKey) {
		this.usdaDatabaseAPIKey = foodDBKey;
	}
	public String getFoodSearchUrl() {
		return foodSearchUrl;
	}
	public void setFoodSearchUrl(String foodSearchUrl) {
		this.foodSearchUrl = foodSearchUrl;
	}
	public String getNutrientSearchUrl() {
		return nutrientSearchUrl;
	}
	public void setNutrientSearchUrl(String nutrientSearchUrl) {
		this.nutrientSearchUrl = nutrientSearchUrl;
	}
	
}
