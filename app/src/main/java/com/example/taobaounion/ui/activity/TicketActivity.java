package com.example.taobaounion.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseActivity;
import com.example.taobaounion.moudle.bean.TicketResult;
import com.example.taobaounion.presenter.impl.TicketPresenterImpl;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.PresenterManager;
import com.example.taobaounion.utils.ToastUtils;
import com.example.taobaounion.utils.UrlUtils;
import com.example.taobaounion.view.ITicketResultCallBack;

import butterknife.BindView;

//淘口令界面
public class TicketActivity extends BaseActivity implements ITicketResultCallBack {

    private TicketPresenterImpl ticketPresenter;

    @BindView(R.id.iv_back)
    public ImageView iv_back;
    @BindView(R.id.iv_goods_img)
    public ImageView iv_goodsImage;

    @BindView(R.id.ed_ticket_code)
    public EditText ed_ticketCode;

    @BindView(R.id.btn_get_ticket)
    public TextView tv_getTicket;


    @BindView(R.id.loading_view)
    public View loadingView;

    private boolean mHasTaoBaoApp = false;//是否有淘宝

    //初始化Presenter
    @Override
    public void initPresenter() {
        ticketPresenter = PresenterManager.getInstance().getTicketPresenter();
        //把本类的对象注册传递过去
        ticketPresenter.registerViewCallBack(this);
    }

    @Override
    public void initView() {
        LogUtils.d(this,"initView...");
        //判断手机中有没有安装淘宝，若有则修改按钮文本为打开淘宝领券，没有则修改为复制淘口令
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.taobao.taobao", PackageManager.MATCH_UNINSTALLED_PACKAGES);
            if (packageInfo != null) {
                mHasTaoBaoApp = true;//有淘宝
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            mHasTaoBaoApp = false;//没有淘宝
        }
        LogUtils.d(this, "mHasTaoBaoApp --> " + mHasTaoBaoApp);
        //修改UI
        tv_getTicket.setText(mHasTaoBaoApp ? "打开淘宝领券" : "复制淘口令");
    }


    //设置监听事件
    @Override
    public void initListener() {
        iv_back.setOnClickListener(v -> finish());

        //为按钮设置点击事件
        tv_getTicket.setOnClickListener(v -> {
            //首先将淘口令复制到剪切板,去掉空格
            String ticketCode = ed_ticketCode.getText().toString().trim();
            //获取系统剪切板管理器
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            //将淘口令复制到剪切板
            ClipData clipData = ClipData.newPlainText("com.example.taobaounion.ui.activity.TicketActivity", ticketCode);
            clipboardManager.setPrimaryClip(clipData);
            //点击后判断手机有无淘宝，有则跳转
            if(mHasTaoBaoApp){
                //跳转
                Intent taobaoIntent = new Intent();
                taobaoIntent.setPackage("com.taobao.taobao");
                taobaoIntent.setAction(Intent.ACTION_VIEW);
                taobaoIntent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(taobaoIntent);
            }else {
                ToastUtils.showToast("淘口令复制成功！打开淘宝即可领券！");
            }
        });
    }

    //返回界面视图id
    @Override
    public int getViewId() {
        return R.layout.activity_ticket;
    }

    /**
     * 请求到的数据从这里回来
     * @param picUrl
     * @param result
     */
    @Override
    public void onLoadTicketResult(String picUrl, TicketResult result) {
        LogUtils.d(this,"onLoadTicketResult...");
        String url = UrlUtils.getPicUrl(picUrl);
        Glide.with(this).load(url).into(iv_goodsImage);
        if (result.getData().getTbk_tpwd_create_response() == null || result.getData().getTbk_tpwd_create_response().getData() == null) {
            return;
        }
        ed_ticketCode.setText(result.getData().getTbk_tpwd_create_response().getData().getModel());
        if (loadingView != null) {
            loadingView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onError() {
        if (loadingView != null) {
            loadingView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onEmpty() {

    }


    @Override
    public void release() {
        if (ticketPresenter != null) {
            //取消注册
            ticketPresenter.unregisterViewCallBack(this);
        }
    }

}
