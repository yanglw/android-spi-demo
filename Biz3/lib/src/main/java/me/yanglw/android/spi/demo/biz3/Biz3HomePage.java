package me.yanglw.android.spi.demo.biz3;

import android.content.Context;
import androidx.fragment.app.Fragment;

import me.yanglw.android.spi.ServiceProvider;
import me.yanglw.android.spi.demo.home.service.HomePage;

/**
 * Created by yanglw on 2018-05-28.
 */
@ServiceProvider(services = {HomePage.class}, priorities = {0})
public class Biz3HomePage implements HomePage {
    @Override
    public String getName(Context context) {
        return "Biz3";
    }

    @Override
    public Fragment getContent(Context context) {
        return new Biz3HomeFragment();
    }
}
