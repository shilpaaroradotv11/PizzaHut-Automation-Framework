package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PizzaHutHomePage;
import pages.PizzaMenuPage;
import utilities.DriverSetup;
import utilities.ExtentTestManager;

public class LaunchSteps {

	@Given("User launch Pizzahut application with {string}")
	public void user_launch_pizzahut_application_with(String url) {
		DriverSetup.launchBrowser(url);
		ExtentTestManager.test.pass("User launched Pizza Hut application");
	}

	@When("User wait for auto location black pop up screen")
	public void user_wait_for_auto_location_black_pop_up_screen() {

		ExtentTestManager.test.pass("Auto location popup step executed");
	}

	@Then("User close the pop up screen")
	public void user_close_the_pop_up_screen() {

		ExtentTestManager.test.pass("Popup closed successfully");
	}

	@And("User see pop up for delivery asking for enter location")
	public void user_see_pop_up_for_delivery_asking_for_enter_location() {

		PizzaHutHomePage homePage = new PizzaHutHomePage(DriverSetup.driver);

		Assert.assertTrue(homePage.isLocationPopupDisplayed());

		ExtentTestManager.test.pass("Location popup displayed successfully");
	}

	@When("User type address as {string}")
	public void user_enters_delivery_location_as(String location) throws InterruptedException {

		PizzaHutHomePage homePage = new PizzaHutHomePage(DriverSetup.driver);

		homePage.enterLocation(location);

		ExtentTestManager.test.pass("Location entered: " + location);
	}

	@When("User select first auto populate drop down option")
	public void user_selects_first_location_suggestion() {

		PizzaHutHomePage homePage = new PizzaHutHomePage(DriverSetup.driver);

		homePage.selectFirstLocation();

		ExtentTestManager.test.pass("First location suggestion selected");
	}

	@Then("User should see selected delivery location")
	public void user_should_see_pizza_hut_home_page() {

		PizzaHutHomePage homePage = new PizzaHutHomePage(DriverSetup.driver);

		Assert.assertTrue(homePage.isDeliveryAddressDisplayed());

		ExtentTestManager.test.pass("Delivery location displayed successfully");
	}

	@When("User navigate to deals page")
	public void user_navigate_to_deails_page() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.isDealsPageDisplayed());

		ExtentTestManager.test.pass("User navigated to deals page");

	}

	@Then("User validate vegetarian radio button flag is off")
	public void user_validate_vegetarian_radio_button_flag_is_off() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.validateVegToggleOff());

		ExtentTestManager.test.pass("Vegetarian toggle is OFF");
	}

	@When("User clicks on Pizzas menu bar option")
	public void user_clicks_on_pizzas_tab() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		menuPage.clickPizzaTab();

		ExtentTestManager.test.pass("Pizza tab clicked");
	}

	@When("User select add button of any pizza from Recommended")
	public void user_select_add_button_of_any_pizza_from_recommended() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		menuPage.clickPizzaAddButton();

		ExtentTestManager.test.pass("Pizza added to basket");

	}

	@Then("User see that the pizza is getting added under Your Basket")
	public void user_see_that_the_pizza_is_getting_added_under_your_basket() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.isPizzaAddedToBasket());

		ExtentTestManager.test.pass("Pizza visible in basket");

	}

	@And("User validate pizza price plus Tax is checkout price")
	public void user_validate_pizza_price_plus_tax_is_checkout_price() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		menuPage.storePizzaPrice();
		menuPage.storeTaxAmount();

		menuPage.storeCheckoutPrice();

		Assert.assertTrue(menuPage.validatePrice());

		ExtentTestManager.test.pass("Pizza price + tax validated");

	}

	@Then("User validate checkout button contains Item count")
	public void user_validate_checkout_button_contains_item_count() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.validateItemCount());

		ExtentTestManager.test.pass("Checkout item count validated");
	}

	@And("User validate checkout button contains total price count")
	public void user_validate_checkout_button_contains_total_price_count() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.validateCheckoutPriceDisplayed());

		ExtentTestManager.test.pass("Checkout price displayed");
	}

	@Then("User clicks on Drinks option")
	public void user_clicks_on_drinks_option() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		menuPage.clickDrinksTab();

		ExtentTestManager.test.pass("Drinks tab clicked");
	}

	@And("User select Pepsi option to add into the Basket")
	public void user_select_pepsi_option_to_add_into_the_basket() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		menuPage.storeOldCheckoutPrice();

		menuPage.clickPepsiAddButton();

		ExtentTestManager.test.pass("Pepsi added to basket");
	}

	@Then("User see 2 items are showing under checkout button")
	public void user_see_2_items_are_showing_under_checkout_button() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.validateTwoItemsCheckout());

		ExtentTestManager.test.pass("Two items displayed in checkout");
	}

	@And("User see total price is now more than before")
	public void user_see_total_price_is_now_more_than_before() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		menuPage.storeNewCheckoutPrice();

		Assert.assertTrue(menuPage.validatePriceIncreased());

		ExtentTestManager.test.pass("Checkout price increased");
	}

	@Then("User remove the Pizza item from Basket")
	public void user_remove_the_pizza_item_from_basket() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		menuPage.removePizzaFromBasket();

		ExtentTestManager.test.pass("Pizza removed from basket");
	}

	@And("see Price tag got removed from the checkout button")
	public void see_price_tag_got_removed_from_the_checkout_button() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.validatePizzaRemoved());

		ExtentTestManager.test.pass("Pizza price removed from checkout");
	}

	@And("User see 1 item showing in checkout button")
	public void user_see_1_item_showing_in_checkout_button() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.validateOneItemCheckout());

		ExtentTestManager.test.pass("One item displayed in checkout");
	}

	@Then("User Clicks on Checkout button")
	public void user_clicks_on_checkout_button() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		menuPage.clickCheckoutButton();

		ExtentTestManager.test.pass("Checkout button clicked");
	}

	@And("User see minimum order required pop up is getting displayed")
	public void user_see_minimum_order_required_pop_up_is_getting_displayed() {

		PizzaMenuPage menuPage = new PizzaMenuPage(DriverSetup.driver);

		Assert.assertTrue(menuPage.validateMinimumOrderPopup());

		ExtentTestManager.test.pass("Minimum order popup displayed");
	}

}