package me.yanglw.android.spi.demo.biz1;

import android.content.Context;
import androidx.fragment.app.Fragment;

import me.yanglw.android.spi.ServiceProvider;
import me.yanglw.android.spi.demo.service.home.HomePage;

/**
 * Created by yanglw on 2018-05-28.
 */
@ServiceProvider(services = {HomePage.class}, priorities = {4})
public class Biz1HomePage implements HomePage {
    @Override
    public String getName(Context context) {
        return "Biz1";
    }

    @Override
    public Fragment getContent(Context context) {
        return new Biz1HomeFragment();
    }
}
