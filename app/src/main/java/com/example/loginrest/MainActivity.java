package com.example.loginrest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entities.UserLog;
import com.example.entities.UserLogReturn;
import com.example.services.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView usuario, senha;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CadastroNovoUsuarioActivity.class));
                //startActivity(new Intent(MainActivity.this,ListaDeProdutosActivity.class));
            }
        });

        usuario = findViewById(R.id.tv_user_name);
        senha = findViewById(R.id.tv_senha);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logar(View view) {
        String user = usuario.getText().toString();
        String senha = this.senha.getText().toString();

        if(user.isEmpty() || senha.isEmpty()){
            Toast.makeText(getApplicationContext(),"Preencha os campos corretamente!", Toast.LENGTH_LONG).show();
        }else {

            UserLog userLog = new UserLog(user, senha);
            RetrofitService.getServico().autenticar(userLog).enqueue(new Callback<UserLogReturn>() {
                @Override
                public void onResponse(Call<UserLogReturn> call, Response<UserLogReturn> response) {
                    Log.d(TAG, "onResponse: " + response.body().getToken());

                    if(isLogado(response.body().getToken())) {
                        SharedPreferences sp = getSharedPreferences("usuario", 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("token", response.body().getToken());
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, ListaDeProdutosActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Email ou Senha Inválidos!",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UserLogReturn> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(MainActivity.this, "Erro. Tente novamente!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private boolean isLogado(String token){
        if(token.equals("111")){
            return true;
        }
        return false;
    }
}
