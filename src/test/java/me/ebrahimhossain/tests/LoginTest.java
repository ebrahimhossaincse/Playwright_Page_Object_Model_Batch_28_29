package me.ebrahimhossain.tests;

import me.ebrahimhossain.basedriver.BaseDriver;
import me.ebrahimhossain.pages.LoginPage;
import org.testng.annotations.*;

public class LoginTest extends BaseDriver {

    @BeforeClass
    @Parameters({"url","browserName","headless"})
    public void openUrl(@Optional("https://testing-and-learning-hub.vercel.app/Projects/Platzi%20Fake%20Store/login.html") String url,
                        @Optional("chrome") String browserName, @Optional("false") String headless) throws InterruptedException {

//        System.out.println("Opening URL: " + url);
//        System.out.println("Browser: " + browserName);
//        System.out.println("Headless: " + headless);
        start(browserName, headless);
        launchApplication(url);
    }

    @Test
    public void loginTest(){
        LoginPage loginPage = new LoginPage(page);
        loginPage.login();
    }

    @AfterClass
    public void tearDown(){
        stop();
    }

}
