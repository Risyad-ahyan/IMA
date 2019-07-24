package com.example.ima.ima;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.ima.Utils.TypefaceUtil;

public class MainActivity extends Activity {

    private Button login;
    private EditText nip, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        declareId();
        setOnClick();

    }

    private void initView(){
        // change type face
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/HelveticaNeueLTPro-MdCn.otf");
    }
    private boolean validasi(){

        boolean isValid =  false;
        String nipUser  = nip.getText().toString();
        String passUser = password.getText().toString();



        if(TextUtils.isEmpty(nipUser)&&TextUtils.isEmpty(passUser)){

            Toast.makeText(this, "Nip dan Password harus diisi", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(nipUser)){

            Toast.makeText(this, "NIP harus diisi", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(passUser)){

            Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show();

        }else {

            isValid = true;
        }

        return isValid;

    }

    private void setOnClick(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validasi()){
                    startActivity(new Intent(MainActivity.this, Dashboard_Activity.class));
                }
            }
        });
    }

    private void declareId(){

        login       = (Button) findViewById(R.id.btnLogin);
        nip         = (EditText) findViewById(R.id.nip);
        password    = (EditText) findViewById(R.id.password);
        password.setTypeface(Typeface.SERIF);

    }
}
