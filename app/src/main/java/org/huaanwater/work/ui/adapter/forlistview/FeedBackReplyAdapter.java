package org.huaanwater.work.ui.adapter.forlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.feedback.FeedBackReply;
import org.huaanwater.work.logic.LogicFeedBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 * 类描述
 * 版本
 */

public class FeedBackReplyAdapter extends BaseAdapter {


    private List<FeedBackReply> list;

    private Context context;
    private LayoutInflater inflater;

    private LogicFeedBack logicFeedBack;

    public FeedBackReplyAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
        this.logicFeedBack = new LogicFeedBack();
    }


    public List<FeedBackReply> getList() {
        return list;
    }

    public void setList(List<FeedBackReply> list) {
        if (null == list) {

            list = new ArrayList<>();

        }
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {

            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null && list.size() > 0) {

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
        FeedBackReply feedBackReply = list.get(position);


        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_feedback_reply, null);
            vh.tv_reply_time = (TextView) convertView.findViewById(R.id.tv_reply_time);
            vh.iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            vh.tv_relply_content = (TextView) convertView.findViewById(R.id.tv_relply_content);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        vh.tv_reply_time.setText(ConstHz.REPLY+ConstSign.COLON+feedBackReply.getReply_time());
        vh.tv_relply_content.setText(feedBackReply.getReply_content());
        vh.tv_name.setText(feedBackReply.getFull_name());




        return convertView;
    }

    class ViewHolder {


        TextView tv_reply_time;
        ImageView iv_head;
        TextView tv_name;
        TextView tv_relply_content;
    }

}
