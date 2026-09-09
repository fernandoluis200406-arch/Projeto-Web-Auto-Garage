package org.example.controller;

public class Validacao {
    public static boolean validarLogin(String usuario, String senha){
        if (usuario.equals("admin") && senha.equals("1234")){
            return true;
        }
        return false;
    }
}
