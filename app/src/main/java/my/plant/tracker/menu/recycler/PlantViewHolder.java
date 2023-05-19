package my.plant.tracker.menu.recycler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import my.plant.tracker.R;
import my.plant.tracker.menu.recycler.model.PlantModel;

public class PlantViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener onItemClickListener;

    public PlantViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
    }

    void bind(PlantModel plantModel) {
        Glide.with(itemView.getContext())
                .load(getInternalBitmap(plantModel.getPhotoPath()))
                .into((ImageView) itemView.findViewById(R.id.plantImage));
        ((TextView) itemView.findViewById(R.id.title)).setText(plantModel.getTitle());
        ((TextView) itemView.findViewById(R.id.shortDescription)).setText(plantModel.getShortDescription());
        itemView.setOnClickListener(v -> onItemClickListener.onItemClick(plantModel));
    }

    private Bitmap getInternalBitmap(String fileName) {
        File internalStorageDir = itemView.getContext().getFilesDir();
        File imageFile = new File(internalStorageDir, fileName);
        if (imageFile.exists()) {
            return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        } else {
            return null;
        }
    }
}
