package com.test.automation.wasi.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide.element
import org.openqa.selenium.By

class PaginaTableroUsuario {

    // private val modalBienvenida = element(byAttribute("data-dismiss", "modal"))
    private val modalBienvenida = element(byXpath("(.//*[normalize-space(text()) and normalize-space(.)='(Opcional)'])[1]/following::span[1]"))
    private val h2NombreUsuario = element(byId("profile_fullname"))
    // private val menuUsuario = element(By.linkText("Demo"))
    private val botonLogout = element(By.linkText("Salir"))

    fun cerrarModalBienvenida(){
        modalBienvenida.click()
    }

    fun validarCargaPagina(){
        h2NombreUsuario.shouldBe(Condition.visible)
    }

    fun cerrarSesion(usuario: String){
        element(By.linkText(usuario)).click()
        botonLogout.click()
    }

}