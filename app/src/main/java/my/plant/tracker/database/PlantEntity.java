package my.plant.tracker.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Plant")
public class PlantEntity {

    @PrimaryKey(autoGenerate = true)
    public int id = 0;
    String title;
    String watering;
    String shortDescription;
    String description;
    String photoPath;
    String userEmail;

    public PlantEntity(String title, String watering, String shortDescription, String description, String userEmail, String photoPath) {
        this.title = title;
        this.watering = watering;
        this.description = description;
        this.shortDescription = shortDescription;
        this.userEmail = userEmail;
        this.photoPath = photoPath;
    }

    @Ignore
    public PlantEntity(int id, String title, String watering, String shortDescription, String description, String userEmail, String photoPath) {
        this.id = id;
        this.title = title;
        this.watering = watering;
        this.description = description;
        this.shortDescription = shortDescription;
        this.userEmail = userEmail;
        this.photoPath = photoPath;
    }

    public String getTitle() {
        return title;
    }

    public String getWatering() {
        return watering;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
