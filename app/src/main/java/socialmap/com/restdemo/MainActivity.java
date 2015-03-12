package socialmap.com.restdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import socialmap.com.restdemo.fragments.LoginFragment;
import socialmap.com.restdemo.fragments.LogoutFragment;
import socialmap.com.restdemo.fragments.RegisterFragment;


public class MainActivity extends ActionBarActivity {

    private static Context context;
    private ViewPager pager;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getContext();
        //App.sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        App.sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        TbsClient.init(this);

        setContentView(R.layout.activity_main);

        FixedPagerAdapter adapter = new FixedPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        tabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_config_server:
                startActivity(new Intent(MainActivity.this, ConfigServerActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class FixedPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<String> titles = new ArrayList<>();

        public FixedPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new LoginFragment());
            titles.add("登陆");
            fragments.add(new LogoutFragment());
            titles.add("登出");
            fragments.add(new RegisterFragment());
            titles.add("注册");
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
