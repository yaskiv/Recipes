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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssortmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AssortmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssortmentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String[] imagemas = new String[]{"Jam/1.png", "Jam/2.png", "Jam/3.png", "Jam/4.png", "Jam/5.png", "Jam/6.png", "Jam/7.png", "Jam/8.png", "Jam/9.png"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AssortmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssortmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssortmentFragment newInstance(String param1, String param2) {
        AssortmentFragment fragment = new AssortmentFragment();
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
    SimpleCursorAdapter userAdapter;
    public List<JamAssortment> jams;
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_assortment, container, false);
        ListView gridView = (ListView) rootview.findViewById(R.id.grid_vie_for_assortiment_of_jam);


        sqlHelper = new DatabaseHelper(getActivity());
        // создаем базу данных
        sqlHelper.create_db();

        jams=new ArrayList<>();

        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select * from Jam", null);
            if(userCursor.moveToFirst()){
                do{
                    Log.d("ID", userCursor.getString(0));
                    Log.d("ID", userCursor.getString(1));
                    Log.d("ID", userCursor.getString(3));
                    Log.d("ID", userCursor.getString(4));
                    //assing values
                    jams.add(new JamAssortment(userCursor.getString(0),userCursor.getString(1),userCursor.getString(2),
                            userCursor.getString(3),userCursor.getString(4)));

                }while(userCursor.moveToNext());
            }
        }

        catch (SQLException ex){}



        gridView.setAdapter(new AdpterForJamGridView(getActivity(), jams));
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),Jam.class);
                intent.putExtra("position", i);
                intent.putParcelableArrayListExtra("fdf", (ArrayList<? extends Parcelable>) jams);
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
