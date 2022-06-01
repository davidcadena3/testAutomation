package com.test.automation.wasi.cases

import com.test.automation.commons.base.BaseRequest
import com.test.automation.commons.base.BaseTest
import com.test.automation.wasi.pages.PaginaPrincipal
import com.test.automation.wasi.pages.PaginaTableroUsuario
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

data class LoginRequest(val usuario:String, val password: String): BaseRequest()

class LoginTest: BaseTest(PaginaPrincipal.url) {

    private lateinit var paginaPrincipal: PaginaPrincipal
    private lateinit var paginaTableroUsuario: PaginaTableroUsuario

    @BeforeEach
    fun setUp() {
        paginaPrincipal = PaginaPrincipal()
        paginaTableroUsuario = PaginaTableroUsuario()
    }

    @Test
    fun realizarLogin(
       // request: LoginRequest?
    ){
        val request =  LoginRequest("lord@wasitest.com","demo23467")
        val methodName = object {}.javaClass.enclosingMethod.name
        customScreenshot(request.uuid, methodName)
        paginaPrincipal.presionarBotonIngresar()
        customScreenshot(request.uuid, methodName)
        paginaPrincipal.diligenciarUsuario(request.usuario)
        paginaPrincipal.diligenciarPassword(request.password)
        customScreenshot(request.uuid, methodName)
        paginaPrincipal.presionarBotonLogin()
        paginaTableroUsuario.cerrarModalBienvenida()
        paginaTableroUsuario.validarCargaPagina()
    }

    @Test
    fun realizarLogout(request: BaseRequest){
        val methodName = object {}.javaClass.enclosingMethod.name
        customScreenshot(request.uuid, methodName)
        // paginaPrincipal.presionarBotonLogout()
    }
}