package com.yich.libary.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.yich.libary.layout.status.BaseStatusViewProvider;
import com.yich.libary.layout.status.IStatusView;
import com.yich.libary.layout.status.OnUserClickListener;


/**
 * Created by yich on 2018/1/19.
 * 2016928168@qq.com
 * use status view to show ui page status
 */

public  class StatusWrapLayout extends RelativeLayout  {
    public static  final  String TAG="yich";



    public static enum State {
        CONTENT, LOADING, EMPTY, ERROR,OTHER
    }

    /**
     * current View status
     *
     */
    private State mState=State.CONTENT;
    /**
     * all status  views  Provider and manager
     */
    private BaseStatusViewProvider mStatusViewProvider;
    public StatusWrapLayout(Context context) {
        super(context);
        mStatusViewProvider=new DeafultStatusViewProvider();
    }

    public StatusWrapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mStatusViewProvider=new DeafultStatusViewProvider();

    }

    public StatusWrapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mStatusViewProvider=new DeafultStatusViewProvider();
        doRebuildStatusView();
    }

    /**
     *  if your  class extends this class .Override this method use  static class {@link Builder} to init your status views
     */
    public void doRebuildStatusView() {
    }


    /**
     *to show content
     */
    public void showContent() {
        mStatusViewProvider.hideAllStatusViews("temp");
    }

    /**
     * defaut status Views Provider call method to easy show empty
     * if u define custome empty view ,u may call {@link #showEmpty( OnUserClickListener listener,  Object... param)}  will be better, or u  define class  which extends StatusWrapLayout to DIY;
     * @param onretryListener  user click retry call back
     * @param tipText  tip
     * @param retryBtnText  click text
     * @param drawableResId  empty drawable res id
     */
    public void  showEmpty( int drawableResId,String tipText,String retryBtnText, OnUserClickListener onretryListener){
        switchState(State.EMPTY,onretryListener,drawableResId, tipText,retryBtnText);
    }

    /**
     * custome method to show empty view
     * @param listener
     * @param param
     */
    public void  showEmpty( OnUserClickListener listener,  Object... param){
        switchState(State.EMPTY,listener,param);
    }



    /**
     * defaut status Views Provider call method to easy show error view
     * if u define custome empty view ,u may call {@link #showError(OnUserClickListener listener,  Object... param)} will be better, or u  define class  which extends StatusWrapLayout to DIY;
     * @param tipText  tip
     * @param btnText  click text
     * @param drawableResId  error drawable res id
     */
    public void  showError( int drawableResId,String tipText,String btnText, OnUserClickListener onretryListener){
        switchState(State.ERROR,onretryListener,drawableResId,tipText, btnText);
    }

    /**
     * custome method to show error view
     * @param listener
     * @param param
     */
    public void  showOther( OnUserClickListener listener,  Object... param){
        switchState(State.OTHER,listener,param);
    }


    /**
     * defaut status Views Provider call method to easy show other view
     * if u define custome empty view ,u may call {@link #showOther(OnUserClickListener listener,  Object... param)} will be better, or u  define class  which extends StatusWrapLayout to DIY;
     * @param tipText  tip
     * @param btnText  click text
     * @param drawableResId  error drawable res id
     */
    public void  showOther( int drawableResId,String tipText,String btnText, OnUserClickListener onretryListener){
        switchState(State.OTHER,onretryListener,drawableResId,tipText, btnText);
    }

    /**
     * custome method to show error view
     * @param listener
     * @param param
     */
    public void  showError( OnUserClickListener listener,  Object... param){
        switchState(State.ERROR,listener,param);
    }

    /**
     * defaut status Views Provider call method to easy show loading view
     * if u define custome empty view ,u may call {@link #showLoading(OnUserClickListener listener,  Object... param)} will be better, or u  define class  which extends StatusWrapLayout to DIY;
     * @param tipText  tip
     * @param onretryListener
     */
    public void  showLoading(String tipText ,OnUserClickListener onretryListener){
        switchState(State.LOADING,onretryListener, tipText);
    }

    /**
     * custome method to show error view
     * @param listener
     * @param param
     */
    public void  showLoading( OnUserClickListener listener,  Object... param){
        switchState(State.LOADING,listener,param);
    }

    /**
     * update current status view
     * @param listener
     * @param param
     */
    public void updateCurStateView( OnUserClickListener listener,  Object... param) {
        mStatusViewProvider.getStatusView(mState,getContext()).updataStatusView(listener,param);
    }

    private void switchState(State state, OnUserClickListener listener, Object... param) {
        if (mStatusViewProvider==null){
            Log.e(TAG,"can not find StatusViewProvider ,pls check ur StatusViewProvider");
                    return ;
        }
        this.mState = state;
        mStatusViewProvider.hideAllStatusViews(param);
        if (mStatusViewProvider.getStatusView(mState,getContext()).getView().getParent()==null){
            addView(mStatusViewProvider.getStatusView(mState,getContext()).getView(),new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        }
        mStatusViewProvider.getStatusView(mState,getContext()).getView().setVisibility(View.VISIBLE);
        mStatusViewProvider.getStatusView(mState,getContext()).onShowStatusView(listener,param);


    }




    /***
     *  replace  view of special state
     * @param state
     * @param view
     * @return
     */
    private  boolean resetView(State state,IStatusView view){
        //clean status view berfor rebuild views
            removeView(mStatusViewProvider.getStatusView(state,getContext()).getView());
          return mStatusViewProvider.reSetStatusView(state,view,getContext());
    }
    public static class Builder {
        private IStatusView empty,error,loading,other;

           public  Builder  setEmptyView(IStatusView view){
               this.empty=view;
               return this;
        }
        public  Builder  setErrorView(IStatusView view){
            this.error=view;
            return this;
        }
        public  Builder  setOhterView(IStatusView view){
            this.other=view;
            return this;
        }
        public  Builder  setLoadingView(IStatusView view){
            this.loading=view;
            return this;
        }

        /**
         *  to reset statusWrapLayout of non null  statusViews of this builder
         * @param layout current statusLayout
         */
        public void build(StatusWrapLayout layout){
            if (layout!=null){

                  if (loading!=null){
                      layout.resetView(State.LOADING,loading);
                  }
                  if (empty!=null){
                      layout.resetView(State.EMPTY,empty);
                  }
                  if (error!=null){
                      layout.resetView(State.ERROR,error);
                  }
                  if (other!=null){
                      layout.resetView(State.OTHER,other);
                  }
              }
        }

    }

}
