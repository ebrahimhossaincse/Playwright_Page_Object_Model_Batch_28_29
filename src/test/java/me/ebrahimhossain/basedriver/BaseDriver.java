package me.ebrahimhossain.basedriver;

import com.microsoft.playwright.*;


public class BaseDriver {
    public static Playwright playwright;
    public static BrowserType browserType;
    public static Browser browser;
    public static BrowserContext browserContext;
    public static Page page;

    public void start(String browserName, String headless) {
        playwright = Playwright.create();

        if(browserName.equalsIgnoreCase("chrome")){
            browserType = playwright.chromium();
        }else if(browserName.equalsIgnoreCase("firefox")){
            browserType = playwright.firefox();
        }else if(browserName.equalsIgnoreCase("ie")){
            browserType = playwright.webkit();
        }else if(browserName.equalsIgnoreCase("safari")){
            browserType = playwright.webkit();
        }else{
            System.out.println("Invalid browser name");
        }

        if(headless.equalsIgnoreCase("true")){
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));
        }else{
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        }

        browserContext = browser.newContext();
        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    public void launchApplication(String url) throws InterruptedException {
        page.navigate(url);
    }

    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}
