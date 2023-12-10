package com.example.taobaounion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobaounion.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectedCategoriesAdapter extends RecyclerView.Adapter<SelectedCategoriesAdapter.InnerHolder> {
    public static String[] categories = { "程序员必备","办公室零食","上班族早餐", "日用品","秋天必备"};
    private int mCurrentPosition = 0;
    private View mLastClickView = null;
    private OnSelectedCategoryClickListener listener = null;

    //为条目设置布局
    @NonNull
    @Override
    public SelectedCategoriesAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_category, parent, false);
        return new InnerHolder(itemView);
    }

    //为条目绑定数据
    @Override
    public void onBindViewHolder(@NonNull SelectedCategoriesAdapter.InnerHolder holder, int position) {
        holder.setData(categories[position]);
        if(this.mCurrentPosition == position){
            holder.tv_category.setBackgroundColor(holder.tv_category.getResources().getColor(com.vondear.rxui.R.color.gray1,null));
            mLastClickView = holder.tv_category;
        } else{
            holder.tv_category.setBackgroundColor(holder.tv_category.getResources().getColor(com.vondear.rxui.R.color.white,null));
        }
        //为条目设置监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当条目被点击时，将其对应的索引值+1，传回UI类
                if(listener != null){
                    listener.onSelectedCategoryClick(position + 1);
                }
                if(mLastClickView != null){
                    mLastClickView.setBackgroundColor(holder.tv_category.getResources().getColor(com.vondear.rxui.R.color.white,null));
                }
                v.setBackgroundColor(holder.tv_category.getResources().getColor(com.vondear.rxui.R.color.gray1,null));
                mLastClickView = v;
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_category)
        TextView tv_category;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void setData(String category){
            tv_category.setText(category);
        }
    }

    //定义设置监听接口的方法
    public void setOnSelectedCategoryClickListener(OnSelectedCategoryClickListener listener){
        this.listener = listener;
    }

    //定义监听接口用于通知SelectedFragment被点击的分类
    public interface OnSelectedCategoryClickListener{
        void onSelectedCategoryClick(int categoryID);
    }
}
