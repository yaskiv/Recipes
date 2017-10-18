package yaskiv.recipesbybalham;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaskiv on 28.06.2016.
 */

public class AdpapterForGridView extends BaseAdapter implements Filterable {
    private Context context;
    //private List<Recipes> recipes;
    private List<Recipes> recipes1;

    List mData;
    //List mStringFilterList;
    ValueFilter valueFilter;
    private LayoutInflater inflater;

private boolean stoopped=false;

    public AdpapterForGridView(Context context, List<Recipes> recipes) {
        this.context = context;
        GridViewFragmentForAllRecipes.recipes = recipes;
        this.recipes1=recipes;
    }
int count=0;

    @Override
    public  Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;

    }

    public static final class ViewHolder {
        TextView textView;
        ImageView imageView;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

      // View gridView;
        ViewHolder holder;

        if (convertView == null) {

         //   gridView = new View(context);

            // get layout from mobile.xml
            convertView = inflater.inflate(R.layout.image_with_text_for_adapter, null);
            holder = new ViewHolder();
            holder.imageView=(ImageView) convertView
                    .findViewById(R.id.grid_item_image);
            holder.textView=(TextView) convertView
                    .findViewById(R.id.grid_item_label);
            convertView.setTag(holder);
/*
            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(recipes.get(position).Name);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            Log.d("Position",String.valueOf(count)+"---"+ String.valueOf(position));
            count++;
            String mobile = recipes.get(position).PhotoUrl;
          /*  Bitmap myBitmap = null;
            try {
                InputStream istr = context.getAssets().open(mobile);
                myBitmap = BitmapFactory.decodeStream(istr);

            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(myBitmap);*/
/*
            File f = new File(context.getCacheDir()+"/"+mobile);

            if (!f.exists()) try {

                InputStream is = context.getAssets().open(mobile);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();


                FileOutputStream fos = new FileOutputStream(f);
                fos.write(buffer);
                fos.close();
            } catch (Exception e) { throw new RuntimeException(e); }
            Log.d("Path", f.getPath());
                Picasso.with(context).load("file:///android_asset/"+mobile).into(imageView);*/




        } else {
            holder = (ViewHolder) convertView.getTag();
        }
// set value into textview
     /*   TextView textView = (TextView) gridView
                .findViewById(R.id.grid_item_label);
        textView.setText(recipes.get(position).Name);

        // set image based on selected text
        ImageView imageView = (ImageView) gridView
                .findViewById(R.id.grid_item_image);
*/

            Log.d("!@#!@#", String.valueOf(GridViewFragmentForAllRecipes.recipes.size()) + "||" + String.valueOf(position) + "||");
            holder.textView.setText((String) GridViewFragmentForAllRecipes.recipes.get(position).Name);
            Log.d("Position", String.valueOf(count) + "---" + String.valueOf(position));
            count++;
            String mobile = GridViewFragmentForAllRecipes.recipes.get(position).PhotoUrl;
            Bitmap myBitmap = null;
            try {
                InputStream istr = context.getAssets().open(mobile);
                myBitmap = BitmapFactory.decodeStream(istr);

            } catch (IOException e) {
                e.printStackTrace();
            }
            holder.imageView.setImageBitmap(myBitmap);
            Typeface face = Typeface.createFromAsset(context.getAssets(), "Fonts/Roboto-Regular.ttf");
            holder.textView.setTypeface(face);

       // Picasso.with(context).load("file:///android_asset/"+mobile).into(holder.imageView);
        return convertView;
    }

    @Override
    public int getCount() {
        return GridViewFragmentForAllRecipes.recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ValueFilter extends Filter {
      int count=0;
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                Log.d("Clear", "performFiltering: 1");
                List filterList = new ArrayList();
                Log.d("SIZEQQQQ",String.valueOf( GridViewFragmentForAllRecipes.recipes.size()));

                for (int i = 0; i < recipes1.size(); i++) {
                    Log.d("!@#!@#", "publishResults: ");
                    if ((recipes1.get(i).Name.toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        Log.d("Name", recipes1.get(i).Name);
                        filterList.add(recipes1.get(i));
                    }
                }

                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = recipes1.size();
                results.values = recipes1;
            }

            stoopped=false;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            GridViewFragmentForAllRecipes.recipes = (List) results.values;

            notifyDataSetChanged();
        }

    }

}


