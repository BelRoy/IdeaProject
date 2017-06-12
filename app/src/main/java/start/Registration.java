package start;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devqt.idea.project.Navigate;
import com.devqt.idea.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends Activity implements View.OnClickListener{

    private EditText mail;
    private EditText pass;
    private FirebaseAuth auth;
    private ProgressDialog progress;
    private ImageView check;
    private TextView sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        auth = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);

        check = (ImageView) findViewById(R.id.check);
        mail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        sign_in = (TextView) findViewById(R.id.textv);

        check.setOnClickListener(this);
        mail.setOnClickListener(this);
        pass.setOnClickListener(this);
        sign_in.setOnClickListener(this);

        findViewById(R.id.textv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, LogIn.class));
                finish();
            }
        });

    }

    private void registerUser() {

        String email = mail.getText().toString().trim();
        String password = pass.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Your e-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Your password", Toast.LENGTH_SHORT).show();
            return;
        }

        progress.setMessage("Registering User");
        progress.show();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), Navigate.class));
                        }else{

                            Toast.makeText(Registration.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progress.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if (view == check) {
            registerUser();
        }

        if (view == sign_in) {

            startActivity(new Intent(this, LogIn.class));

        }


    }
}

