package com.example.loginrest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.entities.User;
import com.example.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroNovoUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "CadastroNovoUsuarioActi";
    private TextView nome,fone,mail,senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_usuario);
    }

    public void salvar(View view) {
         nome = (TextView)findViewById(R.id.et_nome);
         fone = (TextView)findViewById(R.id.et_fone);
         mail = (TextView)findViewById(R.id.et_mail);
         senha = (TextView)findViewById(R.id.et_senha);
        User user = new User(mail.getText().toString(),nome.getText().toString(),senha.getText().toString(),fone.getText().toString());
        RetrofitService.getServico().cadastrarNovoUsuario(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: "+response.body().getId());
                Toast.makeText(CadastroNovoUsuarioActivity.this, "Usuário criado com sucesso. Você será redirecionado para tela de login", Toast.LENGTH_SHORT).show();
				
				finish();
                
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
    
}
