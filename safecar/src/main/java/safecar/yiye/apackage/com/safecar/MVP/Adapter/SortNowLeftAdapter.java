package safecar.yiye.apackage.com.safecar.MVP.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import safecar.yiye.apackage.com.safecar.R;

/**
 * Name: SortNowLeftAdapter
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 19:19
 */
public class SortNowLeftAdapter extends BaseAdapter {
    private int selectedPosition = 0;// 选中的位置
    private Context context;
    private ArrayList<String> list;
    private LayoutInflater inflater;
    public SortNowLeftAdapter(Context context, ArrayList<String> list){
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.fragment_sort_now_left_listview,null);
            viewHolder=new ViewHolder();
            viewHolder.tvContent=(TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.llMain=(LinearLayout) convertView.findViewById(R.id.ll_main);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(list.get(position));
        if (selectedPosition == position) {
            viewHolder.llMain.setBackgroundResource(R.color.gray);
        }else{
            viewHolder.llMain.setBackgroundResource(R.color.white);
        }

        return convertView;
    }

    static class ViewHolder{
        TextView tvContent;
        LinearLayout llMain;
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
}
