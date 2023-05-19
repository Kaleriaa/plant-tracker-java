package my.plant.tracker.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PlantDao {

    @Query("SELECT * FROM Users")
    List<UserEntity> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewUser(UserEntity user);

    @Query("SELECT * FROM Plant")
    List<PlantEntity> getPlants();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlant(PlantEntity plantEntity);

}
