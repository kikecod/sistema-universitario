package com.example.sistemauniversitario.validation;

import org.springframework.stereotype.Component;

@Component
public class EstudianteValidator {

//    public boolean validarFormatoEmail(String email) {
//        return email != null && email.matches("^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$");
//    }

    public boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("^\\d{7,15}$");
    }
}