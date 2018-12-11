/**
 * 
 */
package eathealthyweb.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.WebDetection;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.WebDetection.WebEntity;
import com.google.protobuf.ByteString;

/**
 * @author DELL
 * Connects to and gets results from google vision api
 */
public class GoogleVisionProcessor {

	private static ImageAnnotatorClient client;	
	private byte[] imageContents;	
	
	public GoogleVisionProcessor(byte[] imageContents)
	{
		this.imageContents = imageContents;
		createClient();
	}
	
	public void createClient()
	{
		try {
			client = ImageAnnotatorClient.create();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to create google vision client");
		}
	}
	
	
	public FoodItem getFoodItem() throws IOException
	{
		FoodItem result = null;
		// Reads the image file into memory
		   //Path path = Paths.get(imageFileLocation);
		   byte[] data = imageContents;
		   ByteString imgBytes = ByteString.copyFrom(data);

		   // Builds the image annotation request
		   List<AnnotateImageRequest> requests = new ArrayList<>();
		   Image img = Image.newBuilder().setContent(imgBytes).build();
		   Feature feat = Feature.newBuilder().setType(Type.WEB_DETECTION).build();
		   AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
		       .addFeatures(feat)
		       .setImage(img)
		       .build();
		   requests.add(request);

		   // Performs label detection on the image file
		   BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
		   List<AnnotateImageResponse> responses = response.getResponsesList();

		   for (AnnotateImageResponse res : responses) {
		     if (res.hasError()) {
		       System.out.printf("Error: %s\n", res.getError().getMessage());
		       return result;
		     }
		     
		     WebDetection detection = res.getWebDetection();
		     for(WebEntity entity: detection.getWebEntitiesList())
		     {
		    	 if(entity != null)
		    	 {
		    		 String descriptionUpper = entity.getDescription().toUpperCase();
		    		 String [] descriptionSplit = descriptionUpper.split("\\s+");
			    	 for(String generalFood: Constants.GENERAL_FOOD_ITEMS)
			    	 {
			    		 //Takes the first description contains general name and other words. That's most likely the best match for the actual food name
			    		if(descriptionUpper.contains(generalFood) && descriptionSplit.length > 1)
			    		{
			    			//actualFoodName takes only the part of the most relevant to the food. It takes from beginning of the description to where the general food name occurs
			    			//E.g for general food name rice in 'Uncle Bens Express Long Grain Rice Delivered Worldwide', it takes 'Uncle Bens Express Long Grain Rice'
			    			//This increases the chance of getting relevant results when searching the USDA food database api.
			    			String actualFoodName = descriptionUpper.substring(0, descriptionUpper.indexOf(generalFood) + generalFood.length());
			    			result = new FoodItem(generalFood, Utilities.encode(actualFoodName));
			    			break;
			    		}
			    	 }
		    	 }	  
		     }
		   }
		   if(client != null)
			   client.shutdown();
		   return result;
	}
}
