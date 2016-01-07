package edu.boun.cmpe451.group2.android.recipe;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.Recipe;

/**
 * Created by Mustafa Taha on 24.11.2015.
 */
public class RecipeAdapter  extends ArrayAdapter<Recipe> {

    private static class ViewHolder {
        private ImageView imageView;
        private TextView textView;
    }
    private DisplayImageOptions options;

    public RecipeAdapter(Context context, int textViewResourceId, List<Recipe> items) {
        super(context, textViewResourceId, items);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedVignetteBitmapDisplayer(4,5))
                .build();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.recipe_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.list_item_name);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.list_item_image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Recipe item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.textView.setTextColor(Color.parseColor("#000000"));
            viewHolder.textView.setText(item.getName());
            ImageLoader.getInstance().displayImage(item.getPictureAddress(),viewHolder.imageView, options);
        }

        return convertView;
    }
}
