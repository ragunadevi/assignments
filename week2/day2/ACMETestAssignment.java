package assignments.week2.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ACMETestAssignment {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://acme-test.uipath.com/login");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='controls']/input[@id='email']")).sendKeys("kumar.testleaf@gmail.com");
		driver.findElement(By.xpath("//div[@class='control-group form-group']//input[@id='password']")).sendKeys("leaf@12");
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Thread.sleep(1500);
		String strTitle = driver.getTitle();
		System.out.println("Title is "+strTitle);
		driver.findElement(By.xpath("//form[@id='logout-form']/following-sibling::li/a")).click();
		driver.close();	
	}
}
