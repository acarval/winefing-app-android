package fr.dawin.winefing.winefing.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import fr.dawin.winefing.winefing.R;

/* Cette activité est appelée lors du clic sur "Inscription" et permet d'afficher les CGU (lien vers site web)
    Si l'utilisateur accepte, il est redirigé sur le formulaire d'inscription
    Sinon, il repart sur l'accueil
*/

public class LegalMentionsActivity extends AppCompatActivity {

    private static final String TAG = "LegalMentionsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_mentions);


        Typeface hind = Typeface.createFromAsset(getAssets(), "Hind/Hind-Regular.ttf");
        TextView titleCGU = (TextView) findViewById(R.id.title_cgu);
        titleCGU.setTypeface(hind);

        Button btnAccept = (Button) findViewById(R.id.btn_accept_cgu);
        btnAccept.setTypeface(hind);
        Button btnDecline = (Button) findViewById(R.id.btn_decline_cgu);
        btnDecline.setTypeface(hind);
        Button btnSeeCGU = (Button) findViewById(R.id.see_cgu);
        btnSeeCGU.setTypeface(hind);
    }

    @Override
    public void onBackPressed() {
        //Rien ici, permet d'empêcher l'utilisation du bouton de retour
    }

    //Clic sur "Accepter"
    public void continueToSignUp(View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("accepted", true);
        setResult(2, resultIntent);
        finish();
    }

    // Clic sur Refuser
    public void backToLogin(View view) {
        setResult(1);
        finish();
    }

    // Clic sur "Voir les CGU"
    public void openCGU(View view) {
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.winefing.com/fr/mentions-legales"));
        startActivity(browserIntent);
    }
}
