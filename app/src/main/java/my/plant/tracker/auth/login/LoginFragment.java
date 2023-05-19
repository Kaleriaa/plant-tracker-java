package my.plant.tracker.auth.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import my.plant.tracker.R;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;

    public LoginFragment() {
        super(R.layout.login_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        view.findViewById(R.id.loginButton).setOnClickListener(v -> viewModel.tryToSaveUser(
                ((EditText) view.findViewById(R.id.loginEditText)).getText(),
                ((EditText) view.findViewById(R.id.passwordEditText)).getText(),
                requireActivity()
        ));
        viewModel.isUserSaved.observe(getViewLifecycleOwner(), isUserSaved -> {
            if (isUserSaved) {
                Navigation.findNavController(
                        requireActivity(),
                        R.id.nav_host_fragment
                ).navigate(R.id.menuFragment);
            } else {
                Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
