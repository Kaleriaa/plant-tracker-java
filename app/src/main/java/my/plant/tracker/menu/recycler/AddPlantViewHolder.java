package my.plant.tracker.menu.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddPlantViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener onItemClickListener;

    public AddPlantViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        itemView.setOnClickListener(v -> onItemClickListener.addPlantClick());
    }
}
