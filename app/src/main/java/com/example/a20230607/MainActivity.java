package com.example.a20230607;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<ExpensiveObject> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 100; i++) {
            list.add(new ExpensiveObject());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 清空列表，并触发GC
        //增加之后就不会有内存溢出了，似乎不是泄露
        //错了，是太大所以溢出，改小后由于创建的list是静态的，所以会导致泄露！
        list.clear();
        System.gc();//系统.垃圾回收
    }

    static class ExpensiveObject {
        byte[] data = new byte[1024 * 1024];
    }
}