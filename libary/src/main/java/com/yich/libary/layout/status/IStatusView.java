package com.yich.libary.layout.status;

import android.content.Context;
import android.view.View;

import com.yich.libary.layout.StatusWrapLayout;


/**
 * Created by yich on 2018/1/19.
 * 2016928168@qq.com
 * 各种状态view的生命周期
 * {
 *     emptyview，
 *     errorview，
 *     loadingview，
 *     othersView
 *
 * }
 */

public  interface IStatusView {
    /**
     * init view
     */
    public    boolean    initView(Context context);
    /**
     * get current chunk of view
     * @return  view
     */
    public    View    getView();

    /**
     * {@link StatusWrapLayout}
     * @return get current  view status
     */
    public  StatusWrapLayout.State    getViewType();

    /**
     *  when view show  call back
     * @param listener user click current status view listener
     *
     * @param params input params
     */
    public   void onShowStatusView(OnUserClickListener listener, Object... params);

    /**
     *when view gone  call back
     * @param params   input params
     */
    public  void onGoneStatusView(Object... params);

    /**
      * @param listener user click current status view listener
     * @param param  input params
     */
    public  void updataStatusView(OnUserClickListener listener, Object... param);


}
