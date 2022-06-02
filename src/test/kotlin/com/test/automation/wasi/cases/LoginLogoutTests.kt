package com.test.automation.wasi.cases

import com.test.automation.commons.base.BaseRequest
import com.test.automation.commons.base.BaseTest.Companion.customScreenshot
import com.test.automation.wasi.pages.PaginaPrincipal
import com.test.automation.wasi.pages.PaginaTableroUsuario


data class Usuario(val nombre: String, val correo: String, val password: String)
data class LoginRequest(val usuario: Usuario) : BaseRequest()

class LoginLogoutTests {

    private val paginaPrincipal = PaginaPrincipal()
    private val paginaTableroUsuario = PaginaTableroUsuario()

    fun realizarLogin(request: LoginRequest): Boolean {
        val methodName = object {}.javaClass.enclosingMethod.name
        customScreenshot(request.uuid, methodName)
        paginaPrincipal.presionarBotonIngresar()
        customScreenshot(request.uuid, methodName)
        paginaPrincipal.diligenciarUsuario(request.usuario.correo)
        paginaPrincipal.diligenciarPassword(request.usuario.password)
        customScreenshot(request.uuid, methodName)
        paginaPrincipal.presionarBotonLogin()
        return try {
            paginaTableroUsuario.cerrarModalBienvenida()
            customScreenshot(request.uuid, methodName)
            paginaTableroUsuario.validarCargaPagina()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun realizarLogout(request: LoginRequest) {
        val methodName = object {}.javaClass.enclosingMethod.name
        customScreenshot(request.uuid, methodName)
        paginaTableroUsuario.cerrarSesion(request.usuario.nombre)
    }
}