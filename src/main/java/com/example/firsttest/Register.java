package com.example.firsttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    EditText email;
    EditText password;
    TextView nomApp, desc;
    EditText Nom;
    EditText prenom;
    EditText tel;
    Button register;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        mAuth = FirebaseAuth.getInstance();
    nomApp = (TextView) findViewById(R.id.nomApp);
    nomApp.setOnClickListener(this);

    register = (Button) findViewById(R.id.register);
    register.setOnClickListener(this);

        Nom = (EditText) findViewById(R.id.Nom);
        Nom.setOnClickListener(this);

        prenom = (EditText) findViewById(R.id.prenom);
        prenom.setOnClickListener(this);

       tel = (EditText) findViewById(R.id.tel);
        tel.setOnClickListener(this);

        email = (EditText) findViewById(R.id.email);
        email.setOnClickListener(this);

        password = (EditText) findViewById(R.id.password);
        password.setOnClickListener(this);

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        progressbar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
         switch(v.getId()){
             case R.id.nomApp:
                 startActivity(new Intent(this, Login.class));
                 break;
             case R.id.register:
                 register();
                 break;
         }

    }

    private void register() {
        String nom= Nom.getText().toString().trim();
        String Prenom= prenom.getText().toString().trim();
        String Tel= tel.getText().toString().trim();
        String mail= email.getText().toString().trim();
        String pass= password.getText().toString().trim();

        if(nom.isEmpty()){
            Nom.setError("Nom is required");
            Nom.requestFocus();
            return;
        }
        if(Prenom.isEmpty()){
            prenom.setError("prenom is required");
            prenom.requestFocus();
            return;
        }
        if(mail.isEmpty()){
            email.setError("email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            email.setError("Please provide valid email!");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        if(pass.length()<6){
            password.setError("Min password should be more than 6 characters ");
            password.requestFocus();
            return;
        }
        if(Tel.isEmpty()){
            tel.setError("Phone number is required");
            tel.requestFocus();
            return;
        }
    progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user= new User(nom,Prenom,mail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "User has been registred sucessfully", Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.VISIBLE);

                                    }else{
                                        Toast.makeText(Register.this,"Failed to register ! try again!", Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Register.this,"Failed to register ! try again!", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);

                        }
                    }
                });

    }
}