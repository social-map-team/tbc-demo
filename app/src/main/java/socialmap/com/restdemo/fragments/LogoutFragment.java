package socialmap.com.restdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import socialmap.com.restdemo.App;
import socialmap.com.restdemo.R;
import socialmap.com.restdemo.TbsClient;

/**
 * Created by yy on 2/24/15.
 */
public class LogoutFragment extends Fragment {
    private TextView currentUser;
    private ImageView avatar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        currentUser = (TextView) root.findViewById(R.id.current_user);
        avatar = (ImageView) root.findViewById(R.id.avatar);
        if (App.user != null) {
            currentUser.setText(App.user.getUsername());
            avatar.setImageBitmap(App.user.getAvatar());
        }
        Button logout = (Button) root.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TbsClient.getInstance()
                        .request("/login", "delete")
                        .execute(new TbsClient.Callback() {
                            @Override
                            public void onFinished(TbsClient.ServerResponse response) {
                                if (response.getError() == null) {
                                    Toast.makeText(getActivity(), "登出成功", Toast.LENGTH_SHORT).show();
                                    App.user = null;
                                } else {
                                    Toast.makeText(getActivity(), "登出失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        Button refresh = (Button) root.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (App.user != null) {
                    currentUser.setText(App.user.getUsername());
                    avatar.setImageBitmap(App.user.getAvatar());
                } else {
                    currentUser.setText(getString(R.string.no_user));
                    avatar.setImageBitmap(null);
                }
            }
        });
        return root;
    }
}
