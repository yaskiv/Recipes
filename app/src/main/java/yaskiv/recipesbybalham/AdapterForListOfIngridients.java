package yaskiv.recipesbybalham;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaskiv on 12.06.2016.
 */

public class AdapterForListOfIngridients extends BaseAdapter {
    public   List<Ingridients> list;
    private Context context;
    private int id;

    public AdapterForListOfIngridients(Context context, int id) {
        this.context = context;
        this.id = id;
list=new ArrayList<>();
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        // создаем базу данных
        sqlHelper.create_db();

        try {
            sqlHelper.open();
            Cursor userCursor = sqlHelper.database.rawQuery("Select Ingridients.Name,Cooking.Quantity,Ingridients.Measure from Ingridients join Cooking on Cooking.IdIngridients=Ingridients.Id where Cooking.IdRecipes=?", new String[]{String.valueOf(id)});
            if(userCursor.moveToFirst()){
                do{
                    list.add(new Ingridients(userCursor.getString(0),userCursor.getString(1),userCursor.getString(2)));

                }while(userCursor.moveToNext());
            }
        }

        catch (SQLException ex){}
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.adapter_for_list_of_ingridients, null);




        } else {
            gridView = (View) convertView;
        }



        TextView textview_name=(TextView)gridView.findViewById(R.id.ingridients_name);
        TextView textview_count=(TextView)gridView.findViewById(R.id.ingridients_count);
        textview_name.setText(list.get(position).getName());
        textview_count.setText(list.get(position).getQuantity()+" "+list.get(position).getMeasure());
        return gridView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }}

