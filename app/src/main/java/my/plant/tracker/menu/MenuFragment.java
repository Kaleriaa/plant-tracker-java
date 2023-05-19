package my.plant.tracker.menu;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import my.plant.tracker.R;
import my.plant.tracker.menu.recycler.OnItemClickListener;
import my.plant.tracker.menu.recycler.PlantAdapter;
import my.plant.tracker.menu.recycler.model.PlantModel;
import my.plant.tracker.menu.recycler.model.RecyclerModel;

public class MenuFragment extends Fragment implements OnItemClickListener {

    private MenuViewModel viewModel;
    private PlantAdapter plantAdapter;

    public MenuFragment() {
        super(R.layout.menu_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        plantAdapter = new PlantAdapter(this);
        ((RecyclerView) view.findViewById(R.id.plantRecycler)).setAdapter(plantAdapter);
        viewModel.loadPlants(requireActivity());
        viewModel.plantsLiveData.observe(getViewLifecycleOwner(), new Observer<List<RecyclerModel>>() {
            @Override
            public void onChanged(List<RecyclerModel> recyclerModels) {
                plantAdapter.submitList(recyclerModels);
            }
        });
    }

    @Override
    public void onItemClick(PlantModel plantModel) {
        Bundle data = new Bundle();
        data.putSerializable("plant", plantModel);
        Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
        ).navigate(R.id.plantFragment, data);
    }

    @Override
    public void addPlantClick() {
        Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
        ).navigate(R.id.plantFragment);
    }
}
