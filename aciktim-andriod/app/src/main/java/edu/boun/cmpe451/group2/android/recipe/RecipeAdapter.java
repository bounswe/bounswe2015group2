package edu.boun.cmpe451.group2.android.recipe;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.Recipe;

/**
 * Created by Mustafa Taha on 24.11.2015.
 */
public class RecipeAdapter  extends ArrayAdapter<Recipe> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public RecipeAdapter(Context context, int textViewResourceId, List<Recipe> items) {
        super(context, textViewResourceId, items);
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

        Recipe item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.itemView.setTextColor(Color.parseColor("#000000"));
            viewHolder.itemView.setText(String.format("%s %d", item.getName(), item.getId()));
        }

        return convertView;
    }
}
