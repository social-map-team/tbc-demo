package socialmap.com.restdemo.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import socialmap.com.restdemo.App;
import socialmap.com.restdemo.R;
import socialmap.com.restdemo.TbsClient;
import socialmap.com.restdemo.User;

/**
 * Created by yy on 2/24/15.
 */
public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        final TextView _username = (TextView) root.findViewById(R.id.username);
        final TextView _password = (TextView) root.findViewById(R.id.password);
        Button _login = (Button) root.findViewById(R.id.login);
        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TbsClient.refresh(_username.getText().toString(), _password.getText().toString());
                System.out.println("登陆：" + _username.getText().toString() + " " + _password.getText().toString());

                TbsClient.getInstance()
                        .request("/login", "post")
                        .execute(new TbsClient.Callback() {
                            @Override
                            public void onFinished(TbsClient.ServerResponse response) {
                                if (response.is2xx() && response.getContent().length == 0) {
                                    Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();

                                    // 获取用户头像，并把用户信息放入App类
                                    TbsClient.getInstance()
                                            .request("/profile/avatar", "get")
                                            .execute(new TbsClient.Callback() {
                                                @Override
                                                public void onFinished(TbsClient.ServerResponse response) {
                                                    if (response.getContentType().startsWith("image")) {
                                                        User user = new User();
                                                        user.setUsername(_username.getText().toString());
                                                        user.setAvatar(BitmapFactory.decodeByteArray(response.getContent(),
                                                                0, response.getContent().length));
                                                        App.user = user;
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(getActivity(), "登陆失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        return root;
    }
}
