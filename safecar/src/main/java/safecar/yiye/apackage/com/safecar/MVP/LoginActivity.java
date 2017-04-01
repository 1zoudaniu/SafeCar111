package safecar.yiye.apackage.com.safecar.MVP;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import rx.Observer;
import safecar.yiye.apackage.com.safecar.Boss.Constant.Constant;
import safecar.yiye.apackage.com.safecar.Boss.Data.HttpData.HttpData;
import safecar.yiye.apackage.com.safecar.Boss.MyApplication.MyApplication;
import safecar.yiye.apackage.com.safecar.Boss.Util.SPUtils;
import safecar.yiye.apackage.com.safecar.MVP.Base.BaseActivity;
import safecar.yiye.apackage.com.safecar.MVP.Entity.LoginBean;
import safecar.yiye.apackage.com.safecar.MVP.Home.HomeTabActivity;
import safecar.yiye.apackage.com.safecar.R;
import safecar.yiye.apackage.com.safecar.Widget.Dialog.ColorDialog;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_phone_code)
    EditText mEtPhoneCode;
    @BindView(R.id.tv_get_phone_code)
    TextView mTvGetPhoneCode;
    @BindView(R.id.btn_submit_user)
    Button mBtnSubmitUser;
    @BindView(R.id.btn_submit_test)
    Button mBtnSubmitTest;

    private static final String APPKEY = "194a23300eb1f";
    private static final String APPSECRECT = "6a47d06f965f1642d89bf187243f4db8";
    private static final int CODE_ING = 1;   //已发送，倒计时
    private static final int CODE_REPEAT = 2;  //重新发送
    private static final int SMSDDK_HANDLER = 3;  //短信回调
    @BindView(R.id.login_bg)
    ImageView loginBg;
    private String login_safebox;
    private static final int REQUEST_CODE_SETTING = 300;
    private TranslateAnimation animation;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case REQUEST_CODE_SETTING: {
//                //申请权限
//                AndPermission.with(this)
//                        .requestCode(REQUEST_CODE_ALL_LOGIN)
//                        .permission(Manifest.permission.ACCESS_COARSE_LOCATION,
//                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.READ_PHONE_STATE)
//                        .send();
//                break;
//            }
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        /**
//         * 转给AndPermission分析结果。
//         *
//         * @param object     要接受结果的Activity、Fragment。
//         * @param requestCode  请求码。
//         * @param permissions  权限数组，一个或者多个。
//         * @param grantResults 请求结果。
//         */
//        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
//    }

    public static final int REQUEST_CODE_ALL_LOGIN = 500;

//    @PermissionYes(REQUEST_CODE_ALL_LOGIN)
//    private void getMultiYes(List<String> grantedPermissions) {
//    }
//
//    @PermissionNo(REQUEST_CODE_ALL_LOGIN)
//    private void getMultiNo(List<String> deniedPermissions) {
//
//        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
//        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
//            AndPermission.defaultSettingDialog(LoginActivity.this, REQUEST_CODE_SETTING)
//                    .setTitle("友情提示：")
//                    .setMessage("运行该APP必须要您的电话、位置、内存权限，否则APP将无法使用。由于您勾选不再提示，请手动开启权限。")
//                    .setPositiveButton("立刻去开启这三个权限")
//                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(LoginActivity.this, "未给应用授权，页面退出！", Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(LoginActivity.this, SplashActivity.class));
////                            MyApplication.getInstance().exit();
//                            finish();
//                        }
//                    })
//                    .show();
//            // 更多自定dialog，请看上面。
//        } else {
//            Toast.makeText(LoginActivity.this, "未给应用授权，页面退出！", Toast.LENGTH_LONG).show();
//            startActivity(new Intent(LoginActivity.this, SplashActivity.class));
////            MyApplication.getInstance().exit();
//            finish();
//        }
//    }

    //制定布局页面
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_login);
        //当token验证失败后弹出吐司提示
        Intent intent = getIntent();
        String token_out_of_time = intent.getStringExtra("token_out_of_time");
        if (token_out_of_time != null) {
            showToast(Constant.TOKEN_OUT_OF_TIME);
        }
    }

    @Override
    protected void findViewById() {
        login_safebox = SPUtils.getString(LoginActivity.this, "loginToken");

        SharedPreferences preferences = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        if (!TextUtils.isEmpty(token)) {
            Intent intent = new Intent(LoginActivity.this, HomeTabActivity.class);
            startActivity(intent);
//            MyApplication.getInstance().exit();
            finish();
        } else {
            SMSSDK.initSDK(this, APPKEY, APPSECRECT);
            EventHandler eh = new EventHandler() {

                @Override
                public void afterEvent(int event, int result, Object data) {
                    Message msg = new Message();
                    msg.arg1 = event;
                    msg.arg2 = result;
                    msg.obj = data;
                    handler.sendMessage(msg);
                }

            };
            SMSSDK.registerEventHandler(eh);
            setListener();
        }
    }

    private int editStart;//光标开始位置
    private int editEnd;//光标结束位置
    private final int charMaxNum = 11;


    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public static void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
