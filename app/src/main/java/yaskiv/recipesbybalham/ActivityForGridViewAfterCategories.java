package yaskiv.recipesbybalham;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityForGridViewAfterCategories extends AppCompatActivity {
    DatabaseHelper sqlHelper;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    public List<Recipes> recipes;
    ListView gridView;
    AdpapterForGridView adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_for_grid_view_after_categories);


String nameOfCategori= getIntent().getStringExtra("NameCategori");
        gridView = (ListView) findViewById(R.id.grid_view_after_categories);
        sqlHelper = new DatabaseHelper(this);

        setTitle(nameOfCategori);
        // создаем базу данных
        sqlHelper.create_db();
String id="1";
        recipes=new ArrayList<>();

        try {
            sqlHelper.open();

            Cursor cursor=sqlHelper.database.rawQuery("SELECT IdCategori FROM Categori WHERE Name =?", new String[]{nameOfCategori});
            if(cursor.moveToFirst()){
                do{
id=cursor.getString(0);
                }
                while(cursor.moveToNext());
            }

        userCursor = sqlHelper.database.rawQuery("select * from Recipes where IdCategori=?", new String[]{id});
            if(userCursor.moveToFirst()){
                do{
                    Log.d("ID", userCursor.getString(0));
                    Log.d("ID", userCursor.getString(1));
                    Log.d("ID", userCursor.getString(3));
                    Log.d("ID", userCursor.getString(4));
                    //assing values
                    recipes.add(new Recipes(Integer.parseInt(userCursor.getString(0)),userCursor.getString(1),userCursor.getString(2),
                            userCursor.getString(3),userCursor.getString(4)));

                }while(userCursor.moveToNext());
            }
        }

        catch (SQLException ex){}
adapter=new AdpapterForGridView(this,recipes);

        //Создаем пример класса ImageAdapter:
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3)
            {
                Intent intent = new Intent(ActivityForGridViewAfterCategories.this,Recipe.class);
                intent.putExtra("position", position);
                intent.putParcelableArrayListExtra("fdf", (ArrayList<? extends Parcelable>) recipes);
                startActivity(intent);

            }
        });


    }
}
