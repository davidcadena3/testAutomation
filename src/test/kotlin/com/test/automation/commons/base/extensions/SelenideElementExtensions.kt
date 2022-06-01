package com.test.automation.commons.base.extensions

import com.codeborne.selenide.SelenideElement

fun SelenideElement.multiClick(clicks: Int){
    for (i in 0 until clicks) {
        this.click()
    }
}