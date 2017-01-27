package fr.dawin.winefing.winefing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setBackgroundDrawableResource(R.drawable.login_background);
    }


    public void signUp(View view) {
        Intent signupIntent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(signupIntent, REQUEST_SIGNUP);
    }


    public void login(View view) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        final Button _loginButton = (Button)findViewById(R.id.btn_login);
        final EditText _emailInput = (EditText) findViewById(R.id.input_email);
        final EditText _passwordInput = (EditText) findViewById(R.id.input_password);

        Log.d(TAG, "Login");

        if (!validate(_emailInput, _passwordInput)) {
            onLoginFailed(_loginButton);
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.WineFingTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Identification...");
        progressDialog.show();


        String email = _emailInput.getText().toString();
        String plainPassword = _passwordInput.getText().toString();

        String link = "http://dawin.winefing.fr/winefing/web/app_dev.php/api/users/tokens.json";

        TasksManagerPost json = new TasksManagerPost();
        try {
            String result = json.execute(link,email,plainPassword).get();
            if(result == "" || result == null){
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), "Mauvais email ou mot de passe.", Toast.LENGTH_LONG).show();

            } else{
                final User user = new User();
                JSONObject jObject = null;
                jObject = new JSONObject(result);
                String token = jObject.getString("token");
                user.setUserAttr(jObject.getString("token"), jObject.getString("first_name"), jObject.getString("last_name"), "telephone", "date naissance", "description");
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                onLoginSuccess(_loginButton,user);
                                progressDialog.dismiss();
                            }
                        }, 2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // Ici, on laisse le user se connecter tout seul
            }
            else {
                Toast.makeText(getBaseContext(), "Erreur lors de l'inscription, veuillez réessayer.", Toast.LENGTH_LONG).show();
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
        Toast.makeText(getBaseContext(), "La connexion a échoué", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }


    private boolean validate(EditText _emailInput, EditText _passwordInput) {
        boolean valid = true;

        String email = _emailInput.getText().toString();
        String password = _passwordInput.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailInput.setError("Entrez une adresse mail valide.");
            valid = false;
        }
        else {
            _emailInput.setError(null);
        }

        if (password.isEmpty()) {
            _passwordInput.setError("Vous devez saisir votre mot de passe !");
            valid = false;
        } else {
            _passwordInput.setError(null);
        }
        return valid;
    }
}
