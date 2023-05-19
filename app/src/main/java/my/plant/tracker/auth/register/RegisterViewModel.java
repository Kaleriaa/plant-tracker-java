package my.plant.tracker.auth.register;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import my.plant.tracker.database.PlantDatabase;
import my.plant.tracker.database.UserEntity;

public class RegisterViewModel extends ViewModel {

    MutableLiveData<Boolean> isUserSaved = new MutableLiveData<>();

    public RegisterViewModel() {
        super();
    }

    void tryToSaveUser(Editable email, Editable password, Context context) {
        if (email.length() == 0 || password.length() == 0) {
            isUserSaved.postValue(false);
            return;
        }
        new Thread(() -> {
            PlantDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                    PlantDatabase.class, "plant-database").build();
            db.plantDao().insertNewUser(new UserEntity(
                    email.toString(),
                    password.toString()
            ));
            new Handler(Looper.getMainLooper()).post(() -> isUserSaved.postValue(true));
        }).start();
    }
}
