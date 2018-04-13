package com.test;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TestAdapter myCountAdapter;
    private List<Entity> eList = new ArrayList<>();
    private ListView listView;
    /**当前时间**/
    public long time_Current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) this.findViewById(R.id.list_view);
        initData();
        myCountAdapter = new TestAdapter(eList, this);
        listView.setAdapter(myCountAdapter);
    }

    public void initData() {
        eList.add(new Entity("一组测试一", "one",101000));
        eList.add(new Entity("一组测试二", "one",102000));
        eList.add(new Entity("一组测试三", "one",103000));
        eList.add(new Entity("一组测试四", "one",104000));
        eList.add(new Entity("一组测试一", "one",105000));
        eList.add(new Entity("一组测试二", "one",106000));

        eList.add(new Entity("二组测试一", "two",107000));
        eList.add(new Entity("二组测试二", "two",108000));
        eList.add(new Entity("二组测试三", "two",109000));
        eList.add(new Entity("二组测试四", "two",110000));
        eList.add(new Entity("二组测试一", "two",111000));
        handler_timeCurrent.sendEmptyMessageDelayed(0,1000);
    }

    private Handler handler_timeCurrent = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            time_Current = time_Current+1000;
            myCountAdapter.setTimeCurrent(time_Current);
            handler_timeCurrent.sendEmptyMessageDelayed(0,1000);
        }
    };


    @Override
    protected void onDestroy() {
        if (null != handler_timeCurrent){
            handler_timeCurrent.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

}
