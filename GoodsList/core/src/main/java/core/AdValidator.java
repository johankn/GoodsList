package core;

import java.util.Calendar;

public class AdValidator {

    private boolean validatePriceField(String field){
        if (!(field.matches("[1-9][0-9]*"))){
            throw new IllegalArgumentException("Price field must only contain whole numbers");

        }
        return validateBlankField(field);
        
    }
    private boolean validateBlankField(String field){
        if (field.isBlank()){
            throw new IllegalArgumentException("You have to fill out all of the input fields");
        }
        return true;

    }
    private boolean validateBedrooms(String bedrooms){
        if (!(bedrooms.matches("[1-9][0-9]*"))){
            throw new IllegalArgumentException("Bedrooms field must only contain numbers");

        }
        if (Integer.parseInt(bedrooms)>10){
            throw new IllegalArgumentException("You can't have more than 10 bedrooms");
        }

        return validateBlankField(bedrooms);
    }
    private boolean validateYear(String year){
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        if (!(year.matches("[1-9][0-9]*"))){
            throw new IllegalArgumentException("Year field must only contain numbers");

        }

        if (Integer.parseInt(year)>thisYear){
            throw new IllegalArgumentException("Year can't be in the future");

        }
        
        return validateBlankField(year);
    }

    private boolean validateArea(String area){
        if (!(area.matches("[1-9][0-9]*"))){
            throw new IllegalArgumentException("Area field must only contain numbers");

        }
        //begrensing på hvor stort huset kan være?
        return validateBlankField(area);
    }
    private boolean validatePages(String pages){
        if (!(pages.matches("[1-9][0-9]*"))){
            throw new IllegalArgumentException("Pages field must only contain numbers");

        }
        if (Integer.parseInt(pages)>30000){
            throw new IllegalArgumentException("Can't be more than 30 000 pages");

        }
        return validateBlankField(pages);    
    }
    
    public boolean validateElectronics(String title, String description, String price, String brand, String type){
        return validateBlankField(title) &&validateBlankField(description) &&validateBlankField(brand) && validateBlankField(type) && validatePriceField(price);
    }
    public boolean validateClothing(String title, String description, String price, String brand, String type, String size){
        return validateBlankField(title) &&validateBlankField(description)&&validateBlankField(brand) && validateBlankField(type) && validateBlankField(size) && validatePriceField(price);
    }
    public boolean validateProperty(String title, String description, String price, String propertyType, String yearBuilt, String bedrooms,
    String area){
        return validateBlankField(title) &&validateBlankField(description)&&validateBlankField(propertyType) && validateArea(area) && validateYear(yearBuilt) && validateBedrooms(bedrooms) && validatePriceField(price);
    }
    public boolean validateVehicles(String title, String description, String price, String brand, String modelName, String modelYear){
        return validateBlankField(title) &&validateBlankField(description)&&validateBlankField(brand) && validateBlankField(modelName) && validateYear(modelYear) && validatePriceField(price);
    }
    public boolean validateBooks(String title, String description, String price, String author, String genre, String releaseYear, String pages){
        return validateBlankField(title) &&validateBlankField(description)&&validateBlankField(author) && validateBlankField(genre) && validateYear(releaseYear) && validatePages(pages) && validatePriceField(price);

    }

}
