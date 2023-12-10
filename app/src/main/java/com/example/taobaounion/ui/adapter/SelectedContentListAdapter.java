package com.example.taobaounion.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
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
import com.example.taobaounion.moudle.bean.SelectedContentResult;
import com.example.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectedContentListAdapter extends RecyclerView.Adapter<SelectedContentListAdapter.InnerHolder> {
    private List<SelectedContentResult.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> dataList = new ArrayList<>();
    private OnSelectedContentItemClickListener listener = null;

    //绑定条目布局
    @NonNull
    @Override
    public SelectedContentListAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_content, parent, false);
        return new InnerHolder(v);
    }

    //为条目绑定数据
    @Override
    public void onBindViewHolder(@NonNull SelectedContentListAdapter.InnerHolder holder, int position) {
       //TODO:
        holder.setData(dataList.get(position));
        //为列表条目设置监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    //将被点击的条目的数据传递到UI中
                    listener.onSelectedContentItemClick(dataList.get(position));
                }
            }
        });
    }

    //提供方法给外部传入数据
    public void setDataList(SelectedContentResult.DataBean dataBean){
        this.dataList.clear();
        this.dataList.addAll(dataBean.getTbk_dg_optimus_material_response().getResult_list().getMap_data());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goods_img)
        public ImageView iv_goods_img;
        @BindView(R.id.tv_tag_cheap)
        public TextView tv_cheep;
        @BindView(R.id.tv_title)
        public TextView tv_title;
        @BindView(R.id.tv_get_ticket)
        public TextView tv_getTicket;
        @BindView(R.id.tv_normal_price)
        public TextView tv_normalPrice;

        @BindView(R.id.tv_price)
        public TextView tv_price;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //绑定控件
            ButterKnife.bind(this,itemView);

        }

        //为控件设置数据
        public void setData(SelectedContentResult.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean bean){
            Context context = itemView.getContext();
            String pict_url = bean.getPict_url();
            String picUrl = UrlUtils.getPicUrl(pict_url);
            Float normalPrice = Float.parseFloat(bean.getZk_final_price());
            long discount = bean.getCoupon_amount();
            String coupon_click_url = bean.getCoupon_click_url();
            Glide.with(this.itemView).load(picUrl).into(iv_goods_img);
            tv_title.setText(bean.getTitle());
            if(TextUtils.isEmpty(coupon_click_url)){
                //优惠券已经完了
                tv_price.setVisibility(View.GONE);
                tv_cheep.setVisibility(View.INVISIBLE);
                tv_getTicket.setVisibility(View.GONE);
                tv_normalPrice.setText("来晚啦~优惠券已经发完啦~");
            }else{
                tv_price.setVisibility(View.VISIBLE);
                tv_cheep.setVisibility(View.VISIBLE);
                tv_cheep.setText(String.format(context.getString(R.string.tag_cheap_format),discount));
                tv_normalPrice.setText(String.format(context.getString(R.string.tv_normal_price_format),normalPrice));
                tv_getTicket.setVisibility(View.VISIBLE);
            }
        }
    }

    //对外提供设置接口的方法
    public void setOnSelectedContentItemClickListener(OnSelectedContentItemClickListener listener){
        this.listener = listener;
    }

    //内容条目被点击的UI监听回调接口
    public interface OnSelectedContentItemClickListener{
        void onSelectedContentItemClick(IBaseInfo item);
    }
}
