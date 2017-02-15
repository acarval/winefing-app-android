package fr.dawin.winefing.winefing.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vmorreel on 07/02/2017.
 */

public class Location implements Parcelable {

    private int id;
    private float prixChambre;
    private String nomChambre;
    private int nbPersonnes;
    private String urlImage;
    private String nomDomaine;
    private String description;



    public Location() {

    }

    public Location(Location location) {
        id = location.getId();
        prixChambre = location.getPrixChambre();
        nomChambre = location.getNomChambre();
        urlImage = location.getUrlImage();
        nbPersonnes = location.getNbPersonnes();
        nomDomaine = location.getNomDomaine();
        description = location.getDescription();
    }

    public Location(int id, float prixChambre, String nomChambre, String urlImage, int nbPersonnes, String nomDomaine, String description) {
        this.id = id;
        this.prixChambre = prixChambre;
        this.nomChambre = nomChambre;
        this.urlImage = urlImage;
        this.nbPersonnes = nbPersonnes;
        this.nomDomaine = nomDomaine;
        this.description = description;
    }

    public String getNomDomaine() {
        return nomDomaine;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrixChambre() {
        return prixChambre;
    }

    public void setPrixChambre(float prixChambre) {
        this.prixChambre = prixChambre;
    }

    public String getNomChambre() {
        return nomChambre;
    }

    public void setNomChambre(String nomChambre) {
        this.nomChambre = nomChambre;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public static Creator<Location> getCREATOR() {
        return CREATOR;
    }

    protected Location(Parcel in) {
        id = in.readInt();
        prixChambre = in.readFloat();
        nomChambre = in.readString();
        urlImage = in.readString();
        nomDomaine = in.readString();
        description = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeFloat(prixChambre);
        dest.writeString(nomChambre);
        dest.writeString(urlImage);
        dest.writeString(nomDomaine);
        dest.writeString(description);
    }
}
