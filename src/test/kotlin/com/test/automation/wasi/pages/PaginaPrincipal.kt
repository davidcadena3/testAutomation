package com.test.automation.wasi.pages

import com.codeborne.selenide.Selenide.*
import org.openqa.selenium.By

class PaginaPrincipal {

    companion object {
        const val url = "https://www.wasi.co/"
    }

    private val linkIngresar = element(By.linkText("INGRESAR"))
    private val inputUsuario = element(By.id("user"))
    private val inputPassword = element(By.id("password"))
    private val botonLogin = element(By.id("login"))

    fun presionarBotonIngresar() {
        linkIngresar.click()
    }

    fun diligenciarUsuario(usuario: String) {
        inputUsuario.click()
        inputUsuario.clear()
        inputUsuario.sendKeys(usuario)
    }

    fun diligenciarPassword(password: String) {
        inputPassword.click()
        inputPassword.clear()
        inputPassword.sendKeys(password)
    }

    fun presionarBotonLogin() {
        botonLogin.click()
    }

}