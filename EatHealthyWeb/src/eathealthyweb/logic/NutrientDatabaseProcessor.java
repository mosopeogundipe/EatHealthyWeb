/**
 * 
 */
package eathealthyweb.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import eathealthyweb.logic.FoodSearch.Item;
import eathealthyweb.logic.NutrientSearch.Nutrient;

/**
 * @author DELL
 * Connects to and gets results from the USDA Food Composition Databases API
 */
public class NutrientDatabaseProcessor {

	private HttpsURLConnection connection;
	private String response;
	private Gson gson;
	public NutrientDatabaseProcessor()
	{
		gson = new Gson();
	}
	
	public FoodSearch searchFoodByGeneralName(FoodItem item)
	{
		FoodSearch result = null;
		String url = Constants.FOOD_SEARCH_URL.replace("QUERY", Utilities.encode(item.getGeneralFoodName()));
		url = url.replace("KEY", Constants.FOOD_DB_KEY);
		System.out.println("General Food Name: " + Utilities.encode(item.getGeneralFoodName()));
		try {
			connection = sendRequest(url);
			response = getResponse(connection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error communicating with USDA Food Database API");
		}
		result = gson.fromJson(response, FoodSearch.class);
		return result;
	}
	
	public FoodSearch searchFoodByActualName(FoodItem item)
	{
		FoodSearch result = null;
		String url = Constants.FOOD_SEARCH_URL.replace("QUERY", item.getActualFoodName());
		url = url.replace("KEY", Constants.FOOD_DB_KEY);
		try {
			connection = sendRequest(url);
			response = getResponse(connection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error communicating with USDA Food Database API");
		}
		result = gson.fromJson(response, FoodSearch.class);
		return result;
	}
	
	public NutrientSearch searchNutrientByFoodNo(String foodNumber)
	{
		NutrientSearch result = null;
		String url = Constants.NUTRIENT_SEARCH_URL.replace("QUERY", foodNumber);
		url = url.replace("KEY", Constants.FOOD_DB_KEY);
		try {
			connection = sendRequest(url);
			response = getResponse(connection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error communicating with USDA Food Database API");
		}
		result = gson.fromJson(response, NutrientSearch.class);
		return result;
	}
	
	public HttpsURLConnection sendRequest(String url) throws IOException
	{
		URL urlObject = new URL(url);
		//create secure connection 
		HttpsURLConnection connection = (HttpsURLConnection)urlObject.openConnection();		
		connection.setRequestMethod("GET");		
		String contentType = "application/json";
		connection.setRequestProperty("Content-Type", contentType);
		connection.connect();
		return connection;
	}
	
	public String getResponse(HttpsURLConnection connection) throws IOException
	{
		int responseCode = connection.getResponseCode();
		StringBuffer response = new StringBuffer();
		if (responseCode == HttpsURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;			

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} else {
			System.out.println("POST request unsuccessful");
		}		
		return response.toString();
	}
	
	/**
	 * Returns the healthier option for the given food item
	 * Healthier option is gotten by taken the first related food item with lesser sugar content.
	 * @param item
	 * @return
	 */
	public String getHealthierFood(FoodItem item)
	{
		String result = "";	
		if(item == null)			
			return result = "No results for Image";
		FoodSearch actualFood = searchFoodByActualName(item);
		FoodSearch otherSimilarFoods = searchFoodByGeneralName(item);
		NutrientSearch actualFoodsNutrients = null;
		NutrientSearch otherSimilarFoodsNutrients = null;
		Optional<Nutrient> actualFoodsSugar = null;
		Optional<Nutrient> otherSimilarFoodsSugar = null;
		if(actualFood == null)
		{
			//TODO: If searchFoodByActualName returns null, then search food by General name, and get first healthier option from there!
			result = "Food from image uploaded does not exist in database. Kindly try with another food item";
		}
		else
		{
			if(actualFood == null || actualFood.getList() == null || actualFood.getList().getItem().size() == 0 || actualFood.getList().getItem().get(0) == null)
			{
				result = "Data 1 Not Properly Formed for Request. Please Try Again. If Error Persists, use another image";
				return result;
			}
			if(otherSimilarFoods == null || otherSimilarFoods.getList() == null || otherSimilarFoods.getList().getItem().size() == 0)
			{
				result = "Data 2 Not Properly Formed for Request. Please Try Again. If Error Persists, use another image";
				return result;
			}
			actualFoodsNutrients = searchNutrientByFoodNo(actualFood.getList().getItem().get(0).getNdbno());
			//Below line of code finds the first Nutrient object containing "sugar". Sugar is the parameter we use to determine healtier option
			actualFoodsSugar = actualFoodsNutrients.getReport().getFood().getNutrients().stream().filter(x -> x.getName().toUpperCase().contains(Constants.SUGAR)).findFirst();
			if(!actualFoodsSugar.isPresent())
			{
				return result = "No results for Image. Sugar content unavailable";
			}
			double actualFoodSugarNum = Double.valueOf(actualFoodsSugar.get().getValue());
			if(actualFoodSugarNum <= 0)
			{
				return result = "The image you uploaded is the healthiest kind of " + item.getGeneralFoodName() + " there is!" + " It has zero sugars.";
			}
			for(Item element: otherSimilarFoods.getList().getItem())
			{
				otherSimilarFoodsNutrients = searchNutrientByFoodNo(element.getNdbno());
				otherSimilarFoodsSugar = otherSimilarFoodsNutrients.getReport().getFood().getNutrients().stream().filter(x -> x.getName().toUpperCase().contains(Constants.SUGAR)).findFirst();
				if(!otherSimilarFoodsSugar.isPresent())
					continue;				
				double otherFoodsSugarNum = Double.valueOf(otherSimilarFoodsSugar.get().getValue());
				if(otherFoodsSugarNum < actualFoodSugarNum)
				{
					double scale = Math.pow(10, 2);
					double percentDiff = ((actualFoodSugarNum - otherFoodsSugarNum)/actualFoodSugarNum) * 100;
					percentDiff = Math.round(percentDiff * scale) / scale;
					String manufacturer = (element.getManu().equals("none")) ? "N/A" : element.getManu();
					//result = element.getName() + " is a healther option." + "\n" + "Made by: " + manufacturer + "\n" + "It has "  + percentDiff + " percent less sugar";
					//result = "Healthier option found ";
					result = " A healthier option is: " + element.getName() + "\n" + "Made by: " + manufacturer + "\n" + "It has "  + percentDiff + " percent less sugar";
					result = Utilities.encodeHtml(result);
					result = Utilities.encodeJavaScript(result);
					break;
				}				
			}
			if(result.isEmpty())
				result = "No healthier option found. Kindly go with the image uploaded";
		}
		return result;
	}
}