// 使用颜色资源
        tintManager.setStatusBarTintResource(R.color.colorPrimary11);
    }

    @Override
    protected void setListener() {
//状态栏颜色
        initSystemBar(LoginActivity.this);
//        setImmerseLayout(loginBg);

        /** 设置位移动画 向右位移150 */
        animation = new TranslateAnimation(0, -1000, 0, 0);
        animation.setDuration(10000);//设置动画持续时间
        animation.setRepeatCount(TranslateAnimation.INFINITE);//设置重复次数
        animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
        loginBg.startAnimation(animation);

        mTvGetPhoneCode.setOnClickListener(this);
        mBtnSubmitUser.setOnClickListener(this);
        mBtnSubmitTest.setOnClickListener(this);
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
                if (mEtPhone.getText().toString().trim().length() >= 11) {
                    if (!validatePhone()) {
                        showToast("手机号不对！");
                    } else {
//
//                        if (mEtPhone.getText().toString().trim().length() == charMaxNum) {
//                            mEtPhoneCode.requestFocus();
//                        }
                    }
                }
            }
        });
    }

    /**
     * 验证手机号码是否符合要求，11位 并且没有注册过
     *
     * @return 是否符合要求
     */
    private boolean validatePhone() {
        String phone = mEtPhone.getText().toString().trim();
        Pattern pattern = Pattern.compile("^1[35678][0-9]{9}$");
        Matcher m = pattern.matcher(phone);
        return m.matches();
    }

    @Override
    protected void processLogic() {
//        //申请权限
//        AndPermission.with(this)
//                .requestCode(REQUEST_CODE_ALL_LOGIN)
//                .permission(Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_PHONE_STATE)
//                .rationale(new RationaleListener() {
//                    @Override
//                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
//                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
//                        AndPermission.rationaleDialog(LoginActivity.this, rationale).show();
//                    }
//                })
//                .send();
    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击获取验证码控件
            case R.id.tv_get_phone_code:

                if (validatePhone()) {
                    //TODO  校验手机号正确后进行访问后台网络进行登陆处理

                    //启动获取验证码 86是中国
                    final String phone = mEtPhone.getText().toString().trim();
                    HttpData.getInstance().getLogin(phone, new Observer<LoginBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.toString();
                        }

                        @Override
                        public void onNext(LoginBean loginBean) {
                            String res_code = loginBean.getRes_code();

                            //手机号不在数据库中
                            if (res_code.equals("notexist")) {
                                ColorDialog dialog = new ColorDialog(LoginActivity.this);
                                dialog.setColor("#000");
                                dialog.setAnimationEnable(true);
                                dialog.setTitle("提示");
                                dialog.setContentText("用户尚未注册，请尽快联系后台。" +
                                        "\n1861696993");
                                dialog.setPositiveListener("知道了", new ColorDialog.OnPositiveListener() {
                                    @Override
                                    public void onClick(ColorDialog dialog) {
                                        dialog.cancel();
                                    }
                                }).show();
                                //请求网络出错
                            } else if (res_code.equals("error")) {
                                showToast("网络请求失败，请重试！");
                                //生成token成功
                            } else if (res_code.equals("success")) {

                                SMSSDK.getVerificationCode("86", phone);//发送短信验证码到手机号
                                timer.start();//使用计时器 设置验证码的时间限制

                                String token = loginBean.getToken();
                                SharedPreferences preferences = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("token", token);
                                editor.putString("phone", phone);
                                Log.d("token值：", token);
                                editor.commit();


//                                //特殊需求   返回直接去掉上面注释代码
//                                String token = loginBean.getToken();
//                                SharedPreferences preferences = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = preferences.edit();
//                                editor.putString("token", token);
//                                Log.d("token值：", token);
//                                editor.commit();
//                                Intent intent = new Intent(LoginActivity.this, HomeTabActivity.class);
//                                startActivity(intent);
//                                finish();

                            }
                        }
                    });


                } else {
                    showToast("手机号不正确");
                    mEtPhone.requestFocus();
                }
                break;
            //点击提交信息按钮
            case R.id.btn_submit_user:
//                mEtPhoneCode.requestFocus();
                if (validatePhone()) {
                    submitInfo();
                } else {
                    showToast("手机号不正确");
                    mEtPhone.requestFocus();
                }
                break;
            case R.id.btn_submit_test:
//                432F45B44C432414D2F97DF0E5743818   游客的token  固定值
                String token = "432F45B44C432414D2F97DF0E5743818";
                SharedPreferences preferences = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("token", token);
                editor.putString("phone", "12345678910");
                Log.d("token值：", token);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, HomeTabActivity.class);
                startActivity(intent);
