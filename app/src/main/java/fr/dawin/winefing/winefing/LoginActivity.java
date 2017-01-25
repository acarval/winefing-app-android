package fr.dawin.winefing.winefing;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

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
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.WineFingTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Identification...");
        progressDialog.show();
    }



}
