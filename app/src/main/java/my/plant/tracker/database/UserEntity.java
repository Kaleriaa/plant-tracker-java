package my.plant.tracker.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String email;
    public String password;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
