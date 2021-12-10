package com.UnitTest.UnitTestMokito.Service;

import com.UnitTest.UnitTestMokito.models.Banco;
import com.UnitTest.UnitTestMokito.models.Cuenta;

import java.math.BigDecimal;
import java.util.Optional;

public class Datos {
//Se puede hacer de dos formas, creando las cuentas de una forma estatica
    //Lo cual hace probavle que fallen los test

    // public static final Cuenta CUENTA_001 = new Cuenta(1L, "Andrés", new BigDecimal("1000"));
    // public static final Cuenta CUENTA_002 = new Cuenta(2L, "Jhon", new BigDecimal("2000"));
    // public static final Banco BANCO = new Banco(1L, "El banco financiero", 0);

    //por eso se lo hace de forma dimanica crando una instancia por cada test
    public static Optional<Cuenta> crearCuenta001() {
        return Optional.of(new Cuenta(1L, "Andres", new BigDecimal("1000")));
    }

    public static Optional<Cuenta> crearCuenta002() {
        return Optional.of(new Cuenta(2L, "Jhon", new BigDecimal("2000")));
    }

    public static Optional<Banco> crearBanco() {
        return Optional.of(new Banco(1L, "El banco financiero", 0));
    }

    //public static User crearUser() {
        //return new User(1l,"user", "Last_user", "user@gmail.com", "1234");
    //}

    //public static User crearUser1() {
        //return new User(2l,"user1", "Last_user1", "user1@gmail.com", "1234");
   // }




}
