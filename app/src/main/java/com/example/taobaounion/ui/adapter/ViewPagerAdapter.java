package com.example.taobaounion.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.taobaounion.moudle.bean.Categories;
import com.example.taobaounion.ui.fragmnt.HomeViewPagerFragment;
import com.example.taobaounion.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

//首页TabLayout每个ViewPager的的适配器
public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Categories.DataBean> categoriesList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        LogUtils.d(this,categoriesList.get(position).getTitle());
        return categoriesList.get(position).getTitle();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        HomeViewPagerFragment homeViewPagerFragment = new HomeViewPagerFragment(categoriesList.get(position));
        return homeViewPagerFragment;
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    //设置数据
    public void setCategories(List<Categories.DataBean> list) {
        this.categoriesList.clear();//清理缓存
        this.categoriesList.addAll(list);
        LogUtils.d(this,"setCategories-->categoriesList.size()=" + categoriesList.size());
        notifyDataSetChanged();
    }
}
