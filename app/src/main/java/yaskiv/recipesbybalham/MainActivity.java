package yaskiv.recipesbybalham;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        FragmentOfAllRecipes.OnFragmentInteractionListener ,
        GridViewFragmentForAllRecipes.OnFragmentInteractionListener ,
        AssortmentFragment.OnFragmentInteractionListener,AboutFirmFragment.OnFragmentInteractionListener ,
        Youtube_chanel_millioner.OnFragmentInteractionListener {
    DatabaseHelper sqlHelper;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    public  List<Recipes> recipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        sqlHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        sqlHelper.create_db();
        displayView(R.id.categori_menu);









    }

    @Override
    public void onResume(){
        super.onResume();
      /*  recipes=new ArrayList<>();

        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select * from Recipes", null);
            if(userCursor.moveToFirst()){
                do{
                    Log.d("ID", userCursor.getString(0));
                    Log.d("ID", userCursor.getString(1));
                    Log.d("ID", userCursor.getString(3));
                    Log.d("ID", userCursor.getString(4));
                    //assing values
                    recipes.add(new Recipes(Integer.parseInt(userCursor.getString(0)),userCursor.getString(1),
                            userCursor.getString(3),userCursor.getString(4)));

                }while(userCursor.moveToNext());
            }

        }

        catch (SQLException ex){}
      /*  GridView gridView = (GridView) findViewById(R.id.grid_view);

        //Создаем пример класса ImageAdapter:
        gridView.setAdapter(new AdpapterForGridView(this,recipes));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3)
            {
                Intent intent = new Intent(MainActivity.this,Recipe.class);
                intent.putExtra("position", position);
                intent.putParcelableArrayListExtra("fdf", (ArrayList<? extends Parcelable>) recipes);
                startActivity(intent);

            }
        });*/
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключения
//        sqlHelper.database.close();
        //userCursor.close();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
public  static int count=0;
    public  static Menu menu_q;
    int count1=0;
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu_q=menu;
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
     /*   searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                AdpapterForGridView adapter=new AdpapterForGridView(getApplicationContext(),GridViewFragmentForAllRecipes.getRecipes());
                adapter.getFilter().filter(newText);

                Log.d("Count_oo", String.valueOf(count1)+"||"+GridViewFragmentForAllRecipes.gridView.getCount());
                count1++;
                GridViewFragmentForAllRecipes.gridView.setAdapter(adapter);
                return false;
            }
        });

        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.categori_menu:
                fragment
                        = new GridViewFragmentForAllRecipes();
                title  = "Все рецепты";

                break;
            case R.id.recipes_menu:
                fragment = new FragmentOfAllRecipes();
                title  = "Категории";

                break;
            case R.id.asoortiment_menu:
                fragment = new AssortmentFragment();
                title  = "Ассортимент Варенья";

                break;
            case R.id.about_menu:
                fragment = new AboutFirmFragment();
                title  = "О Фирме";

                break;
            case R.id.project_menu:
                fragment = new Youtube_chanel_millioner();
                title  = "О Проекте";

                break;



        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId());
        /* // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        if (id == R.id.recipes_menu) {
            fragment = new FragmentOfAllRecipes();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
          //  startActivity(new Intent(MainActivity.this,AllRecipes.class));
        } else if (id == R.id.categori_menu) {
startActivity(new Intent(MainActivity.this,Categories.class));
        } else if (id == R.id.asoortiment_menu) {
            startActivity(new Intent(MainActivity.this,Assortment.class));
        } else if (id == R.id.about_menu) {
            startActivity(new Intent(MainActivity.this,About.class));
        } else if (id == R.id.project_menu) {
            startActivity(new Intent(MainActivity.this,Youtube_chanel.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
