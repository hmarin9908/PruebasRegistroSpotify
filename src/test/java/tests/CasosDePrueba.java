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
    public void CP001_Registro_Fallido_Captcha_en_blanco() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),\"Regístrate\")]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.id("confirm")).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.name("password")).sendKeys("Abc123.999999asdA");

        driver.findElement(By.id("displayname")).sendKeys("HumbertoMarin9908201t8");

        driver.findElement(By.id("day")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.id("year")).sendKeys("1999");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for=\"gender_option_male\"]"));

        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        optionMale.click();

        driver.findElement(By.xpath("//label[@for=\"marketing-opt-checkbox\"]")).click();

        driver.findElement(By.xpath("//label[@for=\"third-party-checkbox\"]")).click();

        btnRegistro.click();

        Assert.assertEquals("Confirma que no eres un robot.",
                driver.findElement(By.xpath("//div[contains(text(),\"Confirma que no eres un robot.\")]")).getText());

    }
    @Test
    public void CP002_Registro_Fallido_Contraseña_corta() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),\"Regístrate\")]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.id("confirm")).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.name("password")).sendKeys("Abc123");

        driver.findElement(By.id("displayname")).sendKeys("HumbertoMarin9908201t8");

        driver.findElement(By.id("day")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.id("year")).sendKeys("1999");

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

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),\"Regístrate\")]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.id("confirm")).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.name("password")).sendKeys("Abc123a123.789");

        driver.findElement(By.id("displayname")).sendKeys("HumbertoMarin9908201t8");

        driver.findElement(By.id("day")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.id("year")).sendKeys("199");

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

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),\"Regístrate\")]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.id("confirm")).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.name("password")).sendKeys("Abc123123456A.");

        driver.findElement(By.id("displayname")).sendKeys("");

        driver.findElement(By.id("day")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.id("year")).sendKeys("199");

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

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),\"Regístrate\")]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.id("confirm")).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.name("password")).sendKeys("Abc123123456A.");

        driver.findElement(By.id("displayname")).sendKeys("HumbertoMarin9908201t8");

        driver.findElement(By.id("day")).sendKeys("");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.id("year")).sendKeys("1999");

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
    public void CP006_Registro_Fallido_Mes_Inválido() {

        By localizadorBtnRegistrarse = By.xpath("//button[contains(text(),\"Regístrate\")]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.id("confirm")).sendKeys("humberto.marin@tsoftlatam.com");

        driver.findElement(By.name("password")).sendKeys("Abc123123456A.");

        driver.findElement(By.id("displayname")).sendKeys("HumbertoMarin9908201t8");

        driver.findElement(By.id("day")).sendKeys("20");

        Select cmbMes = new Select(driver.findElement(By.id("month")));
        cmbMes.selectByVisibleText("Agosto");

        driver.findElement(By.id("year")).sendKeys("1999");

        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        driver.findElement(By.xpath("//label[@for=\"marketing-opt-checkbox\"]")).click();

        driver.findElement(By.xpath("//label[@for=\"third-party-checkbox\"]")).click();

        btnRegistro.click();

        Assert.assertEquals("Selecciona tu sexo.",
                driver.findElement(By.xpath("//div[contains(text(),\"Selecciona tu sexo.\")]")).getText());
    }
}



