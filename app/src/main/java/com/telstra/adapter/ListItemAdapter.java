package com.telstra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;
import com.telstra.R;
import com.telstra.framework.network.json.RequestQSingleton;
import com.telstra.model.ServiceResponse;

import java.util.List;

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
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        initView(viewHolder, rowsList.get(position));
        return convertView;
    }

    /* Fetch the image bitmap from service url */
    private void loadImage(NetworkImageView imageView, String url) {
        ImageLoader imageLoader = RequestQSingleton.getInstance(mContext).getImageLoader();
        imageView.setImageUrl(url, imageLoader);
    }

    /* Notify the adapter */
    public void refreshAdapter(List<ServiceResponse.Rows> rowsList) {
        this.rowsList = rowsList;
        notifyDataSetChanged();
    }

    /* Set the view and initialize the data*/
    private void initView(ViewHolder viewHolder, ServiceResponse.Rows rows) {
        if (viewHolder != null && rows != null) {
            if (rows.getTitle() != null)
                viewHolder.titleTextView.setText(rows.getTitle());
            else
                viewHolder.titleTextView.setText("");

            if (rows.getDescription() != null)
                viewHolder.descriptionTextView.setText(rows.getDescription());
            else
                viewHolder.descriptionTextView.setText("");

            // Validate the miage url and load the images
            if (rows.getImageHref() != null && rows.getImageHref().trim().length() > 0) {
                Picasso.with(mContext)
                        .load(rows.getImageHref())
                        .placeholder(R.mipmap.loadig_image)
                        .error(R.mipmap.error_image)
                        .into(viewHolder.imageView);
            } else {
                viewHolder.imageView.setImageResource(R.mipmap.error_image);
            }
        }
    }

    /* Adapter view holder*/
    static class ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView imageView;
    }
}
