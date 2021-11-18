package com.vianna.entregasapp.service;

import com.vianna.entregasapp.model.dto.InputLoginDTO;
import com.vianna.entregasapp.model.dto.UserDTO;
import com.vianna.entregasapp.service.task.LoginTask;

public class UserService {

    public UserDTO logar(String login, String senha){//2

        InputLoginDTO log = new InputLoginDTO(login, senha);

        LoginTask task = new LoginTask();//cada task é consumida por um serviço // se espera o retorno de um UserDTO preenchido nesse caso
        try {
            return task.execute(log).get();
        } catch (Exception e) {
            return null;
        }
    }
}
