package yaskiv.recipesbybalham;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by yaskiv on 03.06.2016.
 */

public class AdpterForJamGridView extends BaseAdapter {
    private Context context;
    private final List<JamAssortment> jams;

    public AdpterForJamGridView(Context context, List<JamAssortment> jams) {
        this.context = context;
        this.jams = jams;
    }
    int count=0;
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.list_adpter_for_jams, null);




        } else {
            gridView = (View) convertView;
        }


        // set image based on selected text
        ImageView imageView = (ImageView) gridView
                .findViewById(R.id.grid_item_image_jams);

        Log.d("Position",String.valueOf(count)+"---"+ String.valueOf(position));
        count++;
        String mobile ="Jam/"+ jams.get(position).getImageUrl();
        Bitmap myBitmap = null;
        try {
            InputStream istr = context.getAssets().open(mobile);
            myBitmap = BitmapFactory.decodeStream(istr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(myBitmap);
        return gridView;
    }

    @Override
    public int getCount() {
        return jams.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }}
