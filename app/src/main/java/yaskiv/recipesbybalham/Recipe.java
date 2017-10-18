package yaskiv.recipesbybalham;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Recipe extends AppCompatActivity {
    public List<Recipes> recipes;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = this.getIntent();
        position=intent.getIntExtra("position",1000);
        recipes=intent.getParcelableArrayListExtra("fdf");
        ImageView image= (ImageView)findViewById(R.id.imageViewForRecipe);
        TextView text=(TextView)findViewById(R.id.textViewForRecipe);
        String mobile = recipes.get(position).PhotoUrl;
        Bitmap myBitmap = null;
        try {
            InputStream istr = this.getAssets().open(mobile);
            myBitmap = BitmapFactory.decodeStream(istr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(myBitmap);
        setTitle(recipes.get(position).Name);
        ListView listView=(ListView)findViewById(R.id.ingridients);
        ListAdapter adapter=new AdapterForListOfIngridients(this,recipes.get(position).id);
        listView.setAdapter(adapter);
        int itemHeight = dpToPx(this, 45);
        listView.getLayoutParams().height= itemHeight*adapter.getCount();

        text.setText(recipes.get(position).MethodOfCooking);
    }
    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }



}
