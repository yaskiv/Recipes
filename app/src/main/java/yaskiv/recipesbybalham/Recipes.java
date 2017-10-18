package yaskiv.recipesbybalham;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yaskiv on 01.06.2016.
 */

public class Recipes implements Parcelable {
    public  int id;
    public String Name;
    public String categori_id;
    public String MethodOfCooking;
    public String PhotoUrl;

    public Recipes(int id, String name,String categori_id, String methodOfCooking, String photoUrl) {
        this.id = id;
        Name = name;
        this.categori_id=categori_id;
        MethodOfCooking = methodOfCooking;
        PhotoUrl = photoUrl;
    }

    protected Recipes(Parcel in) {
        id = in.readInt();
        Name = in.readString();
        categori_id=in.readString();
        MethodOfCooking = in.readString();
        PhotoUrl = in.readString();
    }

    public static final Creator<Recipes> CREATOR = new Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
parcel.writeInt(id);
        parcel.writeString(Name);
        parcel.writeString(categori_id);
        parcel.writeString(MethodOfCooking);
        parcel.writeString(PhotoUrl);
    }
}
