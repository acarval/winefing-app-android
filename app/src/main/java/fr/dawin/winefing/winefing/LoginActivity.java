package fr.dawin.winefing.winefing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

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

    public void login(View view) {
        final EditText editEmail = (EditText) findViewById(R.id.input_email);
        final EditText editPassword = (EditText) findViewById(R.id.input_password);

        String email = editEmail.getText().toString();
        String plainPassword = editPassword.getText().toString();

        String link = "http://dawin.winefing.fr/winefing/web/app_dev.php/api/users/tokens.json";

        TasksManagerPost json = new TasksManagerPost();
        try {
            String result = json.execute(link,email,plainPassword).get();
            if(result == "" || result == null){
                // afficher erreur

            } else{
                User user = new User();
                JSONObject jObject = null;
                jObject = new JSONObject(result);
                String token = jObject.getString("token");
                user.setUserAttr(jObject.getString("token"), jObject.getString("first_name"), jObject.getString("last_name"), "telephone", "date naissance", "description");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void signUp(View view) {
        Intent signupIntent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(signupIntent, REQUEST_SIGNUP);
    }

}
