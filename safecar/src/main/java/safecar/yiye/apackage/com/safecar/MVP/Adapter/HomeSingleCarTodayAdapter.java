package safecar.yiye.apackage.com.safecar.MVP.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import safecar.yiye.apackage.com.safecar.Boss.Util.ToastUtil;
import safecar.yiye.apackage.com.safecar.MVP.Entity.HomeSingleCarTodayBean;
import safecar.yiye.apackage.com.safecar.MVP.Holder.HomeSingleCarTodayHolder;
import safecar.yiye.apackage.com.safecar.MVP.Home.Activity.SingleCarTodayActivity;
import safecar.yiye.apackage.com.safecar.R;

/**
 * Name: HomeSingleCarTodayAdapter
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-24 16:55
 */
public class HomeSingleCarTodayAdapter extends BaseAdapter {

    private Context mContext;
    private int mFragment_index_listview_item;
    private HomeSingleCarTodayBean mFakes;
    private SingleCarTodayActivity singleCarTodayActivity;

    public HomeSingleCarTodayAdapter(Context context, int fragment_index_listview_item, HomeSingleCarTodayBean fakes, SingleCarTodayActivity singleCarTodayActivity) {
        this.mContext = context;
        this.mFragment_index_listview_item = fragment_index_listview_item;
        this.mFakes = fakes;
        this.singleCarTodayActivity = singleCarTodayActivity;
    }


    @Override
    public int getCount() {
        return mFakes.getRes_expection().size();
    }

    @Override
    public Object getItem(int position) {
        return mFakes.getRes_expection().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeSingleCarTodayHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, mFragment_index_listview_item, null);
            holder = new HomeSingleCarTodayHolder();
            holder.singcar_lv_time = (TextView) convertView.findViewById(R.id.singcar_lv_time);
            holder.singcar_lv_event = (TextView) convertView.findViewById(R.id.singcar_lv_event);
            holder.singcar_lv_score1 = (TextView) convertView.findViewById(R.id.singcar_lv_score1);
            holder.singcar_lv_score2 = (ImageView) convertView.findViewById(R.id.singcar_lv_score2);
            holder.singcar_lv_location = (ImageView) convertView.findViewById(R.id.singcar_lv_location);
            convertView.setTag(holder);

        } else {
            holder = (HomeSingleCarTodayHolder) convertView.getTag();
        }
        HomeSingleCarTodayBean.ResExpectionBean resExpectionBean = mFakes.getRes_expection().get(position);
        long vehicle_exception_timestamp = resExpectionBean.getData_date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(vehicle_exception_timestamp);
        String format = sdf.format(date);
        holder.singcar_lv_time.setText(format + "");
        holder.singcar_lv_event.setText(resExpectionBean.getPro());
        //TODO  分数没给
        String score = resExpectionBean.getScore();
        double aDouble = Double.parseDouble(score);
        if (aDouble == 0) {
            holder.singcar_lv_score2.setVisibility(View.VISIBLE);
            holder.singcar_lv_score1.setVisibility(View.GONE);

            holder.singcar_lv_score2.setImageResource(R.drawable.attation);
            holder.singcar_lv_score2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.startShort(singleCarTodayActivity,"该扣分项已达上限，请谨慎驾驶");
                }
            });
        } else {
            holder.singcar_lv_score1.setVisibility(View.VISIBLE);
            holder.singcar_lv_score2.setVisibility(View.GONE);

            holder.singcar_lv_score1.setText("-" + score + "");
//            holder.singcar_lv_score.setBackgroundColor(R.color.white);
            holder.singcar_lv_score1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if (position == selectItem) {
            convertView.setBackgroundColor(Color.RED);
        } else {
            convertView.setBackgroundColor(Color.BLUE);
        }
        return convertView;
    }

    public void setSelectItemPosition(int selectItem) {
        this.selectItem = selectItem;
    }

    private int selectItem = -1;
}
