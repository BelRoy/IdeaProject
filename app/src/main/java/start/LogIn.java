package start;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devqt.idea.project.NavigatorMenu;
import com.devqt.idea.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn  extends AppCompatActivity implements View.OnClickListener{

    private EditText mail;
    private EditText pass;
    private FirebaseAuth auth;
    private ImageView check;
    private ProgressDialog progress;
    private TextView reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        auth = FirebaseAuth.getInstance();
        mail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        check = (ImageView) findViewById(R.id.check);
        reg = (TextView) findViewById(R.id.reg);
        progress = new ProgressDialog(this);

        reg.setOnClickListener(this);
        check.setOnClickListener(this);

        if(auth.getCurrentUser() != null){
            finish();

            startActivity(new Intent(getApplicationContext(), NavigatorMenu.class));
        }
    }


    private void loginUser() {

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

        progress.setMessage("Please wait...");
        progress.show();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), NavigatorMenu.class));
                        } else {
                            Toast.makeText(LogIn.this, "Please, try again", Toast.LENGTH_SHORT).show();
                        }
                        progress.dismiss();
                    }
                });
    }

    private void reg() {
        finish();

        startActivity(new Intent(this, Registration.class));
    }


    @Override
    public void onClick(View view) {

        if (view == check) {
            loginUser();
        }

        if (view == reg) {
        reg();

        }
    }
}