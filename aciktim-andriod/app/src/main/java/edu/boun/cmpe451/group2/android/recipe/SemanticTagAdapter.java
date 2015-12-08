package edu.boun.cmpe451.group2.android.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.SemanticTag;


public class SemanticTagAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    List<SemanticTag> semanticTags;

    public SemanticTagAdapter(Context context, List<SemanticTag> semanticTagList) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        semanticTags = semanticTagList;
    }

    @Override
    public int getCount() {
        return semanticTags.size();
    }

    @Override
    public Object getItem(int i) {
        return semanticTags.get(i);
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
            view = mInflater.inflate(R.layout.row_semantic_tag_list, null);
            viewHolder.tagClassTV = (TextView) view.findViewById(R.id.tagClassTV);
            viewHolder.tagNameTV = (TextView) view.findViewById(R.id.tagNameTV);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        SemanticTag semanticTag = (SemanticTag) getItem(i);
        if (semanticTag != null) {
            viewHolder.tagNameTV.setText(mContext.getString(R.string.tag_name, semanticTag.getTagName()));
            viewHolder.tagClassTV.setText(mContext.getString(R.string.tag_class, semanticTag.getTagClass()));
        }
        return view;
    }

    private static final class ViewHolder {
        TextView tagNameTV, tagClassTV;
    }
}
