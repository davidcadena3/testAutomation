package com.test.automation.wasi.cases

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.test.automation.commons.base.BaseRequest
import com.test.automation.commons.base.BaseTest
import com.test.automation.commons.utils.RealExcelFile
import com.test.automation.wasi.pages.PaginaPrincipal
import com.test.automation.wasi.pages.PaginaTableroUsuario
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


data class Usuario(val nombre: String, val correo: String, val password: String)
data class LoginRequest(val usuario: Usuario) : BaseRequest()

class LoginTest : BaseTest(PaginaPrincipal.url) {

    private lateinit var paginaPrincipal: PaginaPrincipal
    private lateinit var paginaTableroUsuario: PaginaTableroUsuario
    private val peticiones = mutableListOf<LoginRequest>()
    private val dataTest = "loginData.xlsx"

    @BeforeEach
    fun setUp() {
        paginaPrincipal = PaginaPrincipal()
        paginaTableroUsuario = PaginaTableroUsuario()
        RealExcelFile(dataTest).getDataAsJson()?.let { json ->
            Gson().fromJson(json["data"].toString(), Array<Usuario>::class.java).toMutableList().forEach {
                peticiones.add(LoginRequest(it))
            }
        }
        assert(peticiones.isNotEmpty())
    }

    inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

    @Test
    fun abrirYCerrarSesion() {
        peticiones.forEach {
            if (realizarLogin(it)) {
                realizarLogout(it)
            }
        }
    }

    private fun realizarLogin(request: LoginRequest): Boolean {
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

    private fun realizarLogout(request: LoginRequest) {
        val methodName = object {}.javaClass.enclosingMethod.name
        customScreenshot(request.uuid, methodName)
        paginaTableroUsuario.cerrarSesion(request.usuario.nombre)
    }
}