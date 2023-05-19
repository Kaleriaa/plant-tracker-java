package my.plant.tracker.auth.plant;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import my.plant.tracker.database.PlantDatabase;
import my.plant.tracker.database.PlantEntity;
import my.plant.tracker.database.SharedPrefsManager;
import my.plant.tracker.menu.recycler.model.PlantModel;

public class PlantViewModel extends ViewModel {

    MutableLiveData<PlantModel> plantModelLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> isPlantSaved = new MutableLiveData<>();

    public PlantViewModel() {
        super();
    }

    void initPlantScreen(PlantModel plantModel) {
        plantModelLiveData.postValue(plantModel);
    }

    void savePlant(Integer id, String title, String watering, String smallDescription, String description, String imagePath, Context context) {
        new Thread(() -> {
            PlantDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                    PlantDatabase.class, "plant-database").build();
            SharedPrefsManager sharedPrefsManager = new SharedPrefsManager(context);
            if (id == null) {
                db.plantDao().insertPlant(new PlantEntity(
                        title,
                        watering,
                        smallDescription,
                        description,
                        sharedPrefsManager.getEmail(),
                        imagePath
                ));
            } else {
                db.plantDao().insertPlant(new PlantEntity(
                        id,
                        title,
                        watering,
                        smallDescription,
                        description,
                        sharedPrefsManager.getEmail(),
                        imagePath
                ));
            }
            new Handler(Looper.getMainLooper()).post(() -> isPlantSaved.postValue(true));
        }).start();
    }

}
