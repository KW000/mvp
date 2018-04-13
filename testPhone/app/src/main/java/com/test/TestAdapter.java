package com.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * desc
 * Author:shimao
 * Date:2017.09.11 10:10
 */
public class TestAdapter extends BaseAdapter {

    private List<Entity> eList = new ArrayList<>();

    private Context mContext;
    private long timeCurrent;
    ViewHolder viewHolder;

    public TestAdapter(List<Entity> eList, Context mContext) {
        this.eList = eList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return eList.size();
    }

    @Override
    public Object getItem(int position) {
        return eList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.title1 = (TextView) convertView.findViewById(R.id.one);
            viewHolder.title2 = (TextView) convertView.findViewById(R.id.two);
            viewHolder.second = (TextView) convertView.findViewById(R.id.second);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Entity entity = eList.get(position);
        updateTextView(entity.getTime()-timeCurrent,viewHolder);
  //      viewHolder.second.setTimes(entity.getTime());
        return convertView;
    }

    class ViewHolder{
        TextView title1;
        TextView title2;
        TextView second;
    }

    public void setTimeCurrent(long timeCurrent) {
        this.timeCurrent = timeCurrent;
        for (int i = 0; i < eList.size(); i++) {
            updateTextView(eList.get(i).getTime()-timeCurrent,viewHolder);
        }
    }

    /**
     * 刷新倒计时控件
     */
    public void updateTextView(long times_remain,ViewHolder viewHolder) {

        if (times_remain <= 0) {
            viewHolder.title1.setText("00");
            viewHolder.title2.setText("00");
            viewHolder.second.setText("00");
            return;
        }
        //秒钟
        long time_second = (times_remain/1000)%60;
        String str_second;
        if (time_second < 10) {
            str_second = "0" + time_second;
        } else {
            str_second = "" + time_second;
        }

        long time_temp = ((times_remain / 1000) - time_second) / 60;
        //分钟
        long time_minute = time_temp % 60;
        String str_minute;
        if (time_minute < 10) {
            str_minute = "0" + time_minute;
        } else {
            str_minute = "" + time_minute;
        }

        time_temp = (time_temp - time_minute) / 60;
        //小时
        long time_hour = time_temp;
        String str_hour;
        if (time_hour < 10) {
            str_hour = "0" + time_hour;
        } else {
            str_hour = "" + time_hour;
        }

        viewHolder.title1.setText(str_hour);
        viewHolder.title2.setText(str_minute);
        viewHolder.second.setText(str_second);
    }

}
