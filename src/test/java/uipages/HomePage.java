package uipages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
// Page Object Model for the Home Page
// This class contains the methods to interact with the Home Page of the website: https://www.jaguarlandrover.com/
// The methods in this class are used in the test cases to perform actions on the Home Page

public class HomePage  {
    private WebDriver driver;
    private By logo = By.xpath("//img[@alt='Logo']");
    private By brandNames = By.xpath("//div[@class='nav-2024__item'][normalize-space()='Brands']");
    private By menu = By.xpath("//button[@class='menu-button']");
    private By menuItems = By.xpath("//div[@class='menu-item']");
    private By searchIcon = By.xpath("//button[@class='search-button']");
    private By searchBox = By.xpath("//input[@class='search-input']");
    private By searchResults = By.xpath("//div[@class='search-results']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public WebElement getLogo() {
        return driver.findElement(logo);
    }

    public List<String> getBrandNames() {
        return driver.findElements(brandNames).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void openMenu() {
        driver.findElement(menu).click();
    }

    public List<String> getMenuItems() {
        return driver.findElements(menuItems).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void search(String query) {
        driver.findElement(searchIcon).click();
        driver.findElement(searchBox).sendKeys(query);
    }

    public List<String> getSearchResults() {
        return driver.findElements(searchResults).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void launchPage(Object o) {
    }
}