package fr.dawin.winefing.winefing.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vmorreel on 02/02/2017.
 */

public class Property implements Parcelable {
    private int id;

    private String nomDomaine;
    private String nomRegion;
    private String urlImage;
    private float prixMin;
    private float prixMax;
    private String caracteristiques;

    private Boolean vinRouge;
    private Boolean vinBlanc;
    private Boolean vinRose;
    private Boolean vinSpiritueux;
    //private Boolean vinBulles;


    public Property() {

    }

    public Property(Property property) {
        id = property.getId();
        nomDomaine = property.getDomainName();
        nomRegion = property.getRegionName();
        urlImage = property.getUrlImage();
        prixMin = property.getPrixMin();
        prixMax = property.getPrixMax();
        caracteristiques = property.getCaracteristiques();
        vinRouge = property.getVinRouge();
        vinBlanc = property.getVinBlanc();
        vinRose = property.getVinRose();
        vinSpiritueux = property.getVinSpiritueux();
        //vinBulles = property.getVinBulles();
    }

    public Property(int id, String domainName, String regionName, String urlImage, float prixMin, float prixMax, String caracteristiques, Boolean vinRouge, Boolean vinBlanc, Boolean vinRose, Boolean vinSpiritueux) {
        this.id = id;
        this.nomDomaine = domainName;
        this.nomRegion = regionName;
        this.urlImage = urlImage;
        this.prixMin = prixMin;
        this.prixMax = prixMax;
        this.caracteristiques = caracteristiques;
        this.vinRouge = vinRouge;
        this.vinBlanc = vinBlanc;
        this.vinRose = vinRose;
        this.vinSpiritueux = vinSpiritueux;
        //this.vinBulles = vinBulles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomDomaine);
        dest.writeString(nomRegion);
        dest.writeString(urlImage);
        dest.writeFloat(prixMin);
        dest.writeFloat(prixMax);
        dest.writeString(caracteristiques);
    }

    public static final Creator<Property> CREATOR = new Creator<Property>() {
        @Override
        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        @Override
        public Property[] newArray(int size) {
            return new Property[size];
        }
    };

    protected Property(Parcel in) {
        id = in.readInt();
        nomDomaine = in.readString();
        nomRegion = in.readString();
        urlImage = in.readString();
        prixMin = in.readFloat();
        prixMax = in.readFloat();
        caracteristiques = in.readString();
    }


    public int getId() {
        return id;
    }

    public String getDomainName() {
        return nomDomaine;
    }

    public String getRegionName() {
        return nomRegion;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public float getPrixMin(){ return prixMin; };

    public float getPrixMax(){ return prixMax; };

    public String getCaracteristiques() {return caracteristiques;}

    public Boolean getVinRouge() {
        return vinRouge;
    }

    public Boolean getVinBlanc() {
        return vinBlanc;
    }

    public Boolean getVinRose() {
        return vinRose;
    }

    public Boolean getVinSpiritueux() {
        return vinSpiritueux;
    }

    /*public Boolean getVinBulles() {
        return vinBulles;
    }
    */

    public void setId(int id) {
        this.id = id;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public void setUrlImage() {this.urlImage = urlImage; }

    public void setPrixMin(){ this.prixMin = prixMin;}

    public void setPrixMax(){ this.prixMax = prixMax;}

    public void setCaracteristiques(String caracteristiques) { this.caracteristiques = caracteristiques;}

    public void setVinRouge(Boolean vinRouge) {
        this.vinRouge = vinRouge;
    }

    public void setVinBlanc(Boolean vinBlanc) {
        this.vinBlanc = vinBlanc;
    }

    public void setVinRose(Boolean vinRose) {
        this.vinRose = vinRose;
    }

    public void setVinSpiritueux(Boolean vinSpiritueux) {
        this.vinSpiritueux = vinSpiritueux;
    }

    /*public void setVinBulles(Boolean vinBulles) {
        this.vinBulles = vinBulles;
    }
    */

    public void setDomainName(String domainName) {
        this.nomDomaine = domainName;
    }

    public void setRegionName(String regionName) {
        this.nomRegion = regionName;
    }
}
