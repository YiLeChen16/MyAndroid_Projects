package com.example.taobaounion.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taobaounion.R;
import com.example.taobaounion.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

//自定义用于显示推荐词和搜索历史的控件
public class FlowTextLayout extends ViewGroup {
    private List<String> dataList = new ArrayList<>();

    private final float DEFAULT_SPACE = 20;
    private float mItemHorizontalSpace = DEFAULT_SPACE;
    private float mItemVerticalSpace = DEFAULT_SPACE;
    private int mWidth;
    private int mItemHeight = 0;
    private OnFlowTextLayoutItemClickListener listener = null;

    public List<String> getDataList() {
        return dataList;
    }


    public float getmItemHorizontalSpace() {
        return mItemHorizontalSpace;
    }

    public void setmItemHorizontalSpace(float mItemHorizontalSpace) {
        this.mItemHorizontalSpace = mItemHorizontalSpace;
    }

    public float getmItemVerticalSpace() {
        return mItemVerticalSpace;
    }

    public void setmItemVerticalSpace(float mItemVerticalSpace) {
        this.mItemVerticalSpace = mItemVerticalSpace;
    }

    public FlowTextLayout(Context context) {
        this(context,null);
    }

    public FlowTextLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowTextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义控件相关属性
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.FlowTextLayoutStyle);
        //子view的水平间距和竖直间距
        mItemHorizontalSpace = t.getDimension(R.styleable.FlowTextLayoutStyle_horizontal_space, DEFAULT_SPACE);
        mItemVerticalSpace = t.getDimension(R.styleable.FlowTextLayoutStyle_vertical_space, DEFAULT_SPACE);
        t.recycle();
        LogUtils.d(this,"mItemHorizontalSpace-->" + mItemHorizontalSpace);
        LogUtils.d(this,"mItemVerticalSpace-->" + mItemVerticalSpace);
    }




    //给自定义控件设置数据
    public void setData(List<String> textList){
        LogUtils.d(this,"setData...");
        this.dataList.clear();
        this.dataList.addAll(textList);
        //将子View全部移除,避免重复子View的出现
        removeAllViews();
        //将子View添加绑定到界面中,并设置数据
        for (String s : dataList) {
            TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_text_view, this, false);
            view.setText(s);
            //给子View设置监听事件，当子View被点击时，将其中的文字传递到UI类中
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onFlowTextLayoutItemClickListener(s);
                    }
                }
            });
            addView(view);
        }
    }


    //用于存储所有行
    private List<List<View>> lines = new ArrayList<>();
    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //由于此方法会多次被调用，故每次调用前都要清空lines中的内容，避免布局叠加
        lines.clear();
        //用于存储一行的子View
        List<View> line = null;
        //获取当前控件的宽度
        mWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        LogUtils.d(this,"mWidth==" + mWidth);
        LogUtils.d(this,"onMeasure-->" + getChildCount());
        //测量子View的大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            //获取子View的视图
            View itemView = getChildAt(i);
            //测量子View(一定要写！！！！！)
            measureChild(itemView,widthMeasureSpec,heightMeasureSpec);
            //如果View集合为空，直接将子View添加到行集合中
            //或View集合中已有数据，需要测量判断当前行是否还能继续添加子View，即再将当前View添加进去会不会导致超出屏幕宽度
            if(line == null || !isCanAdd(itemView,line)){
                //说明 此第一行View集合还未创建 或 前一行的view集合已经满了
                //创建新的行集合并添加数据
                line = createNewLine(itemView);
                //再把此行添加到行集合中
                lines.add(line);
            }else{
                //可以添加
                line.add(itemView);
            }
        }
        //获取控件的高度
        if(getChildCount() != 0){
            mItemHeight = getChildAt(0).getMeasuredHeight();
        }
        int mHigh = (int) ((lines.size() * mItemHeight) + mItemVerticalSpace * (lines.size() + 1));
        LogUtils.d(this,"mHigh-->" + mHigh);
        //测量自己的大小
        setMeasuredDimension(mWidth,mHigh);
    }

    //创建新的行,并返回
    private List<View> createNewLine(View itemView) {
        List<View> line = new ArrayList<>();
        line.add(itemView);
        return line;
    }

    private boolean isCanAdd(View itemView, List<View> line) {
        //判断条件：
        //若当前itemView的宽度与line集合中每个子View的宽度相加，再加上其左右间距mItemHorizontalSpace*(line.size()-1) <= mWidth,则可以添加，否则不能添加
        //遍历line集合，计算所有子View的宽度之和
        int totalWidthSize = 0;
        for (View view : line) {
            totalWidthSize = totalWidthSize + view.getMeasuredWidth();
        }
        //再加上当前要添加的itemView的宽度和间距
        totalWidthSize = (int) (totalWidthSize + itemView.getMeasuredWidth() + (line.size()+ 1) * mItemHorizontalSpace);
        return totalWidthSize <= mWidth;
    }

    //加载布局(在onMeasure方法执行之后再执行)
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //摆放子View
        LogUtils.d(this,"onLayout-->" + getChildCount());
        if(getChildCount() == 0){
            return;
        }
        int top = (int) mItemVerticalSpace;
        for (List<View> line : lines) {
            int left = (int) mItemHorizontalSpace;
            for (int i = 0; i < line.size(); i++) {
                //获取子View
                View itemView = line.get(i);
                //摆放子View
                itemView.layout(left,top,itemView.getMeasuredWidth() + left,itemView.getMeasuredHeight() + top);
                //更新下一个子View要摆放的left
                left = (int) (left + itemView.getMeasuredWidth() + mItemHorizontalSpace);
            }
            //更新新一行的top值
            top = (int) (top + mItemHeight + mItemVerticalSpace);
        }
    }



    //给外界提供设置接口的方法
    public void setOnFlowTextLayoutItemClickListener(OnFlowTextLayoutItemClickListener listener){
        this.listener = listener;
    }
    //FlowTextLayout控件条目被点击的监听接口
    public interface OnFlowTextLayoutItemClickListener{
        void onFlowTextLayoutItemClickListener(String title);
    }
}
