package my.plant.tracker.menu.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import my.plant.tracker.menu.recycler.model.PlantModel;
import my.plant.tracker.menu.recycler.model.RecyclerModel;

public class PlantDiffCallback extends DiffUtil.ItemCallback<RecyclerModel> {

    @Override
    public boolean areItemsTheSame(@NonNull RecyclerModel oldItem, @NonNull RecyclerModel newItem) {
        if (oldItem instanceof PlantModel && newItem instanceof PlantModel) {
            PlantModel oldPlant = (PlantModel) oldItem;
            PlantModel newPlant = (PlantModel) newItem;
            return oldPlant.getTitle().equals(newPlant.getTitle());
        } else {
            return false;
        }
    }

    @Override
    public boolean areContentsTheSame(@NonNull RecyclerModel oldItem, @NonNull RecyclerModel newItem) {
        if (oldItem instanceof PlantModel && newItem instanceof PlantModel) {
            PlantModel oldPlant = (PlantModel) oldItem;
            PlantModel newPlant = (PlantModel) newItem;
            return oldPlant.getDescription().equals(newPlant.getDescription());
        } else {
            return false;
        }
    }
}
