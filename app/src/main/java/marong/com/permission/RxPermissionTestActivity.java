package marong.com.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class RxPermissionTestActivity extends AppCompatActivity {
    private static final String TAG = "RxPermissionTest";
    private Button locationPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_permission_test);
        initView();
        requestPermissions();
    }

    private void requestPermissions() {

        RxPermissions   rxPermission = new RxPermissions(RxPermissionTestActivity.this);
        rxPermission.requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        ).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {

           if(permission.granted) {
               //用户已经同意该权限
               Log.d(TAG, permission.name + " is granted.");

           }else if(permission.shouldShowRequestPermissionRationale) {
               // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示

               Log.d(TAG, permission.name + " is denied. More info should be provided.");
           }else {
               // 用户拒绝了该权限，并且选中『不再询问』
               Log.d(TAG, permission.name + " is denied.");
           }

            }
        });







    }

    private void initView() {
        locationPermission = (Button) findViewById(R.id.location_permission);
    }
}
