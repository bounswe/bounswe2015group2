package edu.boun.cmpe451.group2.android.ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.boun.cmpe451.group2.android.api.Recipe;

/**
 * Created by Cafer Tayyar YORUK on 08.12.2015.
 */
public class IngredientAdapter extends ArrayAdapter{

    List<IngredientItem> ingredientItemList;
    int resource;

    private static class ViewHolder {
        private TextView itemView;
    }

    public IngredientAdapter(Context context, int resource, List<IngredientItem> objects) {
        super(context, resource, objects);
        ingredientItemList = objects;
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(android.R.layout.simple_list_item_activated_1, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemView.setText(String.format("%s - %s", ingredientItemList.get(position).getName(), ingredientItemList.get( position ).getNdbno()));


        return convertView;
    }
}
