package org.huaanwater.work.ui.adapter.forlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.huaanwater.work.R;
import org.huaanwater.work.entity.Region;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 * 类描述  地址查询编辑
 * 版本
 */
public class SearchHistoryAdapter extends BaseAdapter {


    private List<Region> list;

    private Context context;

    private LayoutInflater inflater;


    public SearchHistoryAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }


    public List<Region> getList() {
        return list;
    }

    public void setList(List<Region> list) {
        if(null == list) {

            list = new ArrayList<>();

        }

        this.list = list;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if(list != null && list.size() > 0) {

            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(list != null && list.size() > 0) {

            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Region region = list.get(position);

        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_search_history, null);
            mViewHolder.tv_search_text = (TextView) convertView.findViewById(R.id.tv_search_text);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }


        mViewHolder.tv_search_text.setText(region.getAddress());

        return convertView;
    }



    public class ViewHolder {

        TextView tv_search_text;
    }


}
