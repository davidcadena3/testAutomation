package com.test.automation.wasi.cases

import com.test.automation.commons.base.BaseRequest
import com.test.automation.commons.base.BaseTest
import com.test.automation.wasi.pages.PaginaTableroUsuario

data class CambioPasswordRequest(val usuario: String, val password: String, val nuevoPassword: String) : BaseRequest()

class ConfiguracionUsuarioTests : BaseTest() {

    private val paginaTableroUsuario = PaginaTableroUsuario()

    fun cambiarPassword(cambioPasswordRequest: CambioPasswordRequest) {
        paginaTableroUsuario.cambiarPasswordUsuario(
            cambioPasswordRequest.uuid,
            cambioPasswordRequest.usuario,
            cambioPasswordRequest.password,
            cambioPasswordRequest.nuevoPassword,
            cambioPasswordRequest.nuevoPassword
        )
        // TODO falta algun assert para validar que todo funcionó
    }


}