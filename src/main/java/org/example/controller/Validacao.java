package org.example.controller;

import java.util.HashMap;
import java.util.Map;

public class Validacao {

    private static final Map<String, String> usuarios = new HashMap<>();
    private static final Map<String, String> perfis = new HashMap<>();

    static {

        usuarios.put("gabi", "1234");
        perfis.put("gabi", "USUARIO");

        usuarios.put("admin", "1234");
        perfis.put("admin", "ADMIN");

        usuarios.put("luis", "1234");
        perfis.put("luis", "ADMIN");
    }

    public static boolean validarLogin(String usuario, String senha) {

        if (usuario == null || senha == null) {
            return false;
        }

        return senha.equals(usuarios.get(usuario));
    }

    public static String getPerfil(String usuario) {
        return perfis.get(usuario);
    }
}