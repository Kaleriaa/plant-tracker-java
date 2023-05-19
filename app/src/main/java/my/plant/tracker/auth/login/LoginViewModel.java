package my.plant.tracker.auth.login;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import my.plant.tracker.database.PlantDatabase;
import my.plant.tracker.database.SharedPrefsManager;
import my.plant.tracker.database.UserEntity;

public class LoginViewModel extends ViewModel {

    MutableLiveData<Boolean> isUserSaved = new MutableLiveData<>();

    public LoginViewModel() {
        super();
    }

    void tryToSaveUser(Editable email, Editable password, Context context) {
        if (email.length() == 0 || password.length() == 0) {
            isUserSaved.postValue(false);
            return;
        }
        new Thread(() -> {
            SharedPrefsManager sharedPrefsManager = new SharedPrefsManager(context);
            PlantDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                    PlantDatabase.class, "plant-database").build();
            Boolean isUserExists = isUserExist(
                    db.plantDao().getUsers(),
                    email.toString(),
                    password.toString()
            );
            if (isUserExists) {
                sharedPrefsManager.saveEmail(email.toString());
            }
            new Handler(Looper.getMainLooper()).post(() -> {
                isUserSaved.postValue(isUserExists);
            });
        }).start();
    }

    public Boolean isUserExist(List<UserEntity> users, String email, String password) {
        for (UserEntity user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }
}
