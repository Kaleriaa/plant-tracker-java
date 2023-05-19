package my.plant.tracker.menu.recycler.model;

import java.io.Serializable;

public class PlantModel extends RecyclerModel implements Serializable {

    Integer id;
    String title;
    String watering;
    String shortDescription;
    String description;
    String photoPath;
    String userEmail;

    public PlantModel(Integer id,String title, String watering, String shortDescription, String description, String userEmail, String photoPath) {
        this.id = id;
        this.title = title;
        this.watering = watering;
        this.description = description;
        this.shortDescription = shortDescription;
        this.userEmail = userEmail;
        this.photoPath = photoPath;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getWatering() {
        return watering;
    }
}
