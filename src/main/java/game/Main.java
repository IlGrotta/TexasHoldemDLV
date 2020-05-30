package game;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        FirefoxDriver f=new FirefoxDriver();
        f.get("https://www.google.com");
        f.manage().window().maximize();
        String appTitle=f.getTitle();
        String expTitle="Google";
        if (appTitle.equals (expTitle)){
            System.out.println("Verification Successfull");
        }
        else{
            System.out.println("Verification Failed");

        }
        
        f.close();
        System.exit(0);





    }
}
