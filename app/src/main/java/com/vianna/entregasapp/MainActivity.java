package com.vianna.entregasapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.vianna.entregasapp.databinding.ActivityMainBinding;
import com.vianna.entregasapp.model.dto.LoginDTO;
import com.vianna.entregasapp.ui.entregas.EntregaAddFragment;
import com.vianna.entregasapp.ui.entregas.EntregasFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    //----------------------inicio
    NavigationView navigationView;
    ImageView ivFotoNavBar;
    TextView cpNome, cpEmail;
    LoginDTO userLogado;
    //----------------------fim


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //----------------------inicio
        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
        navigationView = binding.navView;
        Toolbar tool = binding.appBarMain.toolbar;//toolbar a qual o drawner vai ser atrelado

        vinculo();//binding

        navigationView.setNavigationItemSelectedListener(clickNavigator());
        //----------------------fim


        //----------------------inicio
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(//botao expansivo (que mostra as opções do menu (drawner)) que vai ser colocado na toolbar/////botaoq ue vai ser associado ao drawner
                this,
                drawer,
                tool,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //----------------------fim
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }

    //----------------------inicio
    private NavigationView.OnNavigationItemSelectedListener clickNavigator() {
        return new NavigationView.OnNavigationItemSelectedListener() {//listener dos botoes do menu
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.nav_entrar_all){//se o botao nav_login for clicado...
                    Intent itt = new Intent(getApplicationContext(), LoginActivity.class);

                    viewLoginActivity.launch(itt);
//                } else if (item.getItemId() == R.id.nav_home){
//                    HomeFragment home = new HomeFragment();//cria o fragmento que vai ser chamado
//
//                    Bundle args = new Bundle();//cria um bundle para armazenar argumentos
//                    args.putString("nome", "Zezin da Silva");//cria um novo argumento chamado nome
//                    home.setArguments(args);//coloca os argumentos no fragmento
//
//                    changeFragment(home);//chama o metodo qu faz a troca do fragment
//
//                } else if (item.getItemId() == R.id.nav_filmes){
//
//                    GalleryFragment gal = new GalleryFragment();//cria o fragmento que vai ser chamado
//
//                    Bundle args = new Bundle();//cria um bundle para armazenar argumentos
//                    args.putString("token", userLogado.getToken());//cria um novo argumento chamado nome
//                    gal.setArguments(args);//coloca os argumentos no fragmento
//
//                    changeFragment(gal);//chama o metodo qu faz a troca do fragment
//
////                    testeRetrofit();
                } else if (item.getItemId() == R.id.nav_add_entrega_cliente){//nova entrega
//                    Intent itt = new Intent(getApplicationContext(), CadNovaEntregaActivity.class);
//
//                    viewCadEntregaActivity.launch(itt);

                    EntregaAddFragment ent = new EntregaAddFragment();//cadastro de entrega
                    changeFragment(ent);

                } else if (item.getItemId() == R.id.nav_entregas_atuais_all){//tela d elista de entregas
                    EntregasFragment ent = new EntregasFragment();//cria o fragmento que vai ser chamado

                    changeFragment(ent);//chama o metodo qu faz a troca do fragment

                } else if (item.getItemId() == R.id.nav_sair_all){
                    atualizaMenuDeslogado();

                    //shared prefs ao sair
                    SharedPreferences prefs = getSharedPreferences("entregasApp",MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.remove("accessToken");
                    prefsEditor.remove("ROLE");
                    prefsEditor.commit();
                    //

                    Toast.makeText(getApplicationContext(), "Até mais!",
                            Toast.LENGTH_SHORT).show();

                    finish();
                }

                DrawerLayout drawer = binding.drawerLayout;//drawer é o menuzinho com as opções
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        };
    }
    //----------------------fim



    //----------------------inicio
    ActivityResultLauncher<Intent> viewLoginActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.i("LOGIN", "onActivityResult:  Login retorno - "+result.getResultCode());


                    if (result.getResultCode() == 10){
                        userLogado = (LoginDTO) result.getData().getExtras().getSerializable("userLogado");

                        //shared prefs ao logar
                        SharedPreferences prefs = getSharedPreferences("entregasApp",MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = prefs.edit();
                        prefsEditor.putString("accessToken", userLogado.getAccess_token());
                        prefsEditor.putString("ROLE", userLogado.getROLE());
                        prefsEditor.commit();
                        //
                        Log.i("TOKEN", "ACESS TOKEN DO USUARIO LOGADO "+userLogado.getAccess_token());
                        Toast.makeText(getApplicationContext(), "Bem-vindo!",
                                Toast.LENGTH_SHORT).show();

                        atualizaMenuLogado(userLogado);

//                        EntregasFragment ent = new EntregasFragment();//cria o fragmento que vai ser chamado
//
//                        Bundle args = new Bundle();//cria um bundle para armazenar argumentos
//                        args.putString("nome", "Zezin da Silva");//cria um novo argumento chamado nome
//                        ent.setArguments(args);//coloca os argumentos no fragmento
//
//                        changeFragment(ent);//chama o metodo qu faz a troca do fragment
                    } else if (result.getResultCode() == 15){
                        userLogado = null;
                        Toast.makeText(getApplicationContext(), "Login ou Senha incorreta",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    //----------------------fim

    //----------------------inicio
    ActivityResultLauncher<Intent> viewCadEntregaActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.i("LOGIN", "onActivityResult:  Nova entrega retorno - "+result.getResultCode());

                    if (result.getResultCode() == 10){
                        userLogado = (LoginDTO) result.getData().getExtras().getSerializable("userLogado");

                        Toast.makeText(getApplicationContext(), "Entrega solicitada com sucesso!",
                                Toast.LENGTH_SHORT).show();

                        EntregasFragment ent = new EntregasFragment();//cria o fragmento que vai ser chamado

                        changeFragment(ent);//chama o metodo qu faz a troca do fragment
                    } else if (result.getResultCode() == 15){
                        userLogado = null;
                        Toast.makeText(getApplicationContext(), "Erro ao solicitar entrega",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    //----------------------fim

    //----------------------inicio
    private void atualizaMenuLogado(LoginDTO userLogado) {
        if (userLogado.getROLE().equals("CLIENTE")) {
            ivFotoNavBar.setImageResource(R.drawable.client_consumer_customer_user_avatar_icon);
            navigationView.getMenu().findItem(R.id.nav_add_entrega_cliente).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_entregas_atuais_all).setTitle("Minhas Entregas");
                    } else if (userLogado.getROLE().equals("MOTOBOY")) {
            ivFotoNavBar.setImageResource(R.drawable.motoboy);
            navigationView.getMenu().findItem(R.id.nav_entregas_atuais_all).setTitle("Entregas em Andamento");
            navigationView.getMenu().findItem(R.id.nav_entregas_disponiveis_moto).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_entregas_historico_moto).setVisible(true);
        } else {
            ivFotoNavBar.setImageResource(R.drawable.administrator_user_icon);
            navigationView.getMenu().findItem(R.id.nav_entregas_atuais_all).setTitle("Entregas");
        }

        navigationView.getMenu().findItem(R.id.nav_entregas_atuais_all).setVisible(true);//entregas aparece pra todos mas o nome muda de acordo com o ROLE
        navigationView.getMenu().findItem(R.id.nav_sair_all).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_entrar_all).setVisible(false);
        cpNome.setText(userLogado.getNome());
        cpEmail.setText(userLogado.getEmail());
    }

    private void atualizaMenuDeslogado() {

        navigationView.getMenu().findItem(R.id.nav_entrar_all).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_entregas_atuais_all).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_sair_all).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_add_entrega_cliente).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_entregas_disponiveis_moto).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_entregas_historico_moto).setVisible(false);


        ivFotoNavBar.setImageResource(R.mipmap.ic_launcher_round);
        cpNome.setText(getResources().getString(R.string.nav_header_title));//coloca o texto que está no strings.xml
        cpEmail.setText(getResources().getString(R.string.nav_header_subtitle));//coloca o texto que está no strings.xml

        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.activity_main_drawer);

    }
    //----------------------fim

    //----------------------inicio
    private void vinculo() {
        cpNome =  navigationView.getHeaderView(0).findViewById(R.id.nhNome);
        cpEmail =  navigationView.getHeaderView(0).findViewById(R.id.nhEmail);
        ivFotoNavBar = navigationView.getHeaderView(0).findViewById(R.id.iv_foto);
    }
    //----------------------fim

    //----------------------inicio
    private void changeFragment(Fragment frag) {//metodo responsavel por trocar as fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.nav_host_fragment_content_main,//inicia a transacao///indica aonde o fragmento será trocado
                frag);//qual fragmento será inserido

        transaction.commit();
    }
    //----------------------fim




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}