package com.test.automation.wasi.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide.element
import com.test.automation.commons.base.BaseTest.Companion.customScreenshot

class PaginaTableroUsuario {

    private val modalBienvenida =
        element(byXpath("(.//*[normalize-space(text()) and normalize-space(.)='(Opcional)'])[1]/following::span[1]"))
    private val h2NombreUsuario = element(byId("profile_fullname"))
    private val botonLogout = element(byLinkText("Salir"))

    fun cerrarModalBienvenida() {
        modalBienvenida.click()
    }

    fun validarCargaPagina() {
        h2NombreUsuario.shouldBe(Condition.visible)
    }

    fun cerrarSesion(usuario: String) {
        clickMenuUsuario(usuario)
        botonLogout.click()
    }

    fun cambiarPasswordUsuario(uuid:String, usuario: String, passActual: String, passNuevo: String, confirmPassNuevo: String) {
        val methodName = object {}.javaClass.enclosingMethod.name
        customScreenshot(uuid, methodName)
        clickMenuUsuario(usuario)
        customScreenshot(uuid, methodName)
        val opcionCambiarPassword = element(byLinkText("Cambiar contraseña"))
        val inputPasswordActual = element(byId("modal_current_password"))
        val inputPasswordNuevo = element(byId("modal_password"))
        val inputConfirmPasswordNuevo = element(byId("modal_confirm_password"))
        val botonConfirmacion = element(byId("btn_change_password"))

        opcionCambiarPassword.click()
        inputPasswordActual.click()
        inputPasswordActual.sendKeys(passActual)
        inputPasswordNuevo.click()
        inputPasswordNuevo.sendKeys(passNuevo)
        inputConfirmPasswordNuevo.click()
        inputConfirmPasswordNuevo.sendKeys(confirmPassNuevo)
        customScreenshot(uuid, methodName)
        botonConfirmacion.click()
        botonConfirmacion.shouldBe(Condition.disappear)
    }

    fun clickMenuUsuario(usuario: String) {
        element(byLinkText(usuario)).click()
    }

}