package com.telstra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.telstra.R;
import com.telstra.framework.network.json.RequestQSingleton;
import com.telstra.model.ServiceResponse;

import java.util.List;

/**
 * Created by 461495 on 11/15/2015
 */
public class ListItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<ServiceResponse.Rows> rowsList;

    public ListItemAdapter(Context mContext, List<ServiceResponse.Rows> rowsList) {
        this.mContext = mContext;
        this.rowsList = rowsList;
    }

    @Override
    public int getCount() {
        return rowsList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return rowsList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_view, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.title_txt);
            viewHolder.descriptionTextView = (TextView) convertView.findViewById(R.id.desc_txt);
            viewHolder.imageView = (NetworkImageView) convertView.findViewById(R.id.image_view);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        initView(viewHolder, rowsList.get(position));
        return convertView;
    }

    /* Fetch the image bitmap from service url */
    private void loadImage(NetworkImageView imageView, String url)
    {
        ImageLoader imageLoader = RequestQSingleton.getInstance(mContext).getImageLoader();
        imageView.setImageUrl(url, imageLoader);
    }

    /* Notify the adapter */
    public void refreshAdapter(List<ServiceResponse.Rows> rowsList)
    {
        this.rowsList = rowsList;
        notifyDataSetChanged();
    }

    /* Set the view and initialize the data*/
    private void initView(ViewHolder viewHolder, ServiceResponse.Rows rows)
    {
        if(viewHolder != null && rows != null)
        {
            if(rows.getTitle() != null)
                viewHolder.titleTextView.setText(rows.getTitle());
            else
                viewHolder.titleTextView.setText("");

            if(rows.getDescription() != null)
                viewHolder.descriptionTextView.setText(rows.getDescription());
            else
                viewHolder.descriptionTextView.setText("");

            if(rows.getImageHref() != null)
                loadImage(viewHolder.imageView, rows.getImageHref());
            else
                loadImage(viewHolder.imageView, "");
        }
    }

    /* Adapter view holder*/
    static class ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        NetworkImageView imageView;
    }
}
