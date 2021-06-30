package com.example.photolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.photolist.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private Button bCadastra;
    private FirebaseAuth mAuth;
    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        bCadastra = findViewById(R.id.bCadastra);
        mAuth = FirebaseAuth.getInstance();
        
        bCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dadosFormulario();
                criarLogin();
            }
        });

    }

    private void criarLogin() {
        mAuth.createUserWithEmailAndPassword(u.getEmail(),u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId(user.getUid());
                            u.salvarDados();
                            startActivity(new Intent(CadastroActivity.this,MenuActivity.class));
                        }else{
                            Toast.makeText(CadastroActivity.this, "Erro ao criar login.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void dadosFormulario() {
        if(etNome.getText().toString() == "" || etEmail.getText().toString() == "" || etSenha.getText().toString() == ""){
            Toast.makeText(this, "Preencha todos os campos!",Toast.LENGTH_LONG);
        }else{
            u = new Usuario();
            u.setNome(etNome.getText().toString());
            u.setEmail(etEmail.getText().toString());
            u.setSenha(etSenha.getText().toString());
        }
    }
}