package yaskiv.recipesbybalham;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.SEARCH_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GridViewFragmentForAllRecipes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GridViewFragmentForAllRecipes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridViewFragmentForAllRecipes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseHelper sqlHelper;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    public static List<Recipes> getRecipes() {
        return recipes;
    }

    public static List<Recipes> recipes;
    public static List<Recipes> recipesfull;
    AdpapterForGridView adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GridViewFragmentForAllRecipes() {
        // Required empty public constructor
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                // AdpapterForGridView adapter=new AdpapterForGridView(getActivity(),GridViewFragmentForAllRecipes.getRecipes());
                adapter.getFilter().filter(newText);

                Log.d("Count_oo", "||" + gridView.getCount());

                gridView.setAdapter(adapter);
                return false;
            }
        });




        //TODO this tray to change this method with metod of MainActivity
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GridViewFragmentForAllRecipes.
     */
    // TODO: Rename and change types and number of parameters
    public static GridViewFragmentForAllRecipes newInstance(String param1, String param2) {
        GridViewFragmentForAllRecipes fragment = new GridViewFragmentForAllRecipes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        View item=(View) getActivity().findViewById(R.id.action_search);
if(item!=null)
        item.setVisibility(View.VISIBLE);


    }
View rootview;
   public static ListView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

rootview=inflater.inflate(R.layout.fragment_grid_view_fragment_for_all_recipes, container, false);
         gridView = (ListView) rootview.findViewById(R.id.grid_view);
        sqlHelper = new DatabaseHelper(getActivity());
        // создаем базу данных
        sqlHelper.create_db();

        recipes=new ArrayList<>();
        recipesfull=new ArrayList<>();

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
                            userCursor.getString(2),
                            userCursor.getString(3),userCursor.getString(4)));

                }while(userCursor.moveToNext());
            }
        }

        catch (SQLException ex){}
recipesfull.addAll(recipes);
          adapter=new AdpapterForGridView(getActivity(),recipes);
        Log.d("!@#!@#", "onCreateView: ");
      // AdpapterForGridView adpapterForGridView=new AdpapterForGridView(getActivity(),recipes);

//                userAdapter.notifyDataSetChanged();
        //Создаем пример класса ImageAdapter:


        Log.d("Count_oo", String.valueOf(MainActivity.count)+"--");
        gridView.setAdapter(adapter);





      //  gridView.setScrollingCacheEnabled(false);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3)
            {

                Intent intent = new Intent(getActivity(),Recipe.class);
                intent.putExtra("position", recipes.get(position).id-1);
                intent.putParcelableArrayListExtra("fdf", (ArrayList<? extends Parcelable>) recipesfull);
                startActivity(intent);

            }
        });
//Menu menu=MainActivity.menu_q;
  //      getActivity().getMenuInflater().inflate(R.menu.main, menu);


        return   rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
