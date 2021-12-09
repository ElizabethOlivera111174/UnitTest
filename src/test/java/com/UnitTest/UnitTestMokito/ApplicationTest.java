package com.UnitTest.UnitTestMokito;


import com.UnitTest.UnitTestMokito.Exeptions.DefaultException;
import com.UnitTest.UnitTestMokito.Service.CuentaService;

import com.UnitTest.UnitTestMokito.models.Banco;
import com.UnitTest.UnitTestMokito.models.Cuenta;
import com.UnitTest.UnitTestMokito.repositories.BancoRepository;
import com.UnitTest.UnitTestMokito.repositories.CuentaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static com.UnitTest.UnitTestMokito.Service.Datos.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


@SpringBootTest
class ApplicationTest {

    @MockBean
    CuentaRepository cuentaRepositoryMock;

    @MockBean
    BancoRepository bancoRepositoryMock;

    @Autowired
    CuentaService cuentaService;

  /*  @Test
    void main() {
        Application.main(new String[] {});
    }*/
    @Test
    void contextLoads() {
        //------------------------ Indicando el comportamiento de los Mock -------------------------
        //Pruebas de repositorios y servicios
        when(cuentaRepositoryMock.findById(1L)).thenReturn(crearCuenta001());
        when(cuentaRepositoryMock.findById(2L)).thenReturn(crearCuenta002());
        when(bancoRepositoryMock.findById(1L)).thenReturn(crearBanco());

        //------------------------- Comprobando Saldo ----------------------------------------------
        //Se testea desde la clase Datos el sercicio. Se extra el saldo de dos cuentas
        BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
        BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);
        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        //--------------------------- Segunda Prueba // Comprobando Transferencia ------------------

        //Se testea el servicio con una transferencia de la cuenta 1 hacia la cuenta dos con el metodo transferir
        cuentaService.transferir(1L, 2L, new BigDecimal("100"), 1L);

        //Revicion de los saldos de la cuenta 1 y dos
        saldoOrigen = cuentaService.revisarSaldo(1L);
        saldoDestino = cuentaService.revisarSaldo(2L);

        assertEquals("900", saldoOrigen.toPlainString());
        assertEquals("2100",saldoDestino.toPlainString());

        //-------------------------- Pueba Total Transferecia ------------------------------------------
        int total = cuentaService.revisarTotalTransferencias(1L);
        assertEquals(1, total);

        // ---------------------------------- Verivificacion -------------------------------------------
        //Cuantas veces se invocaron los metodos de cada mock
        //se invoco 3 veces
        verify(cuentaRepositoryMock, times(3)).findById(1L);
        verify(cuentaRepositoryMock, times(3)).findById(2L);
        //una por cada metodo
        verify(cuentaRepositoryMock, times(2)).save(any(Cuenta.class));

        verify(bancoRepositoryMock, times(2)).findById(1L);
        verify(bancoRepositoryMock).save(any(Banco.class));

    }

    @Test
    void contextLoads2() {
        //------------------------ Indicando el comportamiento de los Mock -------------------------
        //Pruebas de repositorios y servicios
        when(cuentaRepositoryMock.findById(1L)).thenReturn(crearCuenta001());
        when(cuentaRepositoryMock.findById(2L)).thenReturn(crearCuenta002());
        when(bancoRepositoryMock.findById(1L)).thenReturn(crearBanco());

        //------------------------- Comprobando Saldo ----------------------------------------------
        //Se testea desde la clase Datos el sercicio. Se extra el saldo de dos cuentas
        BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
        BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);
        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());


        //--------------------------- Segunda Prueba // Comprobando Transferencia ------------------

        //Se testea el servicio con una transferencia de la cuenta 1 hacia la cuenta dos con el metodo transferir
        //Probando error
        assertThrows(DefaultException.class, ()->{
            cuentaService.transferir(1L, 2L, new BigDecimal("1200"), 1L);});


        //Revicion de los saldos de la cuenta 1 y dos
        saldoOrigen = cuentaService.revisarSaldo(1L);
        saldoDestino = cuentaService.revisarSaldo(2L);

        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000",saldoDestino.toPlainString());

        //-------------------------- Pueba Total Transferecia ------------------------------------------
        int total = cuentaService.revisarTotalTransferencias(1L);
        assertEquals(0, total);

    }

    @Test
    void contextLoad3(){
        //Test de comprobacion si los metodos estan devolviendo siempre los mismo objetos
        when(cuentaRepositoryMock.findById(1l)).thenReturn(crearCuenta001());
        Cuenta cuenta1 = cuentaService.findById(1L);
        Cuenta cuenta2 = cuentaService.findById(1L);

        assertSame(cuenta1 , cuenta2);
        assertTrue(cuenta1 == cuenta2);
        assertEquals("Andres", cuenta1.getPersona());
        assertEquals("Andres", cuenta2.getPersona());
        verify(cuentaRepositoryMock, times(2)).findById(1L);
    }
}