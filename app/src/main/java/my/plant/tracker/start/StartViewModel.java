package my.plant.tracker.start;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import my.plant.tracker.database.SharedPrefsManager;

public class StartViewModel extends ViewModel {

    MutableLiveData<Boolean> isUserAutorized = new MutableLiveData();

    public StartViewModel() {
        super();
    }

    public void onStartClicked(Context context) {
        new Thread(() -> {
            SharedPrefsManager sharedPrefsManager = new SharedPrefsManager(context);
            new Handler(Looper.getMainLooper()).post(() -> {
                isUserAutorized.postValue(!sharedPrefsManager.getEmail().equals(""));
            });
        }).start();
    }
}
