package fr.dawin.winefing.winefing;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vmorreel on 02/02/2017.
 */

public class Property implements Parcelable {
    private int id;

    private String nomDomaine;
    private String nomRegion;

    private Boolean vinRouge;
    private Boolean vinBlanc;
    private Boolean vinRose;
    private Boolean vinSpiritueux;
    private Boolean vinBulles;


    public Property() {

    }

    public Property(Property property) {
        id = property.getId();
        //token = user.getToken();
        nomDomaine = property.getDomainName();
        nomRegion = property.getRegionName();
        vinRouge = property.getVinRouge();
        vinBlanc = property.getVinBlanc();
        vinRose = property.getVinRose();
        vinSpiritueux = property.getVinSpiritueux();
        vinBulles = property.getVinBulles();
    }

    //TODO: Supprimer ce constructeur, utile juste pour des tests.
    public Property(int id, String domainName, String regionName, Boolean vinRouge, Boolean vinBlanc, Boolean vinRose, Boolean vinSpiritueux, Boolean vinBulles) {
        this.id = id;
        this.nomDomaine = domainName;
        this.nomRegion = regionName;
        this.vinRouge = vinRouge;
        this.vinBlanc = vinBlanc;
        this.vinRose = vinRose;
        this.vinSpiritueux = vinSpiritueux;
        this.vinBulles = vinBulles;
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

    public Boolean getVinBulles() {
        return vinBulles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

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

    public void setVinBulles(Boolean vinBulles) {
        this.vinBulles = vinBulles;
    }

    public void setDomainName(String domainName) {
        this.nomDomaine = domainName;
    }

    public void setRegionName(String regionName) {
        this.nomRegion = regionName;
    }
}
