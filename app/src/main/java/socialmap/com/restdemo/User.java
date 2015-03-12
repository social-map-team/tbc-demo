package socialmap.com.restdemo;

import android.graphics.Bitmap;

/**
 * Created by yy on 3/5/15.
 */
public class User {
    private String mUsername;
    private Bitmap mAvatar;

    public Bitmap getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Bitmap avatar) {
        mAvatar = avatar;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }
}
