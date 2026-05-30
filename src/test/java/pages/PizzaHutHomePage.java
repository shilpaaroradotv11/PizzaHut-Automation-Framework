package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PizzaHutHomePage {

	WebDriver driver;

	public PizzaHutHomePage(WebDriver driver) {

		this.driver = driver;
	}

	By locationPopup = By.xpath("//input[contains(@placeholder,'location')]");

	By deliveryAddressText = By.xpath("//*[contains(text(),'Ordering for')]");

	public boolean isLocationPopupDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(locationPopup));

		return driver.findElement(locationPopup).isDisplayed();
	}

	public void enterLocation(String location) throws InterruptedException {

		driver.findElement(locationPopup).sendKeys(location);
		WebElement locationField = driver.findElement(locationPopup);

		locationField.sendKeys(location);

		Thread.sleep(3000);

		locationField.sendKeys(Keys.ARROW_DOWN);

		locationField.sendKeys(Keys.ENTER);
	}

	public void selectFirstLocation() {

		// intentionally left blank
	}

	public boolean isDeliveryAddressDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryAddressText));

		return element.isDisplayed();

	}

}
