package com.azka.bp_3_p3_20220810124;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));
        Toolbar toolbar  = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Drawable backArrow = getResources().getDrawable(R.drawable.baseline_arrow_back_ios_24);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
        }
        TextView title = findViewById(R.id.toolbar_title);
        title.setText("Register");
        Button btn = findViewById(R.id.btn);
        TextInputEditText username = findViewById(R.id.username);
        TextInputEditText pass = findViewById(R.id.password);
        TextInputEditText email = findViewById(R.id.email);
        username.setHint("Username");
        pass.setHint("Password");
        email.setHint("Email");
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    username.setHint(null);
                } else {
                    username.setHint("Username");
                }
            }
        });
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    pass.setHint(null);
                } else {
                    pass.setHint("Password");
                }
            }
        });
        btn.setOnClickListener(v -> {
            String u = username.getText().toString();
            String p = pass.getText().toString();
            String e = email.getText().toString();
            if (u.isEmpty() || p.isEmpty()){
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Username, Password dan Email harus terisi!", Snackbar.LENGTH_SHORT);
                View snackbarView = snackbar.getView();
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackbarView.getLayoutParams();
                params.gravity = Gravity.TOP;
                params.setMargins(100, 200, 100, 0);
                snackbarView.setLayoutParams(params);
                snackbar.show();
            }else{
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("username", u);
                intent.putExtra("password", p);
                intent.putExtra("email", e);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}