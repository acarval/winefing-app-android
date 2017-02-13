package fr.dawin.winefing.winefing.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.dawin.winefing.winefing.R;
import fr.dawin.winefing.winefing.classes.User;

public class UserProfileActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mon Profil");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Context myContext = getApplicationContext();


        Bundle extra = getIntent().getExtras();
        user = extra.getParcelable("user");

        ImageView profile_image = (ImageView)findViewById(R.id.profile_image);
        if(user.getUrlImage().equals(""))
            profile_image.setImageResource(R.drawable.default_user_image);
        else
            Picasso.with(myContext).load(user.getUrlImage()).fit().into(profile_image);

        TextView tv_name = (TextView) findViewById(R.id.profile_name);
        tv_name.setText(user.getPrenom() + " " + user.getNom());

        TextView tv_email = (TextView) findViewById(R.id.profile_email);
        tv_email.setText(user.getEmail());

        TextView tv_phone = (TextView) findViewById(R.id.profile_phone);
        tv_phone.setText(user.getTelephone());

        TextView tv_description = (TextView) findViewById(R.id.profile_description);
        tv_description.setText(user.getDescription());

        TextView tv_birthDate = (TextView) findViewById(R.id.profile_birthdate);
        tv_birthDate.setText(user.getDateNaissance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            setResult(666);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
