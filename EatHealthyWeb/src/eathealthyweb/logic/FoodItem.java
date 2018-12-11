/**
 * 
 */
package eathealthyweb.logic;

/**
 * @author DELL
 *
 */
public class FoodItem {

	private String generalFoodName;
	private String actualFoodName;
	public FoodItem(String generalFoodName, String actualFoodName)
	{
		this.generalFoodName = generalFoodName;
		this.actualFoodName = actualFoodName;
	}
	public String getGeneralFoodName() {
		return generalFoodName;
	}
	public void setGeneralFoodName(String generalFoodName) {
		this.generalFoodName = generalFoodName;
	}
	public String getActualFoodName() {
		return actualFoodName;
	}
	public void setActualFoodName(String actualFoodName) {
		actualFoodName = actualFoodName;
	}	
}
