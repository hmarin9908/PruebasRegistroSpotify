package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class CasosDePrueba {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private String rutaDriver = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String propertyDriver = "webdriver.chrome.driver";

    @AfterTest
    public void posCondicion(){
        driver.close();
}

    @BeforeTest
    public void preCondiciones(){

        System.setProperty(propertyDriver,rutaDriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
        js = (JavascriptExecutor) driver;
        driver.navigate().to("https://open.spotify.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void CP001_Buscar_Artista() throws InterruptedException {
    By clickBtnBuscar = By.xpath("//ul/li[2]/a");
    wait.until(ExpectedConditions.presenceOfElementLocated(clickBtnBuscar));
    WebElement btnBuscar = driver.findElement(clickBtnBuscar);
    btnBuscar.click();
    Thread.sleep(3000);

    By buscarCancion = By.xpath("//input[@placeholder=\"¿Qué quieres escuchar?\"]");
    driver.findElement(buscarCancion).sendKeys("The 1975");

    By iconoArtista = By.xpath("//div[contains(text(),\"The 1975\")]");
    wait.until(ExpectedConditions.presenceOfElementLocated(iconoArtista)).click();

    By listaArtista = By.xpath("//h1[text()=\"The 1975\"]");
    Assert.assertEquals("The 1975",
            wait.until(ExpectedConditions.presenceOfElementLocated(listaArtista)).getText());

    }
    @Test
    public void CP002_Registro_Fallido_Contraseña_corta(){

        driver.findElement(By.xpath("//button[contains(text(),\"Regístrate\")]")).click();

        By BtnEmail = By.xpath("//input[@id=\"email\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(BtnEmail)).sendKeys("humberto.marin@tsoftlatam.com");

        By confirmarEmail = By.xpath("//input[@id=\"confirm\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmarEmail)).sendKeys("humberto.marin@tsoftlatam.com");


        By crearContraseña = By.xpath("//input[@placeholder=\"Crea una contraseña.\"]");
            driver.findElement(crearContraseña).sendKeys("Abc123");


        By nombreUsuario = By.xpath("//input[@placeholder=\"Introduce un nombre de perfil.\"]");
        driver.findElement(nombreUsuario).sendKeys("HMarin990820");


        driver.findElement(By.xpath("//input[@placeholder=\"DD\"]")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.xpath("//select[@id=\"month\"]")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.xpath("//input[@placeholder=\"AAAA\"]")).sendKeys("1999");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for=\"gender_option_male\"]"));

        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        optionMale.click();

        driver.findElement(By.xpath("//label[@for=\"marketing-opt-checkbox\"]")).click();

        driver.findElement(By.xpath("//label[@for=\"third-party-checkbox\"]")).click();

        btnRegistro.click();

        Assert.assertEquals("Tu contraseña es muy corta.",
                driver.findElement(By.xpath("//div[contains(text(),\"Tu contraseña es muy corta.\")]")).getText());
    }
    @Test
    public void CP003_Registro_Fallido_Año_inválido() {

        driver.findElement(By.xpath("//button[contains(text(),\"Regístrate\")]")).click();

        By BtnEmail = By.xpath("//input[@id=\"email\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(BtnEmail)).sendKeys("humberto.marin@tsoftlatam.com");

        By confirmarEmail = By.xpath("//input[@id=\"confirm\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmarEmail)).sendKeys("humberto.marin@tsoftlatam.com");


        By crearContraseña = By.xpath("//input[@placeholder=\"Crea una contraseña.\"]");
        driver.findElement(crearContraseña).sendKeys("Abc123456.A");


        By nombreUsuario = By.xpath("//input[@placeholder=\"Introduce un nombre de perfil.\"]");
        driver.findElement(nombreUsuario).sendKeys("HMarin990820");


        driver.findElement(By.xpath("//input[@placeholder=\"DD\"]")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.xpath("//select[@id=\"month\"]")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.xpath("//input[@placeholder=\"AAAA\"]")).sendKeys("199");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for=\"gender_option_male\"]"));

        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        optionMale.click();

        driver.findElement(By.xpath("//label[@for=\"marketing-opt-checkbox\"]")).click();

        driver.findElement(By.xpath("//label[@for=\"third-party-checkbox\"]")).click();

        btnRegistro.click();

        Assert.assertEquals("Indica un año válido.",
                driver.findElement(By.xpath("//div[contains(text(),\"Indica un año válido.\")]")).getText());
    }
    @Test
    public void CP004_Registro_Fallido_Nombre_Perfil_Faltante() {

        driver.findElement(By.xpath("//button[contains(text(),\"Regístrate\")]")).click();

        By BtnEmail = By.xpath("//input[@id=\"email\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(BtnEmail)).sendKeys("humberto.marin@tsoftlatam.com");

        By confirmarEmail = By.xpath("//input[@id=\"confirm\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmarEmail)).sendKeys("humberto.marin@tsoftlatam.com");


        By crearContraseña = By.xpath("//input[@placeholder=\"Crea una contraseña.\"]");
        driver.findElement(crearContraseña).sendKeys("Abc123456.A");


        By nombreUsuario = By.xpath("//input[@placeholder=\"Introduce un nombre de perfil.\"]");
        driver.findElement(nombreUsuario).sendKeys("");


        driver.findElement(By.xpath("//input[@placeholder=\"DD\"]")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.xpath("//select[@id=\"month\"]")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.xpath("//input[@placeholder=\"AAAA\"]")).sendKeys("1999");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for=\"gender_option_male\"]"));

        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        optionMale.click();

        driver.findElement(By.xpath("//label[@for=\"marketing-opt-checkbox\"]")).click();

        driver.findElement(By.xpath("//label[@for=\"third-party-checkbox\"]")).click();

        btnRegistro.click();

        Assert.assertEquals("Introduce un nombre para tu perfil.",
                driver.findElement(By.xpath("//div[contains(text(),\"Introduce un nombre para tu perfil.\")]")).getText());
    }
    @Test
    public void CP005_Registro_Fallido_Dia_del_Mes_Inválido() {

        driver.findElement(By.xpath("//button[contains(text(),\"Regístrate\")]")).click();

        By BtnEmail = By.xpath("//input[@id=\"email\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(BtnEmail)).sendKeys("humberto.marin@tsoftlatam.com");

        By confirmarEmail = By.xpath("//input[@id=\"confirm\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmarEmail)).sendKeys("humberto.marin@tsoftlatam.com");


        By crearContraseña = By.xpath("//input[@placeholder=\"Crea una contraseña.\"]");
        driver.findElement(crearContraseña).sendKeys("Abc123456.A");


        By nombreUsuario = By.xpath("//input[@placeholder=\"Introduce un nombre de perfil.\"]");
        driver.findElement(nombreUsuario).sendKeys("HMarin990820");


        driver.findElement(By.xpath("//input[@placeholder=\"DD\"]")).sendKeys("60");

        Select cmbMes = new Select(driver.findElement(By.xpath("//select[@id=\"month\"]")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.xpath("//input[@placeholder=\"AAAA\"]")).sendKeys("1999");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for=\"gender_option_male\"]"));

        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        optionMale.click();

        driver.findElement(By.xpath("//label[@for=\"marketing-opt-checkbox\"]")).click();

        driver.findElement(By.xpath("//label[@for=\"third-party-checkbox\"]")).click();

        btnRegistro.click();

        Assert.assertEquals("Indica un día del mes válido.",
                driver.findElement(By.xpath("//div[contains(text(),\"Indica un día del mes válido.\")]")).getText());
    }
    @Test
    public void CP006_Registro_Fallido_Sin_Seleccion_Sexo() {

        driver.findElement(By.xpath("//button[contains(text(),\"Regístrate\")]")).click();

        By BtnEmail = By.xpath("//input[@id=\"email\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(BtnEmail)).sendKeys("humberto.marin@tsoftlatam.com");

        By confirmarEmail = By.xpath("//input[@id=\"confirm\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmarEmail)).sendKeys("humberto.marin@tsoftlatam.com");


        By crearContraseña = By.xpath("//input[@placeholder=\"Crea una contraseña.\"]");
        driver.findElement(crearContraseña).sendKeys("Abc123456.A");


        By nombreUsuario = By.xpath("//input[@placeholder=\"Introduce un nombre de perfil.\"]");
        driver.findElement(nombreUsuario).sendKeys("HMarin990820");


        driver.findElement(By.xpath("//input[@placeholder=\"DD\"]")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.xpath("//select[@id=\"month\"]")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.xpath("//input[@placeholder=\"AAAA\"]")).sendKeys("1999");

        //WebElement optionMale = driver.findElement(By.xpath("//label[@for=\"gender_option_male\"]"));

        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        //optionMale.click();

        driver.findElement(By.xpath("//label[@for=\"marketing-opt-checkbox\"]")).click();

        driver.findElement(By.xpath("//label[@for=\"third-party-checkbox\"]")).click();

        btnRegistro.click();

        Assert.assertEquals("Selecciona tu sexo.",
                driver.findElement(By.xpath("//div[contains(text(),\"Selecciona tu sexo.\")]")).getText());
    }
}



