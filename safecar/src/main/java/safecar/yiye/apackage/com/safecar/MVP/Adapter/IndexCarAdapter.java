package safecar.yiye.apackage.com.safecar.MVP.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeHomeCarBean;
import safecar.yiye.apackage.com.safecar.MVP.Holder.IndexCarHolder;
import safecar.yiye.apackage.com.safecar.R;


/**
 * Name: IndexCarAdapter
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 16:00
 */
public class IndexCarAdapter extends BaseAdapter {
    private Context mContext;
    private int mFragment_index_listview_item;
    private HomeHomeCarBean mLists;

    public IndexCarAdapter(Context context, int fragment_index_listview_item, HomeHomeCarBean res_data) {
        this.mContext = context;
        this.mFragment_index_listview_item = fragment_index_listview_item;
        this.mLists = res_data;
    }


    @Override
    public int getCount() {
        return mLists.getRes_data().size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.getRes_data().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IndexCarHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, mFragment_index_listview_item, null);
            holder = new IndexCarHolder();
            holder.iv_car_icon = (RoundedImageView) convertView.findViewById(R.id.index_listview_item_icon);
            holder.tvCarNo = (TextView) convertView.findViewById(R.id.index_listview_item_no);
            holder.tvCarScore = (TextView) convertView.findViewById(R.id.index_listview_item_score);
            convertView.setTag(holder);
        } else {
            holder = (IndexCarHolder) convertView.getTag();
        }
        holder.iv_car_icon.setImageResource(R.drawable.ic_car);
        String score = mLists.getRes_data().get(position).getSum_score();
        //处理分数字段为null的情况
        if (score == null) {
            holder.tvCarScore.setTextColor(this.mContext.getResources().getColor(R.color.red));
            holder.tvCarScore.setText("无信号");
        } else {
            float v1 = Float.parseFloat(score);
            float v = v1 - 100f;
            //让分数从大到小排序   加上分数的颜色防止一下的分数颜色变化
            holder.tvCarScore.setTextColor(this.mContext.getResources().getColor(R.color.text_color_first));
            holder.tvCarScore.setText(v1+"");
        }
        holder.tvCarNo.setText(mLists.getRes_data().get(position).getCar_code());
        return convertView;
    }
}
