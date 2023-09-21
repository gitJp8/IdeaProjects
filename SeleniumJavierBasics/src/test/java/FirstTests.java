import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class FirstTests {
    public static void main(String[] args){
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "/Users/javier/IdeaProjects/chromedriver/chromedriver.app");
        driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();
        //Paso 2: Comenzar a automatizar flujo
        //Abrir browser
        /*
        *Abrir landing page
        * Hcaer click en contact us
        * Llenar formulario
        * Enviar nota
        * Validar mensaje enviado
         */
        //Abrir navegador
        driver.get("http://automationpractice.multiformis.com/index.php");
        //Encontramos el locator
        By lnkContactUs = By.xpath("//a[text()='Contact us']");
        //Interactuamos con el WebElement
        WebElement contactUsButton = driver.findElement(lnkContactUs);
        contactUsButton.click();

        //Validar que llegamos a la pagina correcta
        String contactUsTitle = driver.getTitle();
        softAssert.assertTrue(contactUsTitle.equals("Contact us - My Store"), "The ContactUs Title text does not match the page");

        //Interactuar con elementos pagina Contact
        By cboSubjectHeading = By.id("//*[@id='id_contact']");
        WebElement cboSubjectHeadingDropdown = driver.findElement(cboSubjectHeading);
        Select select = new Select(cboSubjectHeadingDropdown);
        select.selectByValue("Webmaster");

        //
        By txtEmail = By.id("email");
        driver.findElement(txtEmail).sendKeys("javier@yahoo.com");

        By txtOrder = By.id("order");
        driver.findElement(txtOrder).sendKeys("ORDER-12345");

        By flUp = By.name("fileUpload");
        String filePath = "/Users/javier/Desktop/foto1.png";
        driver.findElement(txtEmail).sendKeys(filePath);

        By txaMessage = By.id("message");
        driver.findElement(txaMessage).sendKeys("Text Message 01 - Hello world!");

        By btnSend = By.id("submitMessage");
        driver.findElement(btnSend).click();

        By lblSuccessMessage = By.xpath("//p[contains(text(), 'Your message has been')]");
        String successMessage = driver.findElement(lblSuccessMessage).getText();
        softAssert.assertTrue(successMessage.contains("Your message has been successfully sent to our team"), "The success message could not be sent");

    }
}