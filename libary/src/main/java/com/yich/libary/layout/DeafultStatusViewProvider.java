package com.yich.libary.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yich.libary.layout.status.BaseStatusViewProvider;
import com.yich.libary.layout.status.IStatusView;
import com.yich.libary.layout.status.OnUserClickListener;


/**
 * Created by yich on 2018/1/19.
 * 2016928168@qq.com
 */

public class DeafultStatusViewProvider  extends BaseStatusViewProvider {
    /**
     *   create deafult  OtherView
     * @return
     */
    @Override
    public IStatusView createOtherView() {
        return new IStatusView() {
            private  View view;
            @Override
            public boolean initView(Context con) {
                if (view==null){
                    view= LayoutInflater.from(con).inflate(R.layout.statuswarplayout_empty_view,null);
                }
                ImageView.class.cast(view.findViewById(R.id.iv)).setImageResource(R.drawable.other);
                return false;
            }

            @Override
            public View getView() {
                return view;
            }

            @Override
            public StatusWrapLayout.State getViewType() {
                return  StatusWrapLayout.State.OTHER;
            }

            @Override
            public void onShowStatusView(final OnUserClickListener listener, Object... params) {
                         if (listener!=null){
                             view.findViewById(R.id.fun).setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     listener.onClick(view);
                                 }
                             });
                         }
                         if (params!=null){
                             if (params.length>0&&(Integer)params[0]>0){
                                 ImageView.class.cast(view.findViewById(R.id.iv)).setImageResource((Integer)params[0]);
                             }
                             if (params.length>1&&(String)params[1]!=null){
                                 TextView.class.cast(view.findViewById(R.id.tip)).setText((String)params[1]);
                             }

                             if (params.length>2&&(String)params[2]!=null){
                                 TextView.class.cast(view.findViewById(R.id.fun)).setText((String)params[2]);
                             }
                         }

            }

            @Override
            public void onGoneStatusView(Object... params) {

            }

            @Override
            public void updataStatusView(  OnUserClickListener listener, Object... param) {

            }
        };
    }


    @Override
    public   IStatusView createErrorView() {
        return new   IStatusView() {
            private  View view;
            @Override
            public boolean initView(Context con) {
                if (view==null){
                    view= LayoutInflater.from(con).inflate(R.layout.statuswarplayout_empty_view,null);
                }
                ImageView.class.cast(view.findViewById(R.id.iv)).setImageResource(R.drawable.error);
                return false;
            }

            @Override
            public View getView() {
                return view;
            }

            @Override
            public StatusWrapLayout.State getViewType() {
                return  StatusWrapLayout.State.OTHER;
            }

            @Override
            public void onShowStatusView(final   OnUserClickListener listener, Object... params) {
                if (listener!=null){
                    view.findViewById(R.id.fun).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onClick(view);
                        }
                    });
                }
                if (params!=null){
                    if (params.length>0&&(Integer)params[0]>0){
                        ImageView.class.cast(view.findViewById(R.id.iv)).setImageResource((Integer)params[0]);
                    }
                    if (params.length>1&&(String)params[1]!=null){
                        TextView.class.cast(view.findViewById(R.id.tip)).setText((String)params[1]);
                    }

                    if (params.length>2&&(String)params[2]!=null){
                        TextView.class.cast(view.findViewById(R.id.fun)).setText((String)params[2]);
                    }
                }

            }

            @Override
            public void onGoneStatusView(Object... params) {

            }

            @Override
            public void updataStatusView(  OnUserClickListener listener, Object... param) {

            }
        };

    }

    @Override
    public   IStatusView createEmptyView() {
        return new   IStatusView() {
            private  View view;
            @Override
            public boolean initView(Context con) {
                if (view==null){
                    view= LayoutInflater.from(con).inflate(R.layout.statuswarplayout_empty_view,null);
                }
                ImageView.class.cast(view.findViewById(R.id.iv)).setImageResource(R.drawable.empty);
                return false;
            }

            @Override
            public View getView() {
                return view;
            }

            @Override
            public StatusWrapLayout.State getViewType() {
                return  StatusWrapLayout.State.OTHER;
            }

            @Override
            public void onShowStatusView(final   OnUserClickListener listener, Object... params) {
                if (listener!=null){
                    view.findViewById(R.id.fun).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onClick(view);
                        }
                    });
                }
                if (params!=null){
                    if (params.length>0&&(Integer)params[0]>0){
                        ImageView.class.cast(view.findViewById(R.id.iv)).setImageResource((Integer)params[0]);
                    }
                    if (params.length>1&&(String)params[1]!=null){
                        TextView.class.cast(view.findViewById(R.id.tip)).setText((String)params[1]);
                    }

                    if (params.length>2&&(String)params[2]!=null){
                        TextView.class.cast(view.findViewById(R.id.fun)).setText((String)params[2]);
                    }
                }

            }

            @Override
            public void onGoneStatusView(Object... params) {

            }

            @Override
            public void updataStatusView(  OnUserClickListener listener, Object... param) {

            }
        };
    }

    @Override
    public   IStatusView createLoadingView() {
        return new   IStatusView() {
            private  View view;
            @Override
            public boolean initView(Context con) {
                if (view==null){
                    view= LayoutInflater.from(con).inflate(R.layout.statuswarplayout_loading_view,null);
                }
                return false;
            }

            @Override
            public View getView() {
                return view;
            }

            @Override
            public StatusWrapLayout.State getViewType() {
                return  StatusWrapLayout.State.OTHER;
            }

            @Override
            public void onShowStatusView(final OnUserClickListener listener, Object... params) {
                if (listener!=null){
                    view.findViewById(R.id.empty_fun).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onClick(view);
                        }
                    });
                }
                if (params!=null){
                    if (params.length>1&&(String)params[1]!=null){
                        TextView.class.cast(view.findViewById(R.id.empty_fun)).setText((String)params[1]);
                    }

                }

            }

            @Override
            public void onGoneStatusView(Object... params) {

            }

            @Override
            public void updataStatusView(OnUserClickListener listener, Object... param) {

            }
        };
    }
}
