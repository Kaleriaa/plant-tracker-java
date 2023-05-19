package my.plant.tracker.menu;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import my.plant.tracker.database.PlantDatabase;
import my.plant.tracker.database.PlantEntity;
import my.plant.tracker.database.SharedPrefsManager;
import my.plant.tracker.menu.recycler.model.AddPlantModel;
import my.plant.tracker.menu.recycler.model.PlantModel;
import my.plant.tracker.menu.recycler.model.RecyclerModel;

public class MenuViewModel extends ViewModel {

    MutableLiveData<List<RecyclerModel>> plantsLiveData = new MutableLiveData();

    public MenuViewModel() {
        super();
    }

    void loadPlants(Context context) {
        new Thread(() -> {
            PlantDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                    PlantDatabase.class, "plant-database").build();
            SharedPrefsManager sharedPrefsManager = new SharedPrefsManager(context);
            List<PlantModel> dbList = db.plantDao().getPlants().stream().map(databaseModel -> mapFromDatabase(databaseModel, sharedPrefsManager.getEmail())).collect(Collectors.toList());
            ArrayList<RecyclerModel> recyclerItemsArrayList = new ArrayList<RecyclerModel>(dbList);
            if (!recyclerItemsArrayList.contains(new AddPlantModel())) {
                recyclerItemsArrayList.add(new AddPlantModel());
            }
            new Handler(Looper.getMainLooper()).post(() -> {
                plantsLiveData.postValue(recyclerItemsArrayList);
            });
        }).start();
    }

    private PlantModel mapFromDatabase(PlantEntity plantEntity, String email) {
        if (plantEntity.getUserEmail().equals(email)) {
            return new PlantModel(
                    plantEntity.id,
                    plantEntity.getTitle(),
                    plantEntity.getWatering(),
                    plantEntity.getShortDescription(),
                    plantEntity.getDescription(),
                    plantEntity.getUserEmail(),
                    plantEntity.getPhotoPath()
            );
        } else {
            return null;
        }
    }
}
