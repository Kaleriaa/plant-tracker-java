package my.plant.tracker.auth.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import my.plant.tracker.R;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;

    public RegisterFragment() {
        super(R.layout.register_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        EditText emailEditText = view.findViewById(R.id.loginEditText);
        EditText passwordEditText = view.findViewById(R.id.passwordEditText);
        Button loginButton = view.findViewById(R.id.loginButton);
        view.findViewById(R.id.saveButton).setOnClickListener(v -> {
            viewModel.tryToSaveUser(
                    emailEditText.getText(),
                    passwordEditText.getText(),
                    requireActivity()
            );
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(
                        requireActivity(),
                        R.id.nav_host_fragment
                ).navigate(R.id.loginFragment);
            }
        });
        viewModel.isUserSaved.observe(getViewLifecycleOwner(), isUserSaved -> {
            if (isUserSaved) {
                Toast.makeText(requireContext(), "Пользователь сохранен", Toast.LENGTH_SHORT).show();
                emailEditText.setText(null);
                passwordEditText.setText(null);
                loginButton.setEnabled(true);
            } else {
                Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
