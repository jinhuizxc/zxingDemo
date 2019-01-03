package com.jh.zxingdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 * Android动感系列-扫一扫
 * https://www.imooc.com/learn/648
 */
public class MainActivity extends AppCompatActivity {

    private Button bt_scan, bt_make;
    private TextView tv_result;
    private EditText editText;
    private ImageView iv_show;
    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_scan = findViewById(R.id.bt_scan);
        tv_result = findViewById(R.id.tv_result);
        editText = findViewById(R.id.et_text);
        bt_make = findViewById(R.id.bt_make);
        iv_show = findViewById(R.id.iv_show);
        cb = findViewById(R.id.cb);

        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 扫描二维码并显示
                startActivityForResult(new Intent(MainActivity.this,
                        CaptureActivity.class), 0);
            }
        });

        bt_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 生成二维码
                String input = editText.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
//                    Bitmap bitmap = EncodingUtils.createQRCode(input,
//                            500, 500, null);
                    Bitmap bitmap = EncodingUtils.createQRCode(input,
                            500, 500,
                            cb.isChecked() ? BitmapFactory.decodeResource(getResources(), R.drawable.image) : null);
                    iv_show.setImageBitmap(bitmap);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            Log.e("test", "onActivityResult: " + result);
            tv_result.setText(result);

        }
    }
}
