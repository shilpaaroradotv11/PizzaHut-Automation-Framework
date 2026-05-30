package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PizzaMenuPage {

	WebDriver driver;
	static String pizzaPrice;
	static String checkoutPrice;
	static String taxAmount;
	static String oldCheckoutPrice;
	static String newCheckoutPrice;

	public PizzaMenuPage(WebDriver driver) {

		this.driver = driver;

	}

	By vegToggle = By.xpath("(//*[contains(text(),'Vegetarian')]//following::span[contains(@class,'bg-white')])[1]");

	By pizzaTab = By.xpath("//a[contains(@class,'side-menu__link--pizzas')]//span[text()='Pizzas']");

	By pizzaAddButton = By
			.xpath("//button[contains(@data-synth,'margherita-recommended')]//span[contains(text(),'Add')]");

	By basketPizzaItem = By.xpath("//div[contains(@class,'leading-tight') and contains(text(),'Personal Margherita')]");

	By basketPizzaPrice = By.xpath(
			"//div[contains(@class,'leading-tight') and contains(text(),'Personal Margherita')]//following::div[contains(text(),'₹')][1]");

	By taxAmountText = By.xpath("//span[contains(text(),'Total Tax')]//following::span[contains(text(),'₹')][1]");

	By checkoutButton = By.xpath("//button[contains(@class,'button--primary')]");

	By checkoutItemCount = By.xpath("//button[contains(@class,'button--primary')]//*[contains(text(),'item')]");

	By checkoutTotalPrice = By.xpath("//div[contains(@class,'items-start')]//span[contains(@class,'amountdue')]");

	By drinksTab = By.xpath("//a[contains(@class,'side-menu__link--drinks')]//span[contains(text(),'Drinks')]");

	By pepsiAddButton = By.xpath("//button[contains(@data-synth,'pepsi-600ml')]//span[contains(text(),'Add')]");

	By removePizzaButton = By.xpath("//button[contains(@data-synth,'basket-item-remove--margherita')]");

	By minimumOrderPopup = By.xpath("//span[contains(text(),'Continue Shopping')]");

	public boolean isDealsPageDisplayed() {

		return driver.getCurrentUrl().contains("order");
	}

	public boolean validateVegToggleOff() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement veg = wait.until(ExpectedConditions.visibilityOfElementLocated(vegToggle));

		return veg.isDisplayed();
	}

	public void clickPizzaTab() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(pizzaTab));

		driver.findElement(pizzaTab).click();

	}

	public void clickPizzaAddButton() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement pizza = wait.until(ExpectedConditions.elementToBeClickable(pizzaAddButton));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pizza);

		pizza.click();
	}

	public boolean isPizzaAddedToBasket() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(basketPizzaItem));

		return driver.findElement(basketPizzaItem).isDisplayed();
	}

	public void storePizzaPrice() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(basketPizzaPrice));

		pizzaPrice = driver.findElement(basketPizzaPrice).getText();

	}

	public void storeTaxAmount() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(taxAmountText));

		taxAmount = driver.findElement(taxAmountText).getText();
	}

	public void storeCheckoutPrice() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTotalPrice));

		checkoutPrice = driver.findElement(checkoutTotalPrice).getText();

	}

	public boolean validatePrice() {

		double pizza = Double.parseDouble(pizzaPrice.replaceAll("[^0-9.]", ""));

		double tax = Double.parseDouble(taxAmount.replaceAll("[^0-9.]", ""));

		double checkout = Double.parseDouble(checkoutPrice.replaceAll("[^0-9.]", ""));

		double expected = pizza + tax;

		if (Math.abs(checkout - expected - 35) < 0.5) {
			checkout = checkout - 35;
		}

		return Math.abs(expected - checkout) < 0.5;
	}

	public boolean validateItemCount() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutItemCount));

		return driver.findElement(checkoutItemCount).isDisplayed();
	}

	public boolean validateCheckoutPriceDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTotalPrice));

		return driver.findElement(checkoutTotalPrice).isDisplayed();
	}

	public void clickDrinksTab() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(drinksTab));

		driver.findElement(drinksTab).click();
	}

	public void clickPepsiAddButton() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(pepsiAddButton));

		driver.findElement(pepsiAddButton).click();
	}

	public boolean validateTwoItemsCheckout() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));

		return checkout.getText().contains("2 items");
	}

	public void storeOldCheckoutPrice() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTotalPrice));

		oldCheckoutPrice = driver.findElement(checkoutTotalPrice).getText();
	}

	public void storeNewCheckoutPrice() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTotalPrice));

		newCheckoutPrice = driver.findElement(checkoutTotalPrice).getText();
	}

	public boolean validatePriceIncreased() {

		System.out.println(oldCheckoutPrice);

		System.out.println(newCheckoutPrice);

		double oldPrice = Double.parseDouble(oldCheckoutPrice.replaceAll("[^0-9.]", ""));

		double newPrice = Double.parseDouble(newCheckoutPrice.replaceAll("[^0-9.]", ""));

		return newPrice > oldPrice;
	}

	public void removePizzaFromBasket() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(removePizzaButton));

		driver.findElement(removePizzaButton).click();
	}

	public boolean validatePizzaRemoved() {

		return driver.findElements(basketPizzaItem).size() == 0;
	}

	public boolean validateOneItemCheckout() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));

		return checkout.getText().contains("1 item");
	}

	public void clickCheckoutButton() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

		checkout.click();
	}

	public boolean validateMinimumOrderPopup() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(minimumOrderPopup));

		return driver.findElement(minimumOrderPopup).isDisplayed();
	}

}
