package com.vianna.entregasapp.service;

import com.vianna.entregasapp.model.dto.InputLoginDTO;
import com.vianna.entregasapp.model.dto.LoginDTO;
import com.vianna.entregasapp.service.task.LoginTask;

public class LoginService {

    public LoginDTO logar(String login, String senha){//2


        InputLoginDTO log = new InputLoginDTO(login, senha);

        LoginTask task = new LoginTask();//cada task é consumida por um serviço // se espera o retorno de um UserDTO preenchido nesse caso
        try {
            return task.execute(log).get();
        } catch (Exception e) {
            return null;
        }








//
//        Retrofit r = new RetrofitConfig().getRetrofit();//pega a variavel que é o servico a ser consumido
//        IUserService userService = r.create(IUserService.class);//utiliza o retrofit para criar dinamicamente uma classe que implementa a IFilmeService (logo precisa imlementar todos os metodos da interface)
//
//        Call<UserDTO> user = userService.getUser("Basic YXBwOmFzZHNAQXNkIzIzMjQzRGFz", "password",
//                login, senha);
//
//
//
//        Log.i("USER", user.request().toString());
//        user.enqueue(new Callback<UserDTO>() {//tenta executar a solicitacao
//            @Override
//            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {//se o servidor respondeu
//
//                if (response.isSuccessful()){//se retornou ok
//                    UserDTO usuario = response.body();
//
////                    Toast.makeText(getApplicationContext(), "Quantidade de Filmes "+lFilme.size(),
////                            Toast.LENGTH_SHORT).show();
//                } else {
//
////                    Toast.makeText(getApplicationContext(), "Cade a lista?",
////                            Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserDTO> call, Throwable t) {//se nao respondeu
////                Toast.makeText(getApplicationContext(), "DEU RUIM",
////                        Toast.LENGTH_SHORT).show();
//            }
//        });
//        try {
//            return user.execute().body();
//        } catch (Exception e) {
//            return null;
//        }
    }
}
