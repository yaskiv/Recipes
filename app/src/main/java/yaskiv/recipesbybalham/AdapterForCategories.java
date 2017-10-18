package yaskiv.recipesbybalham;

/**
 * Created by yaskiv on 12.06.2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static yaskiv.recipesbybalham.GridViewFragmentForAllRecipes.recipes;
import static yaskiv.recipesbybalham.MainActivity.count;

public class AdapterForCategories extends BaseAdapter {
    private Context context;
    private final List<String> categories;

    public AdapterForCategories(Context context, List<String> categories) {
        this.context = context;
        this.categories = categories;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.adapter_for_categories, null);




        } else {
            gridView = (View) convertView;
        }

        Typeface face = Typeface.createFromAsset(context.getAssets(), "Fonts/Roboto-Regular.ttf");

        TextView textView=(TextView)gridView.findViewById(R.id.left_text_view);
        textView.setText(categories.get(position));
        textView.setTypeface(face);
        String count="exception";
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        sqlHelper.create_db();
        try {

                sqlHelper.open();
            Cursor cursor = sqlHelper.database.rawQuery("SELECT count(*) FROM Recipes Join Categori on Recipes.IdCategori=Categori.IdCategori WHERE Categori.Name=?", new String[]{categories.get(position)});
            if (cursor.moveToFirst()) {
                do {
                    count = cursor.getString(0);
                }
                while (cursor.moveToNext());
            }

            TextView textViewRight = (TextView) gridView.findViewById(R.id.regiht_text_view);
            textViewRight.setText("Кол-во (" + count + ")");
            textViewRight.setTypeface(face);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gridView;
    }

        @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }}

