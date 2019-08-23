package com.omnify.assignment.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.omnify.assignment.R;
import com.omnify.assignment.databinding.ActivityTabBinding;
import com.omnify.assignment.view.fragment.Comment;
import com.omnify.assignment.view.fragment.Web;
import com.omnify.assignment.utils.Const;

/**
 * Created by neeraj on 9/20/2018.
 */

public class TabActivity extends AppCompatActivity {
    private ActivityTabBinding activityTabBinding;
    private FragmentManager fragmentManager;
    private String webUrl;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_1:
                    setFragment(0);
                    return true;
                case R.id.tab_2:
                    setFragment(1);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        activityTabBinding = DataBindingUtil.setContentView(this, R.layout.activity_tab);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);

        fragmentManager = getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getExtrasFromIntent();

    }


    public static Intent launchDetail(Context context, String title, String url, String user) {
        Intent intent = new Intent(context, TabActivity.class);
        intent.putExtra(Const.TITLE, title);
        intent.putExtra(Const.WEB, url);
        intent.putExtra(Const.USER, user);
        return intent;
    }

    private void getExtrasFromIntent() {
        if (getIntent().getExtras() != null) {
            webUrl = getIntent().getStringExtra(Const.WEB);
            activityTabBinding.textViewTitle.setText(getIntent().getStringExtra(Const.TITLE));
            activityTabBinding.textViewWeb.setText(webUrl);
            activityTabBinding.textViewTimeUser.setText(getIntent().getStringExtra(Const.USER));
        }
    }

    private void setFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Comment();
                fragmentManager.beginTransaction().replace(R.id.rootLayout, fragment).commit();
                break;
            case 1:
                fragment = new Web();
                Bundle bundleTab2 = new Bundle();
                bundleTab2.putString(Const.WEB, webUrl);
                fragment.setArguments(bundleTab2);
                fragmentManager.beginTransaction().replace(R.id.rootLayout, fragment).commit();
                break;

        }
    }


}
