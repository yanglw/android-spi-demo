package me.yanglw.android.spi.demo.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import me.yanglw.android.spi.ServiceLoader;
import me.yanglw.android.spi.demo.home.service.HomePage;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_home);

        List<HomePage> list = ServiceLoader.load(HomePage.class).list();

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return list.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return list.get(position).getContent(HomeActivity.this);
            }
        });

        TabLayout tabLayout = findViewById(R.id.bottom_group);
        new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> tab.setText(list.get(position).getName(this)))
                .attach();
    }
}
