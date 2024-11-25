package com.viniciusjanner.utils

import com.viniciusjanner.apigithub.utils.toBrazilianDate
import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionTest {

    //
    // teste toBrazilianDate converte data ISO para o formato brasileiro
    //
    @Test
    fun `test toBrazilianDate converts ISO date to Brazilian format`() {
        // Data ISO para teste
        val isoDate = "2024-11-25T10:30:00Z"

        // Data brasileira esperada após a conversão
        val expectedBrazilianDate = "25/11/2024 07:30:00"  // O horário será ajustado para GMT-3

        // Converte a data ISO para o formato brasileiro
        val actualBrazilianDate = isoDate.toBrazilianDate()

        // Verifica se a conversão está correta
        assertEquals(expectedBrazilianDate, actualBrazilianDate)
    }

    //
    // teste toBrazilianDate retorna string original quando a entrada é inválida
    //
    @Test
    fun `test toBrazilianDate returns original string when input is invalid`() {
        // Data inválida para teste
        val invalidDate = "invalid-date"

        // A função deve retornar a string original
        val actualBrazilianDate = invalidDate.toBrazilianDate()

        // Verifica se a data inválida é retornada como está
        assertEquals(invalidDate, actualBrazilianDate)
    }
}
