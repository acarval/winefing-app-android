package fr.dawin.winefing.winefing;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Greg on 26/01/2017.
 */

public class User extends AppCompatActivity {
    private String token;
    private String prenom;
    private String nom;
    private String telephone;
    private String dateNaissance;
    private String description;

    public User(){
    }

    public User(User user){
        token = user.getToken();
        prenom = user.getPrenom();
        nom = user.getNom();
        telephone = user.getTelephone();
        dateNaissance = user.getDateNaissance();
        description = user.getDescription();
    }

    public User(String token, String prenom, String nom, String telephone, String dateNaissance, String description, String photoProfil) {
        this.token = token;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.description = description;
    }

    public void setUserAttr(String token, String prenom, String nom, String telephone, String dateNaissance, String description){
        this.token = token;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.description = description;
    }

    /** Deux méthodes afin de convertir cette classe en Parcelable
     * Afin de permettre de la transferer d'activité en activité grace au Bundle **/
    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(token);
        dest.writeString(prenom);
        dest.writeString(nom);
        dest.writeString(telephone);
        dest.writeString(dateNaissance);
        dest.writeString(description);;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel source)
        {
            return new User(source);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };

    public User(Parcel in) {
        this.token = in.readString();
        this.prenom= in.readString();
        this.nom = in.readString();
        this.telephone = in.readString();
        this.dateNaissance = in.readString();
        this.description = in.readString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static Parcelable.Creator<User> getCREATOR() {
        return CREATOR;
    }
}
