import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;

public class AppepeTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        System.out.println("Configurando el WebDriver...");
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        System.setProperty("webdriver.chrome.driver", Paths.get("src/main/resources/chromedriver.exe").toAbsolutePath().toString());
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testEnviarCorreoConAdjunto() throws InterruptedException {
        System.out.println("Abriendo ProtonMail...");
        driver.get("https://mail.proton.me/login");

        System.out.println("Ingresando el nombre de usuario...");
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]")));
        usernameField.sendKeys("example@proton.me"); // Reemplaza con tu usuario

        System.out.println("Ingresando la contraseña...");
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordField.sendKeys("CONTRASEÑA"); // Reemplaza con tu contraseña

        System.out.println("Haciendo clic en el botón de inicio de sesión...");
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        System.out.println("Esperando a que se muestre el botón 'Nuevo mensaje'...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='sidebar:compose']")));

        System.out.println("Ha cargado la bandeja...");
        System.out.println("Haciendo clic en 'Redactar'...");
        WebElement redactarBtn = driver.findElement(By.xpath("//button[@data-testid='sidebar:compose' and text()='Nuevo mensaje']"));
        redactarBtn.click();

        System.out.println("Esperando al campo 'Para'...");
        WebElement campoPara = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-testid='composer:to']")));
        campoPara.sendKeys("example@proton.me");

        WebElement campoAsunto = driver.findElement(By.xpath("//input[@data-testid='composer:subject']"));
        campoAsunto.sendKeys("Asubto");

     // Tabular al editor
        campoAsunto.sendKeys(Keys.TAB);
        Thread.sleep(1000); // Pequeño retraso para permitir que el foco entre al iframe


     // Esperar a que el iframe esté presente y cambiar a él
        WebElement iframeEditor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("iframe[data-testid='rooster-iframe']")));
        driver.switchTo().frame(iframeEditor);

        // Esperar el <body> dentro del iframe y escribir por teclado
        WebElement cuerpoMensaje = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
     // Hacer foco con click
        cuerpoMensaje.click();

        // Crear Actions
        Actions actions = new Actions(driver);

        // Presionar Ctrl + A para seleccionar todo
        actions.keyDown(Keys.CONTROL)
               .sendKeys("a")
               .keyUp(Keys.CONTROL)
               .perform();

        // Ahora escribir el texto nuevo (reemplaza el anterior)
        actions.sendKeys("Presentacion -----, automatizado con Selenium :)\n-------------------------------------------\n Tu nombre.")
               .perform();


        // Volver al contexto principal
        driver.switchTo().defaultContent();

        // Esperar a que el campo para adjuntar archivo esté disponible
         WebElement inputArchivo = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@type='file' and @data-testid='composer-attachments-button']")));

     // Enviar archivo
        inputArchivo.sendKeys("C:\\Ruta\\Al\\Archivo\\file.txt");

        // Esperar a que aparezca el texto "1" en el número de archivos adjuntos
         wait.until(ExpectedConditions.textToBePresentInElementLocated(
            By.cssSelector("[data-testid='attachment-list:pure-attachment-number']"),
            "1"
        ));


        // Esperar a que el botón de enviar esté visible y hacer clic
        WebElement botonEnviar = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@data-testid='composer:send-button']")));
         botonEnviar.click();

        // Esperar a la notificación de que el mensaje fue enviado
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(text(),'Mensaje enviado') or contains(text(),'Message sent')]")));

        System.out.println("Mensaje enviado correctamente.");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Cerrando el navegador...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Navegador cerrado.");
    }
}
