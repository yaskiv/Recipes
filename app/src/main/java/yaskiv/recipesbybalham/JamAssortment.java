package yaskiv.recipesbybalham;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yaskiv on 03.06.2016.
 */

public class JamAssortment implements Parcelable {


    protected JamAssortment(Parcel in) {
        Name = in.readString();
        Description = in.readString();
        ImageUrl = in.readString();
        ImageUrl2 = in.readString();
        Id = in.readString();
    }

    public static final Creator<JamAssortment> CREATOR = new Creator<JamAssortment>() {
        @Override
        public JamAssortment createFromParcel(Parcel in) {
            return new JamAssortment(in);
        }

        @Override
        public JamAssortment[] newArray(int size) {
            return new JamAssortment[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public  String Name;
    public String Description;
    public String ImageUrl;

    public JamAssortment( String id,String name, String description, String imageUrl, String imageUrl2) {
        Name = name;
        Description = description;
        ImageUrl = imageUrl;
        ImageUrl2 = imageUrl2;
        Id = id;
    }

    public String ImageUrl2;
    public String Id;

    public String getImageUrl2() {
        return ImageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        ImageUrl2 = imageUrl2;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Description);
        parcel.writeString(ImageUrl);
        parcel.writeString(ImageUrl2);
        parcel.writeString(Id);
    }
}
