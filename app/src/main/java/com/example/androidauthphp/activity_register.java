package com.example.androidauthphp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidauthphp.Common.Common;
import com.example.androidauthphp.Model.APIResponse;
import com.example.androidauthphp.Remote.IMyAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_register extends AppCompatActivity {

    TextView txt_sign_in;
    EditText edt_email,edt_password,edt_name;
    Button btn_register;

    IMyAPI mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mService= Common.getAPI();
        txt_sign_in=(TextView)findViewById(R.id.txt_login);
        edt_email=(EditText) findViewById(R.id.edit_email);
        edt_name=(EditText) findViewById(R.id.edit_name);
        edt_password=(EditText) findViewById(R.id.edit_password);
        btn_register=(Button) findViewById(R.id.btn_register);

        txt_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser(edt_name.getText().toString(),edt_email.getText().toString(),edt_password.getText().toString());
            }
        });
    }

    private void createNewUser(String name, String email, String password) {
        mService.registerUser(name,email,password)
                .enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        APIResponse result=response.body();
                        if(result.isError()){
                            Toast.makeText(activity_register.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(activity_register.this, "Register Success "+result.getUid(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {

                        Toast.makeText(activity_register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
