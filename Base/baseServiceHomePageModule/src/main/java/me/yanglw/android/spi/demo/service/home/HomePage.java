package me.yanglw.android.spi.demo.service.home;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * Created by yanglw on 2018-05-28.
 */
public interface HomePage {
    String getName(Context context);

    Fragment getContent(Context context);
}
