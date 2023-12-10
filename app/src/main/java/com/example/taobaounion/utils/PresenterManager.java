package com.example.taobaounion.utils;

import com.example.taobaounion.presenter.impl.CategoryPagerPresenterImpl;
import com.example.taobaounion.presenter.impl.HomePresenterImpl;
import com.example.taobaounion.presenter.impl.RedPacketPresenterImpl;
import com.example.taobaounion.presenter.impl.SearchPresenterImpl;
import com.example.taobaounion.presenter.impl.SelectedPresenterImpl;
import com.example.taobaounion.presenter.impl.TicketPresenterImpl;

//Presenter管理器，用于获取所需的Presenter单例
public class PresenterManager {
    private final HomePresenterImpl homePresenter;
    private final CategoryPagerPresenterImpl categoryPagerPresenter;
    private static final PresenterManager mPresenterManager = new PresenterManager();
    private final TicketPresenterImpl ticketPresenter;

    private final SelectedPresenterImpl selectedPresenter;
    private final RedPacketPresenterImpl onSellPresenter;

    private final SearchPresenterImpl searchPresenter;

    //在创建此类的对象时，创建所需的所有Presenter类，因为此类的对象只会创建一次，故而这些类的对象只会创建一次，故而确保了单例
    public PresenterManager() {
        homePresenter = new HomePresenterImpl();
        categoryPagerPresenter = CategoryPagerPresenterImpl.getInstance();
        ticketPresenter = new TicketPresenterImpl();
        selectedPresenter = new SelectedPresenterImpl();
        onSellPresenter = new RedPacketPresenterImpl();
        searchPresenter = new SearchPresenterImpl();
    }

    //对外提供创建此类对象的方法，确保此类的对象为单例
    public static PresenterManager getInstance(){
        return mPresenterManager;
    }


    public HomePresenterImpl getHomePresenter() {
        return homePresenter;
    }

    public CategoryPagerPresenterImpl getCategoryPagerPresenter() {
        return categoryPagerPresenter;
    }

    public TicketPresenterImpl getTicketPresenter() {
        return ticketPresenter;
    }

    public SelectedPresenterImpl getSelectedPresenter(){
        return selectedPresenter;
    }

    public RedPacketPresenterImpl getOnSellPresenter() {
        return onSellPresenter;
    }

    public SearchPresenterImpl getSearchPresenter() {
        return searchPresenter;
    }
}
