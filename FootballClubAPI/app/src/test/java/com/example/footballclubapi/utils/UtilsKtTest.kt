package com.example.footballclubapi.utils

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilsKtTest {

    @Test
    fun toSimpleString() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = dateFormat.parse("28/02/2018")
        assertEquals("Wed, 28 Feb 2018", toSimpleString(date))
    }
}