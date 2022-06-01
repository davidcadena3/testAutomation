package com.test.automation.wasi.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide.element

class PaginaTableroUsuario {

    private val modalBienvenida = element(byAttribute("data-dismiss", "modal"))
    private val h2NombreUsuario = element(byId("profile_fullname"))

    fun cerrarModalBienvenida(){
        if(modalBienvenida.isDisplayed){
            modalBienvenida.click()
        }
    }

    fun validarCargaPagina(){
        h2NombreUsuario.shouldBe(Condition.visible)
    }

}