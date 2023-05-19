package my.plant.tracker.auth.plant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import my.plant.tracker.R;
import my.plant.tracker.menu.recycler.model.PlantModel;

public class PlantFragment extends Fragment {

    private PlantViewModel viewModel;
    private ActivityResultLauncher<String> resultLauncher;
    private String imagePath;

    public PlantFragment() {
        super(R.layout.plant_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        EditText titleEditText = view.findViewById(R.id.titleEditText);
        EditText wateringEditText = view.findViewById(R.id.wateringEditText);
        EditText smallDescription = view.findViewById(R.id.smallDescriptionEditText);
        EditText description = view.findViewById(R.id.descriptionEditText);
        ImageView plantImage = view.findViewById(R.id.plantImage);
        plantImage.setOnClickListener(v -> loadPhotoFromGallery());
        PlantModel plantModel = (PlantModel) (getArguments() != null ? getArguments().getSerializable("plant") : null);
        if (plantModel != null) {
            viewModel.initPlantScreen(plantModel);
        }
        viewModel.plantModelLiveData.observe(getViewLifecycleOwner(), plantModel1 -> {
            titleEditText.setText(plantModel1.getTitle());
            wateringEditText.setText(plantModel1.getWatering());
            smallDescription.setText(plantModel1.getShortDescription());
            description.setText(plantModel1.getDescription());

            Glide.with(requireContext()).load(getInternalBitmap(plantModel1.getPhotoPath())).into(plantImage);
        });
        resultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            Bitmap bitmap = getBitmapFromUri(uri);
            imagePath = "myPhoto" + bitmap.getAllocationByteCount();
            saveBitmapToInternalStorage(bitmap, imagePath);
            plantImage.setImageURI(uri);
        });
        view.findViewById(R.id.plantSave).setOnClickListener(v -> {
            String imagePathToSave;
            if (imagePath == null) {
                if (plantModel == null) {
                    imagePathToSave = "";
                } else {
                    imagePathToSave = plantModel.getPhotoPath();
                }
            } else {
                imagePathToSave = imagePath;
            }
            Integer id;
            if (plantModel == null) {
                id = null;
            } else {
                id = plantModel.getId();
            }
            viewModel.savePlant(
                    id,
                    titleEditText.getText().toString(),
                    wateringEditText.getText().toString(),
                    smallDescription.getText().toString(),
                    description.getText().toString(),
                    imagePathToSave,
                    requireActivity()
            );
        });
        viewModel.isPlantSaved.observe(getViewLifecycleOwner(), plantSaved -> {
            if (plantSaved) {
                Toast.makeText(requireContext(), "Успешно сохранено", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(
                        requireActivity(),
                        R.id.nav_host_fragment
                ).navigate(R.id.menuFragment);
            }
        });
        view.findViewById(R.id.back).setOnClickListener(v -> Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
        ).navigate(R.id.menuFragment));
    }

    void loadPhotoFromGallery() {
        resultLauncher.launch("image/*");
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            InputStream inputStream = requireActivity().getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    void saveBitmapToInternalStorage(Bitmap bitmap, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(requireContext().getFilesDir(), fileName);
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap getInternalBitmap(String fileName) {
        File internalStorageDir = requireActivity().getFilesDir();
        File imageFile = new File(internalStorageDir, fileName);
        if (imageFile.exists()) {
            return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        } else {
            Toast.makeText(requireContext(), "Файл изображения не найден", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
