package com.example.taobaounion.ui.adapter;

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
import com.example.taobaounion.base.IBaseInfo;
import com.example.taobaounion.moudle.bean.RedPacketContentResult;
import com.example.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//特惠界面列表的适配器
public class RedPacketContentAdapter extends RecyclerView.Adapter<RedPacketContentAdapter.InnerHolder> {
    private List<RedPacketContentResult.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> dataList = new ArrayList<>();
    private int oldSize = dataList.size();
    private OnRedPacketItemClickListener listener = null;

    @NonNull
    @Override
    public RedPacketContentAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_red_packet_content, parent, false);
        return new InnerHolder(view);
    }

    //为条目绑定数据
    @Override
    public void onBindViewHolder(@NonNull RedPacketContentAdapter.InnerHolder holder, int position) {
        RedPacketContentResult.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean dataBean = dataList.get(position);
        holder.setData(dataBean);
        //为列表条目设置点击监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击条目时，将条目数据传递到uI类，以实现跳转到淘口令界面
                if(listener != null){
                    listener.onRedPacketItemClick(dataBean);
                }
            }
        });
    }

    //提供方法给外界设置数据进来
    public void setData(List<RedPacketContentResult.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //为适配器添加新的数据
    public void addData(List<RedPacketContentResult.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> newDataList) {
        this.oldSize = dataList.size();
        dataList.addAll(newDataList);
        notifyItemRangeChanged(oldSize,newDataList.size());
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goods_img)
        public ImageView iv_goodsImg;

        @BindView(R.id.tv_title)
        public TextView tv_title;

        @BindView(R.id.tv_normal_price)
        public TextView tv_normalPrice;

        @BindView(R.id.tv_discount_price)
        public TextView tv_discountPrice;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //为原价文本设置中划线效果
            tv_normalPrice.setPaintFlags(tv_normalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void setData(RedPacketContentResult.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean itemData) {
            Context context = itemView.getContext();
            String picUrl = UrlUtils.getPicUrl(itemData.getPict_url());
            String title = itemData.getTitle();
            float normalPrice = Float.parseFloat(itemData.getZk_final_price());
            float discountPrice = normalPrice - itemData.getCoupon_amount();
            Glide.with(context).load(picUrl).into(iv_goodsImg);
            tv_title.setText(title);
            tv_normalPrice.setText(String.format(context.getString(R.string.tv_normal_price_format), normalPrice));
            tv_discountPrice.setText(String.format(context.getString(R.string.tv_discount_price_format), discountPrice));
        }
    }

    //对外提供设置条目监听接口的方法
    public void setOnRedPacketItemClickListener(OnRedPacketItemClickListener listener){
        this.listener = listener;
    }
    //特惠界面条目点击监听接口
    public interface OnRedPacketItemClickListener {
        void onRedPacketItemClick(IBaseInfo item);
    }
}
