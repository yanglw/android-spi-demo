package me.yanglw.android.spi.demo.biz1;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.yanglw.android.spi.demo.user.UserInfo;
import me.yanglw.android.spi.demo.user.UserManager;

/**
 * Created by yanglw on 2018-05-28.
 */
public class Biz1HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.biz1_fragment_home, container, false);
        view.findViewById(R.id.biz1_btn).setOnClickListener(v -> startActivity(new Intent(getActivity(), Biz1Activity.class)));
        TextView textView = view.findViewById(R.id.biz1_text);
        UserInfo user = UserManager.get().getCurrentUserInfo();
        textView.setText("load data from user Module:\nname:" + user.name + "\nemail:" + user.email);
        return view;
    }
}
