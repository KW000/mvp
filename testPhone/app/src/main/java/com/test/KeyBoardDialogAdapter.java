package com.test;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;

/**
 * desc
 * Author:shimao
 * Date:2017.09.15 10:22
 */
public class KeyBoardDialogAdapter extends BaseAdapter{

    public String items[] = {"1","2","3","4","5","6","7","8","9",".","0","close"};
    private Context context;
    private int height;

    public KeyBoardDialogAdapter(Context context) {
        this.context = context;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        height = dm.heightPixels;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.itme,null);
            viewHolder = new ViewHolder();
            viewHolder.tv = (DynamicHeightTextView) convertView.findViewById(R.id.text);
            viewHolder.tv.setHeight((int)(height/3-3*context.getResources().getDimension(R.dimen.gird_spacing))/4);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(items[position]+"");


        return convertView;
    }

    class ViewHolder{
        DynamicHeightTextView tv;
    }

}
