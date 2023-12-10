package com.example.taobaounion.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taobaounion.R;
import com.example.taobaounion.moudle.ILinearItemInfo;
import com.example.taobaounion.moudle.bean.HomeViewPagerContent;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//首页每个Viewpager内容的适配器和搜索界面结果列表适配器
public class LinearItemContentAdapter extends RecyclerView.Adapter<LinearItemContentAdapter.MyViewHolder> {
    private List<ILinearItemInfo> dataBeanList = new ArrayList<>();

    //声明自定义接口变量
    private OnListItemClickListener listener = null;

    //加载item布局
    //正常只会加载屏幕可见的条数，若将屏幕上没有显示的也加载出来会导致内存浪费
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtils.d(this,"onCreateViewHolder-->");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_view_pager_content, parent, false);
        return new MyViewHolder(view);
    }

    //为条目绑定数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LogUtils.d(this,"onBindViewHolder-->" + position);
        ILinearItemInfo dataBean = dataBeanList.get(position);
        holder.setData(dataBean);
        //为条目设置点击事件
        holder.itemView.setOnClickListener(v -> {
            if(listener != null){
                //获取到被点击的条目数据
                ILinearItemInfo item = dataBeanList.get(position);
                //将条目数据传递到HomeViewPagerFragment中
                listener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    //提供给外部为适配器设置数据的方法
    public void setData(List<? extends ILinearItemInfo> dataBeanList){
        this.dataBeanList.clear();
        this.dataBeanList.addAll(dataBeanList);
        notifyDataSetChanged();
    }

    //将刷新加载的数据添加到适配器数据集合中
    public void addData(List<? extends ILinearItemInfo> contents) {
        int oldSize = dataBeanList.size();
        dataBeanList.addAll(contents);
        //更新UI
        notifyItemRangeChanged(oldSize,contents.size());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_goods_img)
        protected ImageView iv_goodsImg;

        @BindView(R.id.tv_goods_name)
        protected TextView tv_goodsName;

        @BindView(R.id.tv_tag_cheap)
        protected TextView tv_discount;

        @BindView(R.id.tv_discount_price)
        protected TextView tv_discountPrice;

        @BindView(R.id.tv_normal_price)
        protected TextView tv_normalPrice;

        @BindView(R.id.tv_sell_count)
        protected TextView tv_sellCount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //绑定控件
            ButterKnife.bind(this,itemView);
            //为原价文本设置中划线效果
            tv_normalPrice.setPaintFlags(tv_normalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        //设置数据
        private void setData(ILinearItemInfo dataBean){
            //根据控件大小来设置加载的网络图片的大小，省流，加快加载速度
            ViewGroup.LayoutParams layoutParams = iv_goodsImg.getLayoutParams();
            int width = layoutParams.width;
            int height = layoutParams.height;
            int size = (Math.max(width, height)) / 2;//不需要完全与控件一样大小，可小一半，但图片宽高都需相同（由后台服务器决定的）
            //根据size来设置加载的网络图片的size
            String picUrl = UrlUtils.getPicUrl(dataBean.getPicUrl(),size);
            Context context = itemView.getContext();
            //使用Glide类来根据服务器中请求下来的网络图片URL加载网络图片
            Glide.with(context)
                    .load(picUrl)//指定要加载的图片URL
                    .error(R.mipmap.ic_launcher)//设置错误图，用于在加载失败时显示
                    .into(iv_goodsImg);//将图片加载到指定的ImageView控件上
            tv_goodsName.setText(dataBean.getTitle());
            Float normalPrice = Float.parseFloat(dataBean.getNormalPrice());
            long discount = dataBean.getDiscount();
            float discountPrice = normalPrice - discount;
            tv_discount.setText(String.format(context.getString(R.string.tag_cheap_format),discount));
            tv_discountPrice.setText(String.format("%.2f",discountPrice));
            tv_normalPrice.setText(String.format(context.getString(R.string.tv_normal_price_format),normalPrice));
            tv_sellCount.setText(String.format(context.getString(R.string.tv_sell_count_format),dataBean.getSellCount()));
        }
    }

    //提供方法给外界设置监听接口
    public void setListItemClickListener(OnListItemClickListener listener){
        this.listener = listener;
    }

    //创建自定义接口，用于将被点击的条目数据传递到对应的UI类中
    public interface OnListItemClickListener{
        void onItemClick(ILinearItemInfo item);  // 点击事件在HomeViewPagerFragment中回调方法
    }
}
