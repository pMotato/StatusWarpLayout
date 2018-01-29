package com.yich.libary.layout.status;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.yich.libary.layout.StatusWrapLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yich on 2018/1/19.
 * 2016928168@qq.com
 */

public abstract class BaseStatusViewProvider {
    public static  final  String TAG="yich";
    /**
     * if init view will add to allStatusViews
     */
    public HashMap<StatusWrapLayout.State,IStatusView> allStatusViews=new HashMap<>();

    /**
     * hide all created status views
     * @param parmas
     */
    public void hideAllStatusViews(Object... parmas) {
        for(Map.Entry<   StatusWrapLayout.State, IStatusView> entry : allStatusViews.entrySet()){
             if (entry.getValue()!=null) {
                 if (entry.getValue().getView() != null && entry.getValue().getView().getVisibility() == View.VISIBLE) {
                     entry.getValue().getView().setVisibility(View.GONE);
                     entry.getValue().onGoneStatusView(parmas);
                 }
             }
        }
    }

    /**
     * return status  view if status view is null then create it
     * @param state
     * @return
     */
    public IStatusView getStatusView(   StatusWrapLayout.State  state, Context con){
                   if (allStatusViews.get(state)==null){
                       allStatusViews.put(state,createStateView(state,con));
                   }
                   return allStatusViews.get(state);

    }

    public boolean reSetStatusView(   StatusWrapLayout.State  state, IStatusView view,Context context){
        if (view!=null){
            view.initView(context);
            view.getView().setClickable(true);
            allStatusViews.put(state,view);
            return true;
        }else{
            return false;
        }

    }

    private IStatusView createStateView(   StatusWrapLayout.State state, Context con) {
        IStatusView statusView;
        switch (state) {
            case CONTENT:
                statusView= createContentView();
                break;
            case LOADING:
                statusView=createLoadingView();
                break;
            case EMPTY:
                statusView=createEmptyView();
                break;
            case ERROR:
                statusView=createErrorView();
                break;
            case OTHER:
                statusView=createOtherView();
                break;
            default:
                Log.e(TAG,"unkonw  view state:"+state.toString());
                statusView= null;
                break;

        }
        if (statusView!=null){
            statusView.initView(con);
            statusView.getView().setClickable(true);
        }
        return statusView;
    }

    public abstract IStatusView createOtherView();

    public abstract IStatusView createErrorView();

    public abstract IStatusView createEmptyView();

    public abstract IStatusView createLoadingView();

    public   IStatusView createContentView(){
        return null;
    }


}
