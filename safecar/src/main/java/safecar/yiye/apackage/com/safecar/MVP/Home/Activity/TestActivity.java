package safecar.yiye.apackage.com.safecar.MVP.Home.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tencent.bugly.Bugly;

import safecar.yiye.apackage.com.safecar.MVP.Home.ZhangPhilListView;
import safecar.yiye.apackage.com.safecar.R;

public class TestActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_test);

        Button viewById = (Button) findViewById(R.id.button_test);



    }
}

