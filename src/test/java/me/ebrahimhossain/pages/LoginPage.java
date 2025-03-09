package me.ebrahimhossain.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import me.ebrahimhossain.utilities.CommonMethods;

public class LoginPage extends CommonMethods {

    Page page;

//    private Locator email;
//    private Locator password;
//    private Locator loginButton;

    Locator email;
    Locator password;
    Locator loginButton;

    public LoginPage(Page page) {
        this.page = page;

        this.email = page.locator("#email");
        this.password = page.locator("#password");
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
    public void login(){
        try{
            if(email.isVisible()){
                email.fill("john@mail.com");
                page.waitForTimeout(1000);
                if(password.isVisible()){
                    password.fill("changeme");
                    page.waitForTimeout(1000);
                    if(loginButton.isVisible()){
                        loginButton.click();
                        page.waitForTimeout(3000);
                    }else{
                        System.out.println("Login Button is not locateable, please check the error message");
                    }
                }else{
                    System.out.println("Password Button is not locateable, please check the error message");
                }
            }else{
                System.out.println("Email is not locateable, please check the error message");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

}
