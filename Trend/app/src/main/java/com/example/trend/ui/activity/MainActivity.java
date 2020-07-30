package com.example.trend.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import com.example.trend.R;
import com.example.trend.ui.fragment.FailFragment;
import com.example.trend.ui.fragment.SuccessFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int errorcode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //取出由SuccessFragment传递的bundle
//        Bundle bundle =this.getIntent().getExtras();
//        errorcode = bundle.getInt("code");
//        Log.d("TAG","code is"+errorcode);
        judge();
    }

    private void judge() {
        getsuccessfragment();
        if (errorcode == 404){
            getFailFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//创建数据菜单
        return super.onCreateOptionsMenu(menu);//呈现菜单
    }

    public void getFailFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FailFragment failFragment = new FailFragment();
        fragmentTransaction.replace(R.id.fragemnt_contair, failFragment);
        fragmentTransaction.commit();
    }

    public void getsuccessfragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        SuccessFragment successFragment = new SuccessFragment();
        fragmentTransaction.replace(R.id.fragemnt_contair, successFragment);
        fragmentTransaction.commit();
    }
}
