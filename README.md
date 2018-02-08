# StatusWrapLayout【[Chinese](https://github.com/yuqiyich/StatusWarpLayout/blob/master/docs/README-zh.md)】
**ui library to wrap your page to  handle different states(like error,empty,others)**

### feature

there are default status to use for your page  error,empty,others.
when there  no data  to display ,u can use default empty status page to cover ur page, like above

![image](https://github.com/yuqiyich/StatusWarpLayout/blob/master/art/deafuat_error.gif)

by the way ,if u think deafult status page can not  satisfy your request, ni can alse use custom page  like this. to dispaly error status

![image](https://github.com/yuqiyich/StatusWarpLayout/blob/master/art/custom_error.gif)

and  this all is easy to use.

### Usage
#### get it
     dependencies {
  	     compile 'com.yich.libary.layout:statuswraplayout:1.0.2'
    }

#### use

**first**
 use  it in layout xml

    <com.yich.libary.layout.StatusWrapLayout
      android:id="@+id/maskLay"
      android:layout_width="match_parent"
      android:layout_height="match_parent">
    <TextView
      android:textSize="30sp"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="this is content!!!"
      android:layout_centerInParent="true"
      android:textColor="@android:color/black"/></com.yich.libary.layout.StatusWrapLayout>

**second**

 in your java code
when u are  loading data u can use showLoading (and more others status ,please refer to  other api)

      // befor process data show loading status
              mStatusWrapLayout.showLoading(null);

#### Advanced Features
when there are default status can  not satisfy your requirement,u can use custom status view to wrap your page.
u first create your custom status view must implement IStatusView


    public interface IStatusView {
     /**
      * init view
      */
     public boolean initView(Context context);

     /**
      * get current chunk of view
      * @return  view
      */
     public View getView();

     /**
      * {@link StatusWrapLayout}
      * @return get current  view status
      */
     public StatusWrapLayout.State getViewType();

     /**
      *  when view show  call back
      * @param listener user click current status view listener
      * @param params input params
      */
     public void onShowStatusView(OnUserClickListener listener, Object... params);

     /**
      *when view gone  call back
      * @param params   input params
      */
     public void onGoneStatusView(Object... params);

     /**
       * @param listener user click current status view listener
      * @param param  input params
      */
     public void updataStatusView(OnUserClickListener listener, Object... param);}


then u can easily add your status view to StatusWrapLayout by this code
change to your custome view

              StatusWrapLayout.Builder builder=new StatusWrapLayout.Builder();
              //change interest status  to replace this status view
              builder.setLoadingView(new MyloadingView())
                      .setErrorView(new MyErrorView())
                      .setOhterView(new MyOtherView())
                      .setEmptyView(new MyEmptyView())
                      .build(mStatusWrapLayout);

### NOTES
This library is inspired by [progress-activity](https://github.com/vlonjatg/progress-activity)  library from vlonjatg.