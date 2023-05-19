package my.plant.tracker.start;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import my.plant.tracker.R;

public class StartFragment extends Fragment {

    private StartViewModel viewModel;

    public StartFragment() {
        super(R.layout.start_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(StartViewModel.class);
        viewModel.isUserAutorized.observe(getViewLifecycleOwner(), userAutorized -> {
            if (userAutorized) {
                Navigation.findNavController(
                        requireActivity(),
                        R.id.nav_host_fragment
                ).navigate(R.id.menuFragment);
            } else {
                Navigation.findNavController(
                        requireActivity(),
                        R.id.nav_host_fragment
                ).navigate(R.id.registerFragment);
            }
        });
        view.findViewById(R.id.start).setOnClickListener(v -> viewModel.onStartClicked(requireActivity()));
    }
}
