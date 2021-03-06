package assignments.week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal 
{
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		
		// Go to Mens Fashion
		Actions builder = new Actions(driver);
		WebElement mensFashion = driver.findElement(By.linkText("Men's Fashion"));
		builder.moveToElement(mensFashion).perform();

		// Go to Sports Shoes
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])")).click();
		Thread.sleep(2000);

		// Get the count of the Sports Shoes
		WebElement shoesCount = driver.findElement(By.xpath("//span[contains(@class,'category-count')]"));
		System.out.println("No of sports shoes: " + shoesCount.getText());

		// Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(2000);
		
		// Sort by Low to High
		driver.findElement(By.xpath("//span[contains(text(),'Sort by')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();

		// Check if the items displayed are sorted correctly
		Thread.sleep(2000);		
		List<WebElement> sortedPrice = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		for(WebElement element:sortedPrice)
		{
			System.out.println(element.getText());
		}

		// Select the price range (900-1200)
		WebElement from = driver.findElement(By.xpath("(//input[@name='fromVal'])[1]"));
		from.clear();
		from.sendKeys("900");
		WebElement to = driver.findElement(By.xpath("(//input[@name='toVal'])[1]"));
		to.clear();
		to.sendKeys("1200",Keys.ENTER);
		Thread.sleep(2000);

		// Filter with color Navy 
		driver.findElement(By.xpath("(//button[text()='View More '])[1]")).click();
		
		WebElement color = driver.findElement(By.xpath("//a[text()=' Navy']"));
		System.out.println("The navy color filter is enabled: " + color.isSelected());
		
		// Verify the all applied filters 
		WebElement priceTag = driver.findElement(By.xpath("(//a[@class='clear-filter-pill'])[1]"));
		String price = priceTag.getText();
		System.out.println("The product price is from: " + price);
		Thread.sleep(2000);

		// Mouse Hover on first resulting Training shoes
		WebElement firstShoe = driver.findElement(By.xpath("//p[@class='product-title']"));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(firstShoe).perform();
		
		// Click QuickView button
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		
		// Print the cost and the discount percentage
		Thread.sleep(2000);
		WebElement cost = driver.findElement(By.xpath("//span[@class='payBlkBig']"));
		String costString = cost.getText();
		System.out.println("The cost of the product is: " + costString);
		
		WebElement p = driver.findElement(By.xpath("//span[@class='percent-desc ']"));
		String discountPercentage = p.getText();
		System.out.println("The discount percentage is: " + discountPercentage);
		
		// Take the snapshot of the shoes.
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./screenshots/img2.png");
		FileUtils.copyFile(source, destination);
		
		// Close the main window
		driver.close();
	}
}
