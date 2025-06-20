package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText objEmail = findViewById(R.id.txtEmail);
                EditText objPass = findViewById(R.id.txtPassword);

                String txtEmail = objEmail.getText().toString();
                String txtPassword = objPass.getText().toString();

//                if(txtEmail.equals("hosiquy") && txtPassword.equals("3110"))
//                {
                    Intent it = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(it);
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),"LOGIN FAIL", Toast.LENGTH_LONG).show();
//                }
            }
        });
        TextView register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(it);
            }
        });
    }
}