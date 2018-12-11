# EatHealthyWeb
Web application that gives healthier food recommendations

This application gives a healthier recommendation for a food item, based on user input. The healthier food item is the first food belonging to same category that has lesser sugar content.

How it works currently:
1) User uploads a food image to the start web page (UploadImage.html)
2) The app sends the image to the google vision api (https://cloud.google.com/vision/)
3) The general name of the food (e.g Rice) is gotten by matching entries in the results from the api results, and comparing the names with general names list stored in the application. It takes the first matching result.
4) The actual name of the food (e.g Uncle Ben's Long Grain rice) is gotten by sifting through the entries from the most likely classes in api results, and picking the first most likely match.
5) The actual names and general retrieved are used in searching the usda food composition database for nutritional statistics of that exact food, and a list other foods belonging to same category
6) It compares the sugar content in list of other foods belonging to same category with the exact food, and returns the first food item which has lesser sugar content as a healthier option.
