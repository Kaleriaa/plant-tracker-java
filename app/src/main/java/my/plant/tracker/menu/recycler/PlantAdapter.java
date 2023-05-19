package my.plant.tracker.menu.recycler;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import my.plant.tracker.R;
import my.plant.tracker.menu.recycler.model.PlantModel;
import my.plant.tracker.menu.recycler.model.RecyclerModel;

public class PlantAdapter extends ListAdapter<RecyclerModel, RecyclerView.ViewHolder> {

    private static final int ITEM_PLANT = 1;
    private static final int ITEM_ADD_PLANT = 2;
    private OnItemClickListener listener;

    public PlantAdapter(OnItemClickListener listener) {
        super(new PlantDiffCallback());
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof PlantModel) {
            return ITEM_PLANT;
        } else {
            return ITEM_ADD_PLANT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_PLANT) {
            return new PlantViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item, parent, false),
                    listener
            );
        } else {
            return new AddPlantViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.add_plant, parent, false),
                    listener
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PlantViewHolder) {
            ((PlantViewHolder) holder).bind((PlantModel) getItem(position));
        }
    }
}
