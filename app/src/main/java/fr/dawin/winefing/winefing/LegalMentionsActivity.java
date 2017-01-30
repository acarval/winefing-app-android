package fr.dawin.winefing.winefing;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class LegalMentionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_mentions);
    }

    @Override
    public void onBackPressed() {
        //Rien ici, permet d'empêcher l'utilisation du bouton de retour
    }

    public void continueToSignUp(View view) {
        finish();
    }

    public void backToLogin(View view) {
        setResult(1);
        finish();
    }
}
