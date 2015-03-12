package socialmap.com.restdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by yy on 3/5/15.
 */
public class AccountService extends Service {
    private User user;

    public class MyBinder extends Binder {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean login(String username, String password){
        return false;
    }

    public boolean logout(){
        return false;
    }

    public boolean register(String phone, String password){
        return false;
    }
}

