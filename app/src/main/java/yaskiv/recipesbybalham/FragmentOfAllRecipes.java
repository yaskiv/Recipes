package yaskiv.recipesbybalham;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentOfAllRecipes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentOfAllRecipes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentOfAllRecipes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentOfAllRecipes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentOfAllRecipes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentOfAllRecipes newInstance(String param1, String param2) {
        FragmentOfAllRecipes fragment = new FragmentOfAllRecipes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    DatabaseHelper sqlHelper;
    Cursor userCursor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

View rootview=inflater.inflate(R.layout.fragment_fragment_of_all_recipes, container, false);
        sqlHelper = new DatabaseHelper(getActivity());
        // создаем базу данных
        sqlHelper.create_db();

        List<String> list=new ArrayList<>();

        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select Name from Categori", null);
            if(userCursor.moveToFirst()){
                do{
                   list.add( userCursor.getString(0));



                }while(userCursor.moveToNext());
            }
        }

        catch (SQLException ex){}
        final ListView listView=(ListView)rootview.findViewById(R.id.list_for_categories);
        ListAdapter adapter=new AdapterForCategories(getActivity(),list);
        listView.setAdapter(adapter);
        final  List<String> list1=list;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),ActivityForGridViewAfterCategories.class);
                String nameOfCategori=list1.get(i);
                intent.putExtra("NameCategori", nameOfCategori);
                startActivity(intent);
            }
        });

        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
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
