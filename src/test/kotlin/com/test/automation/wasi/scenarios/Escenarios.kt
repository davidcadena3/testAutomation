package com.test.automation.wasi.scenarios

import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.ex.ElementNotFound
import com.codeborne.selenide.ex.ElementShould
import com.test.automation.commons.base.BaseTest
import com.test.automation.wasi.cases.CambioPasswordRequest
import com.test.automation.wasi.cases.ConfiguracionUsuarioTests
import com.test.automation.wasi.cases.LoginLogoutTests
import com.test.automation.wasi.pages.PaginaPrincipal
import com.test.automation.wasi.utilidades.data.Data
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class Escenarios : BaseTest() {

    private var loginLogoutTest = LoginLogoutTests()
    private var configuracionUsuarioTests = ConfiguracionUsuarioTests()

    @Test
    fun abrirSesionFallidamente() {

        val mensajeEsperado = "Element not found"

        Data.dataLoginInvalida().forEach {
            open(PaginaPrincipal.url)
            val exception = Assertions.assertThrows(ElementNotFound::class.java) {
                loginLogoutTest.realizarLogin(it)
            }

            val mensajeError = checkNotNull(exception.message) { "Se espera que retorne un mensaje de error" }
            Assertions.assertTrue(mensajeError.contains(mensajeEsperado))
        }

    }

    @Test
    fun abrirYCerrarSesionExitosamente() {
        Data.dataLoginValida().forEach {
            open(PaginaPrincipal.url)
            if (loginLogoutTest.realizarLogin(it)) {
                loginLogoutTest.realizarLogout(it)
            }
        }
    }

    @Test
    fun cambiarPasswordExitosamente() {
        val loginRequest = Data.dataLoginValida().first()
        open(PaginaPrincipal.url)
        loginLogoutTest.realizarLogin(loginRequest)

        val nuevoPassword = UUID.randomUUID().toString()
        logger.info("nuevoPassword -> $nuevoPassword")
        configuracionUsuarioTests.cambiarPassword(
            CambioPasswordRequest(
                loginRequest.usuario.nombre, loginRequest.usuario.password, nuevoPassword
            )
        )
        configuracionUsuarioTests.cambiarPassword(
            CambioPasswordRequest(
                loginRequest.usuario.nombre,
                nuevoPassword,
                loginRequest.usuario.password,
            )
        )

        loginLogoutTest.realizarLogout(loginRequest)
    }

    @Test
    fun cambiarPasswordFallidamente() {
        val loginRequest = Data.dataLoginValida().first()
        open(PaginaPrincipal.url)
        loginLogoutTest.realizarLogin(loginRequest)

        val nuevoPassword = UUID.randomUUID().toString()
        logger.info("nuevoPassword -> $nuevoPassword")

        val mensajeEsperado = "Element should be hidden"

        val exception = Assertions.assertThrows(ElementShould::class.java) {
            configuracionUsuarioTests.cambiarPassword(
                CambioPasswordRequest(
                    loginRequest.usuario.nombre, nuevoPassword, nuevoPassword
                )
            )
        }

        val mensajeError = checkNotNull(exception.message) { "Se espera que retorne un mensaje de error" }
        Assertions.assertTrue(mensajeError.contains(mensajeEsperado))
        loginLogoutTest.realizarLogout(loginRequest)
    }

}