package ejemploSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MetodosNavegacionSelenium {
    public static void main(String[] args) throws InterruptedException {
        //Paso 1. Agregar el ChromeDriver
        //Paso 2. Enlazar el WebDriver.

        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver= rutaProyecto+"\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Paso 3. Instanciar un objeto de tipo ChromeDriver
        WebDriver driver = new ChromeDriver();

        //Paso 4. Levant el browser con la pagina de google
        driver.get("https://www.google.com");

        System.out.println("Titulo pagina " +driver.getTitle());

        //Paso 5. Espero 5 segs
        Thread.sleep(5000);
        //Redireccionar

        driver.navigate().to("https://accounts.spotify.com/es/login");

        System.out.println("Titulo pagina Spotify " + driver.getTitle());
        System.out.println("URL actual " + driver.getCurrentUrl());

        System.out.println("----------------------------------------------------------");
        System.out.println(driver.getPageSource());
        System.out.println("----------------------------------------------------------");

        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.navigate().refresh();



        //Paso 6. Cerrar el browser
        driver.quit();
    }
}

