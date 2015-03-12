package socialmap.com.restdemo.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import socialmap.com.restdemo.R;
import socialmap.com.restdemo.TbsClient;

/**
 * Created by yy on 2/24/15.
 */
public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        final EditText _username = (EditText)root.findViewById(R.id.username);
        final EditText _password = (EditText)root.findViewById(R.id.password);
        Button _register = (Button)root.findViewById(R.id.register);
        _register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TbsClient.getInstance()
                        .request("/register", "post",
                                "username", _username.getText(),
                                "password", _password.getText())
                        .execute(new TbsClient.Callback() {
                            @Override
                            public void onFinished(TbsClient.ServerResponse response) {
                                if (response.getError() == null){
                                    Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        return root;
    }
}
