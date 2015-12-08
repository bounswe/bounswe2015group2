package edu.boun.cmpe451.group2.android.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.Recipe;

/**
 * Created by AhmtBrK on 08/12/15.
 */
public class RecipeListAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    List<Recipe> recipeList;

    public RecipeListAdapter(Context context, List<Recipe> recipes) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        recipeList = recipes;
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int i) {
        return recipeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.row_recipe_list, null);
            viewHolder.recipeImageIV = (ImageView) view.findViewById(R.id.recipeImageIV);
            viewHolder.recipeNameTV = (TextView) view.findViewById(R.id.recipeNameTV);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Recipe recipe = (Recipe) getItem(i);
        viewHolder.recipeNameTV.setText(recipe.getName());
        Picasso.with(mContext).load(recipe.getPictureAddress()).into(viewHolder.recipeImageIV);
        return view;
    }

    private static final class ViewHolder {
        ImageView recipeImageIV;
        TextView recipeNameTV;
    }
}
