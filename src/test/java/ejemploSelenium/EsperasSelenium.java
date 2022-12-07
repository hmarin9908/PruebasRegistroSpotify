package ejemploSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EsperasSelenium {
    public static void main(String[] args) throws InterruptedException {
        //Paso 1. Agregar el ChromeDriver
        //Paso 2. Enlazar el WebDriver.

        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver= rutaProyecto+"\\src\\test\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Paso 3. Instanciar un objeto de tipo ChromeDriver
        WebDriver driver = new ChromeDriver();

        //Se establece un tiempo de espera maximo de 30 segs para que la pagina cargue
        //si se demora mas en cargar, nuestro script se caerá
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Establecer 40 segundos de espera por los componenetes asincronicos de una pagina
        driver.manage().timeouts().setScriptTimeout(40,TimeUnit.SECONDS);

        //Establecer 20 segundos de espera para evitar lanzar una excepcion por no encontrar un elemento web
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,10);



        //Paso 4. Levant el browser con la pagina de google
        driver.get("https://www.google.com");

        System.out.println("Titulo pagina " +driver.getTitle());

        //Paso 5. Espero 5 segs
        Thread.sleep(5000);
        //Redireccionar

        driver.navigate().to("https://open.spotify.com");

        //maximizar el navegador
        driver.manage().window().maximize();

        //Web Element
        /*By locatorBtnCerrarPoliticaPrivacidad = By.xpath("//button[@aria-label=\"Cerrar\"]");

        WebElement btnCerrarPoliticaPrivacidad = driver.findElement(locatorBtnCerrarPoliticaPrivacidad);

        Thread.sleep(3000);

        if(btnCerrarPoliticaPrivacidad.isDisplayed()){
            btnCerrarPoliticaPrivacidad.click();
        }*/


        //Crear localizador
        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),\"Regístrate\")]");

        //Crear elemento web
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        //Accion
        btnRegistrarse.click();

        //Thread.sleep(5000);

        //Agregar texto en campo email
        //driver.findElement(By.id("email")).sendKeys("humberto.marin@tsoftlatam.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("humberto.marin@tsoftlatam.com");


        //Thread.sleep(2000);

        driver.findElement(By.id("confirm")).sendKeys("humberto.marin@tsoftlatam.com");

        //Thread.sleep(2000);

        driver.findElement(By.name("password")).sendKeys("Abc123.999999asdA");

        //Thread.sleep(2000);

        driver.findElement(By.id("displayname")).sendKeys("HumbertoMarin9908201t8");

        //Thread.sleep(2000);

        driver.findElement(By.id("day")).sendKeys("20");

        //Thread.sleep(2000);

        //Instanciar ComboBox

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByVisibleText("Agosto");

        //Thread.sleep(2000);

        driver.findElement(By.id("year")).sendKeys("1999");

        //Thread.sleep(2000);

        driver.findElement(By.xpath("//label[@for=\"gender_option_male\"]")).click();
        //Thread.sleep(2000);

        driver.findElement(By.xpath("//label[@for=\"marketing-opt-checkbox\"]")).click();
        //Thread.sleep(2000);

        driver.findElement(By.xpath("//label[@for=\"third-party-checkbox\"]")).click();
        //Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@type=\"submit\"]")).submit();
        //Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor)  driver;
        //Find element by link text and store in variable "element"
        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type=\"submit\"]"));

        js.executeScript("arguments[0].scollIntoView();", btnRegistro);

        btnRegistro.click();

        String resultadoEsperado = "Confirma que no eres un robot.";
        String resultadoActual = driver.findElement(By.xpath("//div[contains(text(),\"Confirma que no eres un robot.\")]")).getText();

        if(resultadoActual.equals(resultadoEsperado)){
            System.out.println("Caso OK se verifica ausencia de CAPTCHA");
        }else {
            System.out.println("Buuuuu");
        }



        //Paso 6. Cerrar el browser
        driver.quit();
    }
}

