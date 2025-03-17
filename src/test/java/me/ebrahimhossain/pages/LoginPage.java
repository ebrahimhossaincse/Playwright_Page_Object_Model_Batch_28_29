package me.ebrahimhossain.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import me.ebrahimhossain.utilities.CommonMethods;
import me.ebrahimhossain.utilities.ExcelUtils;

import java.io.IOException;
import java.nio.file.Paths;

public class LoginPage extends CommonMethods {

    Page page;
    ExtentTest test;
//    private Locator email;
//    private Locator password;
//    private Locator loginButton;

    Locator email;
    Locator password;
    Locator loginButton;

    public LoginPage(Page page, ExtentTest test) {
        this.page = page;
        this.test = test;
        this.email = page.locator("#email");
        this.password = page.locator("#password2123123112");
        this.loginButton = page.locator("//button[@type='submit']");
    }
/*
    public Locator getEmail() {
        return email;
    }

    public void setEmail(Locator email) {
        this.email = email;
    }

    public Locator getPassword() {
        return password;
    }

    public void setPassword(Locator password) {
        this.password = password;
    }

    public Locator getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Locator loginButton) {
        this.loginButton = loginButton;
    }

*/

    // Method to log a success message with ExtentReports
    public void handlePass(String message) {
        // Log a message with green color indicating a pass
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
    }

    // Method to log a success message and capture a screenshot
    public void handlePassWithScreenshot(String message, String screenshotName) {
        // Log a message with green color and include bold formatting
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
        // Capture a full-page screenshot and save it to the specified path
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./screenshots/" + screenshotName + ".png"))
                .setFullPage(true));


        // Build the full path for the screenshot file
        String dest = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        // Attach the screenshot to the test report
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    // Method to log a failure message, capture a screenshot, and log an exception
    public void handleFail(String message, String screenshotName) {
        // Log a failure message with red color indicating an error
        test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
        // Create an exception and log it in the report
        Throwable t = new InterruptedException("Exception");
        test.fail(t);
        // Capture a full-page screenshot in case of failure and save it to the
        // specified path
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./screenshots/" + screenshotName + ".png"))
                .setFullPage(true));
        // Build the full path for the screenshot file
        String dest = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        // Attach the screenshot to the failure report
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }


    public void login() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.ReadExcel();
        try{
            test.info("Please enter your username");
            if(email.isVisible()){
                email.fill(excelUtils.username);
                handlePass("You have successfully entered your username");
                page.waitForTimeout(1000);
                test.info("Please enter your password");
                if(password.isVisible()){
                    password.fill(excelUtils.password);
                    handlePass("You have successfully entered your password");
                    page.waitForTimeout(1000);
                    test.info("Please click Login button");
                    if(loginButton.isVisible()){
                        loginButton.click();
                        page.waitForTimeout(3000);
                        handlePassWithScreenshot("You have successfully logged in", "login_pass");
                    }else{
                        handleFail("Login Button is not locateable, please check the error message", "login_fail");

                    }
                }else{
                    handleFail("Password Button is not locateable, please check the error message", "password_fail");
                }
            }else{
                handleFail("Email box is not visible, please check the error message", "email_fail");
            }
        } catch (Exception e) {
            handleFail("Something went wrong, please check the error message", "login_page_error");
        }
    }

}