//                MyApplication.getInstance().exit();
                finish();
                break;
        }
    }
    private static String mPhone=null;
    /**
     * 验证用户的其他信息
     * 这里验证两次密码是否一致 以及验证码判断
     */
    private void submitInfo() {
        if (validatePhone()) {
            //TODO  校验手机号正确后进行访问后台网络进行登陆处理

            //启动获取验证码 86是中国
            final String phone = mEtPhone.getText().toString().trim();
            HttpData.getInstance().getLogin(phone, new Observer<LoginBean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    e.toString();
                }

                @Override
                public void onNext(LoginBean loginBean) {
                    String res_code = loginBean.getRes_code();

                    //手机号不在数据库中
                    if (res_code.equals("notexist")) {
                        ColorDialog dialog = new ColorDialog(LoginActivity.this);
                        dialog.setColor("#000");
                        dialog.setAnimationEnable(true);
                        dialog.setTitle("提示");
                        dialog.setContentText("用户尚未注册，请尽快联系后台。" +
                                "\n1861696993");
                        dialog.setPositiveListener("知道了", new ColorDialog.OnPositiveListener() {
                            @Override
                            public void onClick(ColorDialog dialog) {
                                dialog.cancel();
                            }
                        }).show();
                        //请求网络出错
                    } else if (res_code.equals("error")) {
                        showToast("网络请求失败，请重试！");
                        //生成token成功
                    } else if (res_code.equals("success")) {

//                        SMSSDK.getVerificationCode("86", phone);//发送短信验证码到手机号
                        timer.start();//使用计时器 设置验证码的时间限制

                        String token = loginBean.getToken();
                        SharedPreferences preferences = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("token", token);
                        editor.putString("phone", phone);
                        Log.d("token值：", token);
                        editor.commit();

                        //密码验证
                        mPhone = mEtPhone.getText().toString().trim();
                        String code = mEtPhoneCode.getText().toString().trim();
//                        SMSSDK.submitVerificationCode("86", phone, code);//提交验证码  在eventHandler里面查看验证结果

                        //如果电话号码是13333，当验证码是8888时候就登录   否则从第三方获取验证码
                        if (mPhone.equals("13333333333")) {
                            if (code.equals("8888")) {
                                SPUtils.saveString(LoginActivity.this, "safecar_boss_login", phone);
                                showToast("短信验证成功");
                                Intent intent = new Intent(LoginActivity.this, HomeTabActivity.class);
                                startActivity(intent);
//                                MyApplication.getInstance().exit();
                                finish();
                            } else {
                                showToast("验证码错误！");
                            }
                        } else {
                            SMSSDK.submitVerificationCode("86", mPhone, code);//提交验证码  在eventHandler里面查看验证结果
                        }

//                                //特殊需求   返回直接去掉上面注释代码
//                                String token = loginBean.getToken();
//                                SharedPreferences preferences = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = preferences.edit();
//                                editor.putString("token", token);
//                                Log.d("token值：", token);
//                                editor.commit();
//                                Intent intent = new Intent(LoginActivity.this, HomeTabActivity.class);
//                                startActivity(intent);
//                                finish();

                    }
                }
            });


        } else {
            showToast("手机号不正确");
            mEtPhone.requestFocus();
        }
        //所需  去掉上面的就可以了
//        //密码验证
//        String phone = mEtPhone.getText().toString().trim();
//        String code = mEtPhoneCode.getText().toString().trim();
//        SMSSDK.submitVerificationCode("86", phone, code);//提交验证码  在eventHandler里面查看验证结果
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        String trim = mEtPhone.getText().toString().trim();
                        SPUtils.saveString(LoginActivity.this, "safecar_boss_login", trim);
                        showToast("短信验证成功");
                        Intent intent = new Intent(LoginActivity.this, HomeTabActivity.class);

                        startActivity(intent);
//                        MyApplication.getInstance().exit();
                        finish();
                    }
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    showToast("验证码已经发送");

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表
                    showToast("获取国家列表成功");

                } else if (event == SMSSDK.RESULT_ERROR) {
                    try {
                        Throwable throwable = (Throwable) data;
                        throwable.printStackTrace();
                        JSONObject object = new JSONObject(throwable.getMessage());
                        String des = object.optString("detail");//错误描述
                        int status = object.optInt("status");//错误代码
                        if (status > 0 && !TextUtils.isEmpty(des)) {
                            showToast(des);
                            return;
                        }
                    } catch (Exception e) {
                    }
                }
            } else {
                try {
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");//错误描述
                    int status = object.optInt("status");//错误代码
                    if (status > 0 && !TextUtils.isEmpty(des)) {
                        showToast(des);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onRefresh() {
    }

    @Override
    public void onLoadmore() {
    }
    /**
     * 使用计时器来限定验证码
     * 在发送验证码的过程 不可以再次申请获取验证码 在指定时间之后没有获取到验证码才能重新进行发送
     * 这里限定的时间是60s
     */
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTvGetPhoneCode.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            mTvGetPhoneCode.setEnabled(true);
            mTvGetPhoneCode.setText("获取验证码");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        MyApplication.getInstance().addTemActivity(LoginActivity.this);
        MyApplication.getInstance().exit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (TextUtils.isEmpty(login_safebox)) {
            //防止使用短信验证 产生内存溢出问题
            SMSSDK.unregisterAllEventHandler();
        }
        animation.cancel();
    }
}
