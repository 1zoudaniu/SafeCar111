package safecar.yiye.apackage.com.safecar.MVP.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import safecar.yiye.apackage.com.safecar.MVP.Entity.SortBean;
import safecar.yiye.apackage.com.safecar.MVP.Holder.SortNowRightHolder;
import safecar.yiye.apackage.com.safecar.R;

/**
 * Name: SortNowRightAdapter
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-22 19:23
 */
public class SortNowRightAdapter  extends BaseExpandableListAdapter


//        BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter
{
    private Context context;
    private ArrayList<SortBean.DataBean> dataRightBeen;
    private final ArrayList<SortBean.DataBean.CategoriesBean> group_list;

    public SortNowRightAdapter(Context context, ArrayList<SortBean.DataBean.CategoriesBean> dataRightBeen) {
        this.context = context;
        group_list = dataRightBeen;
    }


    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return group_list.get(groupPosition).getSubcategories().size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return group_list.get(groupPosition).getName();
    }

    @Override
    public SortBean.DataBean.CategoriesBean.SubcategoriesBean getChild(int groupPosition, int childPosition) {
        return  group_list.get(groupPosition).getSubcategories().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fragment_sort_now_right_listview, null);
            groupHolder = new GroupHolder();
            groupHolder.txt = (TextView) convertView.findViewById(R.id.paihang_second_group_carname);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.txt.setText(group_list.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SortNowRightHolder vh=null;
        SortBean.DataBean.CategoriesBean.SubcategoriesBean subcategoriesBean = group_list.get(groupPosition).getSubcategories().get(childPosition);

        //对listview进行缓存
        if(convertView==null){
            vh=new SortNowRightHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.fragment_sort_now_right_listview_child, null);
            vh.car=(TextView)convertView.findViewById(R.id.paihang_second_item_carname);
            vh.score= (TextView) convertView.findViewById(R.id.paihang_second_item_score);
            vh.imageView=(ImageView)convertView.findViewById(R.id.paihang_second_item_imageView);
            convertView.setTag(vh);
        }else{
            vh=(SortNowRightHolder) convertView.getTag();
        }

        vh.car.setText(subcategoriesBean.getName());
        vh.score.setText(subcategoriesBean.getItems_count()+"");
        vh.imageView.setImageResource(R.drawable.ic_car);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupHolder {
        public TextView txt;
    }
}
