package my.plant.tracker.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PlantEntity.class, UserEntity.class}, version = 1)
public abstract class PlantDatabase extends RoomDatabase {

    public abstract PlantDao plantDao();

}
