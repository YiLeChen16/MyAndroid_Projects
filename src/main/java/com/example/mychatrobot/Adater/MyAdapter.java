package com.example.mychatrobot.Adater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mychatrobot.Bean.ChatBean;
import com.example.mychatrobot.R;

import java.util.List;

//此适配器用于加载聊天条目布局和内容
public class MyAdapter extends BaseAdapter {
    private List<ChatBean> mChatBeanList;
    private Context mContext;

    //在创建适配器对象时将条目数据传递进来，以实现数据的适配
    public MyAdapter(List<ChatBean> chatBeans, Context context) {
        this.mChatBeanList = chatBeans;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mChatBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mChatBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        //covertView表示的是之前滑出屏幕的item对象
        if (convertView == null) {
            //创建ViewHolder
            viewHolder = new MyViewHolder();
            //判断是条目的状态，是用户发送出去的还是用户接收到的
            if (mChatBeanList.get(position).getState() == ChatBean.SEND) {
                //用户发送的数据，加载用户布局
                convertView = View.inflate(mContext, R.layout.chat_right_item_layout, null);
                //找到控件
                viewHolder.tv_content = convertView.findViewById(R.id.tv_right_text);
            } else if (mChatBeanList.get(position).getState() == ChatBean.RECEIVE) {
                //用户接收到的数据，加载机器人布局
                convertView = View.inflate(mContext, R.layout.chat_left_item_layout, null);
                //找到控件
                viewHolder.tv_content = convertView.findViewById(R.id.tv_left_text);
            }
            //将holder对象添加到convertView对象中,以便下次复用
            assert convertView != null;
            convertView.setTag(viewHolder);
        } else {
            //复用之前的ViewHolder
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        //为控件加载数据
        viewHolder.tv_content.setText(mChatBeanList.get(position).getContent());
        //返回视图
        return convertView;
    }

    //ViewHolder为一种设计模式，用于提高列表的性能和滚动流畅度
    /*
     * 为了避免滚动listView时，不断地创建和销毁视图，引起频繁的布局计算导致滚动卡顿
     * viewHolder设计模式被引入，它是一个类，包含了多个视图控件的引用。当我们自定义了这个类时，当ListView需要重新绘制新的数据时，
     * 就会先从已创建的ViewHolder对象池中获取可重复利用的对象，否则会通过findViewById（）方法来创建新的ViewHolder对象，
     * 并将其与当前的数据项关联起来。然后再使用该ViewHolder对象来填充数据项的内容
     * */
    class MyViewHolder {
        public TextView tv_content;
    }
}
