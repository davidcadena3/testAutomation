package com.test.automation.wasi.utilidades.data

import com.google.gson.Gson
import com.test.automation.commons.utils.RealExcelFile
import com.test.automation.wasi.cases.LoginRequest
import com.test.automation.wasi.cases.Usuario

enum class DataPruebas(val archivo: String) {
    DATA_LOGIN_VALIDA("loginDataValida.xlsx"),
    DATA_LOGIN_INVALIDA("loginDataInvalida.xlsx"),
}

class Data() {
    companion object {
        fun dataLoginInvalida(): List<LoginRequest> {
            val data = mutableListOf<LoginRequest>()
            RealExcelFile(DataPruebas.DATA_LOGIN_INVALIDA.archivo).getDataAsJson()?.let { json ->
                Gson().fromJson(json["data"].toString(), Array<Usuario>::class.java).toMutableList().forEach {
                    data.add(LoginRequest(it))
                }
            }
            assert(data.isNotEmpty())
            return data
        }

        fun dataLoginValida(): List<LoginRequest> {
            val data = mutableListOf<LoginRequest>()
            RealExcelFile(DataPruebas.DATA_LOGIN_VALIDA.archivo).getDataAsJson()?.let { json ->
                Gson().fromJson(json["data"].toString(), Array<Usuario>::class.java).toMutableList().forEach {
                    data.add(LoginRequest(it))
                }
            }
            assert(data.isNotEmpty())
            return data
        }
    }
}