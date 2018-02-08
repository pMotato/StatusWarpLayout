# StatusWrapLayout
**安卓的widget库，来包装你的ui界面**

### 特点

你的错误、没有数据和其他的页面的状态都可以使用该库来处理。
例如：当没有数据显示时，您可以使用默认的空状态页面来覆盖您的页面
![image](https://github.com/yuqiyich/StatusWarpLayout/blob/master/art/deafuat_error.gif)

对了，如果您认为该库提供的的状态页面不能满足您的请求，那么您可以使用定制页面，例如自己定义的显示错误状态

![image](https://github.com/yuqiyich/StatusWarpLayout/blob/master/art/custom_error.gif)

主要这一切复杂的状态页面非常容易实现

### 使用说明
#### 获取该UI库
     dependencies {
  	     compile 'com.yich.libary.layout:statuswraplayout:1.0.2'
    }

#### 使用

**ui界面里面编写**

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

在java代码中
当您加载数据时，您可以使用show加载(以及其他更多的状态，请参考其他api)

      // befor process data show loading status
              mStatusWrapLayout.showLoading(null);

#### 高级特性
当该库提供的默认状态无法满足您的需求时，您可以使用自定义状态视图来包装您的页面。并且该ui界面完全有你来控制
首先创建自定义状态视图必须实现IStatusView ，如下所示


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


然后，您可以通过该代码轻松地将状态视图添加到statuslayout布局中

              StatusWrapLayout.Builder builder=new StatusWrapLayout.Builder();
              //change interest status  to replace this status view
              builder.setLoadingView(new MyloadingView())
                      .setErrorView(new MyErrorView())
                      .setOhterView(new MyOtherView())
                      .setEmptyView(new MyEmptyView())
                      .build(mStatusWrapLayout);

### 注意
这个库的灵感来自于 [progress-activity](https://github.com/vlonjatg/progress-activity)  library from vlonjatg.