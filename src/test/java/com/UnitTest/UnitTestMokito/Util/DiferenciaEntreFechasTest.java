package com.UnitTest.UnitTestMokito.Util;

import com.UnitTest.UnitTestMokito.util.DiferenciaEntreFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.Period;


class DiferenciaEntreFechasTest {

    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas;



    @Test
    void testCalculateYearsOfIndependency() {
        diferenciaEntreFechas = new DiferenciaEntreFechas();
        String fechaIndependencia = "27/02/1844";

        Period resultado = diferenciaEntreFechas.calculateYearsOfIndependency(fechaIndependencia);

        Assertions.assertEquals(9,resultado.getMonths() );
        Assertions.assertEquals(12,resultado.getDays() );
        Assertions.assertEquals(177,resultado.getYears());
    }
}