package fr.dawin.winefing.winefing.classes;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Greg on 26/01/2017.
 */

public class User implements Parcelable {
    private int id;
    //private String token;
    private String prenom;
    private String nom;
    private String telephone;
    private String urlImage;
    private String dateNaissance;
    private String description;

    public User(){
    }

    public User(User user){
        id = user.getId();
        //token = user.getToken();
        prenom = user.getPrenom();
        nom = user.getNom();
        telephone = user.getTelephone();
        dateNaissance = user.getDateNaissance();
        description = user.getDescription();
        urlImage = user.getUrlImage();
        email = user.getEmail();
    }

    //token
    public User(int id, String prenom, String nom, String telephone, String dateNaissance, String description, String urlImage, String email) {
        this.id = id;
        //this.token = token;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.urlImage = urlImage;
        this.description = description;
        this.email = email;
    }
    // token
    public void setUserAttr(int id, String prenom, String nom, String telephone, String dateNaissance, String description, String urlImage, String email){
        this.id = id;
        //this.token = token;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.description = description;
        this.urlImage = urlImage;
        this.email = email;
    }

    /** Deux méthodes afin de convertir cette classe en Parcelable
     * Afin de permettre de la transferer d'activité en activité grace au Bundle **/
    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
       // dest.writeString(token);
        dest.writeString(prenom);
        dest.writeString(nom);
        dest.writeString(telephone);
        dest.writeString(dateNaissance);
        dest.writeString(description);
        dest.writeString(urlImage);
        dest.writeString(email);
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
        this.id = in.readInt();
       // this.token = in.readString();
        this.prenom= in.readString();
        this.nom = in.readString();
        this.telephone = in.readString();
        this.dateNaissance = in.readString();
        this.description = in.readString();
        this.urlImage = in.readString();
        this.email = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   /* public String getToken() {
        return token;
    }
*/
  /*  public void setToken(String token) {
        this.token = token;
    }
*/
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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
