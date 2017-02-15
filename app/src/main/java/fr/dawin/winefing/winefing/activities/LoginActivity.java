package fr.dawin.winefing.winefing.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.classes.User;
import fr.dawin.winefing.winefing.utils.Controller;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    public Controller monController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        monController = new Controller();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setBackgroundDrawableResource(R.drawable.login_background);

        String email="";
        String plainPassword ="";

        // Récupération de l'utilitaire permettant de récupérer des données de login si elles ont été enregistrées auparavant
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        // Récupération d'un potentiel email stocké sur le téléphone
        email = loginPreferences.getString("email", "");

        if(email.length() != 0 && email != null) {
            //L'email est présent, donc on récupère également le mot de passe et on identifie automatiquement l'user
            plainPassword = loginPreferences.getString("plainPassword", "");
            logUserAutomatically(email, plainPassword);
        }
    }

    public void signUp(View view) {
        Intent signupIntent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(signupIntent, REQUEST_SIGNUP);
    }

    //Permet de log automatiquement l'utilisateur
    public void logUserAutomatically(String email, String plainPassword) {

        String result = monController.login(email, plainPassword);
        if (result.equals("error_server") || result.equals("204")) {
            Toast.makeText(getBaseContext(), "Erreur de connexion au serveur.", Toast.LENGTH_LONG).show();
        } else {

            final User user = new User();
            JSONObject jObject = null;
            try {
                jObject = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                getUserInfos(user, jObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // On lance l'activité Dashboard avec pour paramètre l'utilisateur connecté
            Intent signupIntent = new Intent(getApplicationContext(), UserDashboardActivity.class);
            signupIntent.putExtra("user", (Parcelable) user);
            startActivityForResult(signupIntent, REQUEST_SIGNUP);
            finish();
        }
    }

    // Permet de log un User à partir du formulaire de connexion
    public void login(View view) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        final Button _loginButton = (Button)findViewById(R.id.btn_login);
        final EditText _emailInput = (EditText) findViewById(R.id.input_email);
        final EditText _passwordInput = (EditText) findViewById(R.id.input_password);

        String email = _emailInput.getText().toString();
        String plainPassword = _passwordInput.getText().toString();

        if (!validate(_emailInput, _passwordInput)) {
            onLoginFailed(_loginButton);
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.WineFingTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Identification...");
        progressDialog.show();

        String result = monController.login(email,plainPassword);

        if(result.equals("204")) {
            progressDialog.dismiss();
            _loginButton.setEnabled(true);
            Toast.makeText(getBaseContext(), R.string.wrong_mail_password, Toast.LENGTH_LONG).show();
        }else if(result.equals("error_server") || result.equals("")){
            progressDialog.dismiss();
            _loginButton.setEnabled(true);
            Toast.makeText(getBaseContext(), R.string.error_connecting_server, Toast.LENGTH_LONG).show();
        }else{
            final User user = new User();
            JSONObject jObject = null;
            try {
                jObject = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                getUserInfos(user, jObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Stockage des données de l'utilisateur qui se connecte afin qu'il se connecte automatiquement les prochaines fois
            loginPrefsEditor.putString("email", email);
            loginPrefsEditor.putString("plainPassword", plainPassword);
            loginPrefsEditor.commit();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onLoginSuccess(_loginButton, user);
                            progressDialog.dismiss();
                        }
                    }, 2000);
            }
    }

    private void getUserInfos(User user, JSONObject jObject) throws JSONException {
        int id = jObject.getInt("id");

        String first_name;
        if(jObject.has("first_name"))
            first_name = jObject.getString("first_name");
        else
            first_name = "";

        String last_name;
        if(jObject.has("last_name"))
            last_name = jObject.getString("last_name");
        else
            last_name = "";

        String telephone;
        if(jObject.has("phone_number"))
            telephone = jObject.getString("phone_number");
        else
            telephone = "";

        String date_naissance;
        if(jObject.has("birth_date")){
            date_naissance = jObject.getString("birth_date");
            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = formatter.parse(date_naissance);
                date_naissance = String.valueOf(date);
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                date_naissance = newFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else
            date_naissance = "";

        String description;
        if(jObject.has("description"))
            description = jObject.getString("description");
        else
            description = "";

        String urlImage;
        if(jObject.has("picture"))
            urlImage = jObject.getString("picture");
        else
            urlImage = "";

        String emailUser;
        if(jObject.has("email"))
            emailUser = jObject.getString("email");
        else
            emailUser = "";

        user.setUserAttr(id, first_name, last_name, telephone, date_naissance, description, urlImage, emailUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // Ici, on laisse le user se connecter tout seul
            }
            else if (resultCode == 2){

            }
            else {
                Toast.makeText(getBaseContext(), R.string.error_inscription, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onLoginSuccess(Button _loginButton, User user) {
        _loginButton.setEnabled(true);
        Intent signupIntent = new Intent(getApplicationContext(), UserDashboardActivity.class);
        signupIntent.putExtra("user", (Parcelable) user);
        startActivityForResult(signupIntent, REQUEST_SIGNUP);
        finish();
    }


    private void onLoginFailed(Button _loginButton) {
        Toast.makeText(getBaseContext(), R.string.connexion_failed, Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    // Validation des champs du formulaire
    private boolean validate(EditText _emailInput, EditText _passwordInput) {
        boolean valid = true;

        String email = _emailInput.getText().toString();
        String password = _passwordInput.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailInput.setError(getString(R.string.valid_mail));
            valid = false;
        }
        else {
            _emailInput.setError(null);
        }

        if (password.isEmpty()) {
            _passwordInput.setError(getString(R.string.type_password));
            valid = false;
        } else {
            _passwordInput.setError(null);
        }
        return valid;
    }
}
