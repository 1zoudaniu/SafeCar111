package safecar.yiye.apackage.com.safecar.MVP.Home.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;
import safecar.yiye.apackage.com.safecar.Boss.Util.SPUtils;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseActivity;
import safecar.yiye.apackage.com.safecar.MVP.Home.HomeTabActivity;
import safecar.yiye.apackage.com.safecar.MVP.LoginActivity;
import safecar.yiye.apackage.com.safecar.R;

public class PersonInfoActivity extends BaseActivity {

    @BindView(R.id.info_back)
    ImageView infoBack;
    @BindView(R.id.info_rl)
    RelativeLayout infoRl;
    @BindView(R.id.info_exit)
    Button infoExit;
    @BindView(R.id.activity_person_info)
    RelativeLayout activityPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_person_info);
    }

    @OnClick({R.id.info_back, R.id.info_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.info_back:
                finish();
                break;
            case R.id.info_exit:


                Intent intent = new Intent(PersonInfoActivity.this, LoginActivity.class);
                startActivity(intent);

                SPUtils.delString(PersonInfoActivity.this, "safecar_boss_login");
                SharedPreferences preferences=PersonInfoActivity.this.getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected Context getActivityContext() {
        return MyApplication.getContext();
    }
}
