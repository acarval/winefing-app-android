package fr.dawin.winefing.winefing;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SignupActivity extends AppCompatActivity implements OnDateSetListener {

    private static final String TAG = "SignupActivity";
    public static boolean HAS_MINIMUM_AGE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setBackgroundDrawableResource(R.drawable.signup_background);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        final Button _birthDateButton = (Button)findViewById(R.id.btn_birthdate);

        Calendar userAge = new GregorianCalendar(year,month,day);
        Calendar minAdultAge = new GregorianCalendar();
        minAdultAge.add(Calendar.YEAR, -18);
        if (minAdultAge.before(userAge)) {
            _birthDateButton.setError("Vous devez avoir plus de 18 ans.");
            Toast.makeText(getBaseContext(), "Vous devez avoir plus de 18 ans.", Toast.LENGTH_LONG).show();
        }
        else
        {
            HAS_MINIMUM_AGE = true;
            _birthDateButton.setError(null);
            Log.e("DatePicker","Date = " + year);
            month += 1;
            ((Button) findViewById(R.id.btn_birthdate)).setHint(day + "/" + month + "/" + year);
        }
    }

    public void signUpConfirm(View view) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        final Button _signupButton = (Button)findViewById(R.id.btn_signupConfirm);
        final Button _birthDateButton = (Button)findViewById(R.id.btn_birthdate);

        final EditText _firstNameInput = (EditText) findViewById(R.id.input_firstname);
        final EditText _lastNameInput = (EditText) findViewById(R.id.input_lastname);
        final EditText _emailInputConfirm = (EditText) findViewById(R.id.input_emailConfirm);
        final EditText _plainPasswordInput = (EditText) findViewById(R.id.input_passwordConfirm);
        final EditText _plainPasswordInputConfirm = (EditText) findViewById(R.id.input_confirm_password);

        Log.d(TAG, "Signup");

        if (!validate(_firstNameInput, _lastNameInput, _emailInputConfirm, _plainPasswordInput, _plainPasswordInputConfirm, _birthDateButton)) {
            onSignupFailed(_signupButton);
            return;
        }
        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this, R.style.WineFingTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Création du compte...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onSignupSuccess(_signupButton);
                        progressDialog.dismiss();
                    }
                }, 2000);
    }


    public void onSignupSuccess(Button _signupButton) {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed(Button _signupButton) {
        Toast.makeText(getBaseContext(), "La connexion a échoué.", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate(EditText _firstNameInput, EditText _lastNameInput, EditText _emailInputConfirm, EditText _plainPasswordInput, EditText _plainPasswordInputConfirm, Button _birthDateButton) {
        boolean valid = true;

        String firstname = _firstNameInput.getText().toString();
        String lastname = _lastNameInput.getText().toString();
        String email = _emailInputConfirm.getText().toString();
        String plainPassword = _plainPasswordInput.getText().toString();
        String reEnterPassword = _plainPasswordInputConfirm.getText().toString();
        String birthDate = _birthDateButton.getHint().toString();

        if (firstname.isEmpty() || firstname.length() < 2) {
            _firstNameInput.setError("Il faut au moins 2 caractères.");
            valid = false;
        } else {
            _firstNameInput.setError(null);
        }

        if (lastname.isEmpty() || lastname.length() < 2) {
            _lastNameInput.setError("Il faut au moins 2 caractères.");
            valid = false;
        } else {
            _lastNameInput.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailInputConfirm.setError("Entrez une adresse email valide.");
            valid = false;
        } else {
            _emailInputConfirm.setError(null);
        }

        if (birthDate.isEmpty() || HAS_MINIMUM_AGE==false) {
            valid = false;
        }

        if (plainPassword.isEmpty() ) {
            _plainPasswordInput.setError("Vous devez saisir votre mot de passe !");
            valid = false;
        } else {
            _plainPasswordInput.setError(null);
        }

        if (reEnterPassword.isEmpty() || !(reEnterPassword.equals(plainPassword))) {
            _plainPasswordInputConfirm.setError("Les mots de passe doivent correspondre !");
            valid = false;
        } else {
            _plainPasswordInputConfirm.setError(null);
        }

        return valid;
    }
}
