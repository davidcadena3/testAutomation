package com.test.automation.commons.base

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.junit5.ScreenShooterExtension
import com.codeborne.selenide.logevents.SelenideLogger
import io.github.bonigarcia.wdm.WebDriverManager
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

@ExtendWith(ScreenShooterExtension::class)
open class BaseTest(val url: String) {

    companion object {
        @JvmField
        @RegisterExtension
        val screenshotEmAll: ScreenShooterExtension = ScreenShooterExtension(false).to("target/screenshots");

        private val screenshotsMap = mutableMapOf<String,Int>()

        fun customScreenshot(uuid: String, case: String ){
            if(!screenshotsMap.containsKey(case)){
                screenshotsMap[case] = 1
            }else{
                screenshotsMap[case] = screenshotsMap.getValue(case).plus(1)
            }
            Selenide.screenshot("$uuid/$case/screenshot-${screenshotsMap[case]}")
        }
    }

    protected lateinit var driver: WebDriver


    init {
        WebDriverManager.chromedriver().setup()
        SelenideLogger.addListener("allure", AllureSelenide())
        Configuration.reportsFolder = "test-result/reports";
    }

    @BeforeEach
    fun globalSetUp() {
        driver = ChromeDriver()
        Selenide.open(url)
    }

    @AfterEach
    fun closure(){
        driver?.quit()
    }

}