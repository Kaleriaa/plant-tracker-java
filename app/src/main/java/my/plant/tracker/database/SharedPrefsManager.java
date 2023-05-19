package my.plant.tracker.database;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {

   private static final String PREFS_NAME = "MyPrefs"; // Имя файла SharedPreferences
   private static final String KEY_EMAIL = "email"; // Ключ для хранения email
   private Context context;

   public SharedPrefsManager(Context context){
      this.context = context;
   }

   public void saveEmail(String email) {
      SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(KEY_EMAIL, email);
      editor.apply();
   }

   public String getEmail() {
      SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
      return sharedPreferences.getString(KEY_EMAIL, "");
   }

   public void clearSharedPreferences() {
      SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.clear();
      editor.apply();
   }
}
