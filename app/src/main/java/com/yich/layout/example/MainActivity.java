package com.yich.layout.example;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yich.layout.example.statusview.MyEmptyView;
import com.yich.layout.example.statusview.MyErrorView;
import com.yich.layout.example.statusview.MyOtherView;
import com.yich.layout.example.statusview.MyloadingView;
import com.yich.libary.layout.DeafultStatusViewProvider;
import com.yich.libary.layout.StatusWrapLayout;
import com.yich.libary.layout.status.OnUserClickListener;

import fr.ganfra.materialspinner.MaterialSpinner;

public class MainActivity extends AppCompatActivity {
    String[] states = {"error", "empty", "others","content"};
    private StatusWrapLayout mStatusWrapLayout;
    private Button processDatabtn;
    CountDownTimer timer;
    MaterialSpinner spinner;
    RadioGroup group;
    RadioButton default_btn,custom_btn;
    OnUserClickListener retryListener=new OnUserClickListener(){
        @Override
        public void onClick(View v) {
            doAll();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        setContentView(R.layout.activity_main);
        initView();
        initMenu();
        initProcessThings();

        processDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAll();
            }
        });
    }

    private void doAll() {
        // befor process data
        mStatusWrapLayout.showLoading(null);
        //  processing things
        timer.start();
    }

    private void initView() {
        group=findViewById(R.id.radioGroup2);
        default_btn=findViewById(R.id.de_r);
        custom_btn=findViewById(R.id.custom);
        mStatusWrapLayout=(StatusWrapLayout)findViewById(R.id.maskLay);
        processDatabtn=findViewById(R.id.fun);
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        group.check(R.id.de_r);
    }

    private void initProcessThings() {
        timer=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //after process things
                String tag=(String) spinner.getItemAtPosition(spinner.getSelectedItemPosition());
                if (states[0].equals(tag))//error status pages
                {
                   mStatusWrapLayout.showError(0,"error status page","retry",retryListener);
                }
               else if (states[1].equals(tag))//empty status pages
                {
                    mStatusWrapLayout.showEmpty(0,"deafult empty status page","retry",retryListener);
                }
                else  if (states[2].equals(tag))//other status pages
                {
                    mStatusWrapLayout.showOther(R.mipmap.ic_launcher,"deafult others status page","retry",retryListener);
                }else{
                    mStatusWrapLayout.showContent();
                }
            }
        };
    }

    private void initMenu() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                // TODO Auto-generated method stub
                changeStateView(id);
            }
        });
    }

    private void changeStateView(int id) {
         if (id==R.id.de_r)//change to deafualt
         {
             StatusWrapLayout.Builder builder=new StatusWrapLayout.Builder();
             //change interest status  to replace this status view
             DeafultStatusViewProvider deafultStatusViewProvider=new DeafultStatusViewProvider();
             builder.setEmptyView(deafultStatusViewProvider.createEmptyView())
             .setOhterView(deafultStatusViewProvider.createOtherView())
             .setErrorView(deafultStatusViewProvider.createErrorView())
             .setLoadingView(deafultStatusViewProvider.createLoadingView());
             builder.build(mStatusWrapLayout);
         }else if (id==R.id.custom){//change to your custome view
             StatusWrapLayout.Builder builder=new StatusWrapLayout.Builder();
             //change interest status  to replace this status view
             builder.setLoadingView(new MyloadingView())
                     .setErrorView(new MyErrorView())
                     .setOhterView(new MyOtherView())
                     .setEmptyView(new MyEmptyView())
                     .build(mStatusWrapLayout);
         }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
            timer=null;
        }
    }
}
