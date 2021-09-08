package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
		public static WebDriver driver;
		
		//***************Browser Launch******************
		public static WebDriver launchBrowser(String browsername) {
		switch(browsername)	{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			 break;
		default:
			System.err.println("invalid browername");
			
		}
		return driver;
		}
		
		
		//***************GET URL***************************
		public static void launchurl(String url) {
			driver.get(url);
			driver.manage().window().maximize();
		}
		
		
		//***********WAIT*******************
		public static void implicitwait(long sec) {
			driver.manage().timeouts().implicitlyWait(sec,TimeUnit.SECONDS);
		}
		
		
		//*********filltextbox***************
		public static void filltxtbox(WebElement e, String value) {
			e.sendKeys(value);
		}
		
		
		
		//************GET CURRENT URL*****************
		public static String getcurrenturl() {
			 return driver.getCurrentUrl();
		}
				
		//**********GET TITTLE*****************
		public static String getTitle() {
			return driver.getTitle();
		}
		
		
		//**********GETATTRIBUTE*****************
		public static String getAttribute(WebElement e) {
			return e.getAttribute("value");
		}
		
		
		//*******MOUSE ACTIONS********************
		public static void moveToElement(WebElement traget) {
			Actions a = new Actions(driver);
			a.moveToElement(traget).perform();
		}
		
		public static void draganddrop(WebElement scr, WebElement des) {
			Actions a = new Actions(driver);
			a.dragAndDrop(scr, des).perform();
		}
		public static void buttonclick(WebElement e) {
					e.click();
				}
		
		public static void selectByIndex(WebElement e, int index) {
			Select s = new Select(e);
			s.selectByIndex(index);
		}
		
		public static void selectBytxt(WebElement p,String Text) {
			Select s= new Select (p);
			s.selectByVisibleText(Text);
			
		}
		
		public void selevtByvalue(WebElement e, String Value ) {
			Select s = new Select(e);
			s.selectByValue(Value);
		}
		
		public static void clear(WebElement e) {
			e.clear();
		}
		
		//**************scrollup and scrolldown*************
		
		
		public static void pagedown(Object scrolldown) {
			JavascriptExecutor js=(JavascriptExecutor) driver;
		     js.executeScript("arguments[0].scrollIntoView();", scrolldown);
		  //  ((WebElement) scrolldown).click();
		}
		
		public static void pageup(Object scrollup) {
			JavascriptExecutor js=(JavascriptExecutor) driver;
		     js.executeScript("arguments[0].scrollIntoView();", scrollup);
		   // ((WebElement) scrollup).click();
		}
		
		
		
		//**************screenshot*******************
		
		public static void  screenshot(String path) throws IOException {
			 TakesScreenshot tk= (TakesScreenshot) driver;
			 File src = tk.getScreenshotAs(OutputType.FILE);
			// long time = System.currentTimeMillis();
			 File desc = new File("C:\\Users\\DELL\\eclipse-workspace\\Mavenclass\\Screenshot\\"+path);
			 FileUtils.copyFile(src, desc);
		
		}
		
		
		
		public static void Navigate_to(String string) {
			  try {
				driver.navigate().to(string);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
	       public static void backward() {
	    	   try {
				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
	       public static void forward() {
	    	   try {
				driver.navigate().forward();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
	       
		 public static void refresh() {
			 try {
				driver.navigate().refresh();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		
		public static WebElement findElement(String locatorname, String locvalue) {
			WebElement e = null;
			if(locatorname.equals("id")) {
				e=driver.findElement(By.id(locvalue));
			}else if (locatorname.equals("name")) {
				e=driver.findElement(By.name(locvalue));
			}else if (locatorname.equals("xpath")) {
				e=driver.findElement(By.xpath(locvalue));
			}
			return e;			
			}
		
//Alert
		public static  void simple_alert() {
			//WebElement element  = driver.findElement(arg);
			driver.switchTo().alert().accept();
		}
		
		public static void confirm_alert(String ok_or_cancel) {
			//WebElement element  = driver.findElement(arg);
			if(ok_or_cancel.equalsIgnoreCase("ok")) {
				driver.switchTo().alert().accept();
			}
			else if(ok_or_cancel.equalsIgnoreCase("cancel")) { 
				driver.switchTo().alert().dismiss();
			}
		}

		public static void prompt_alert(String arg0, String ok_or_cancel) {
			//WebElement element  = driver.findElement(arg);
			if(ok_or_cancel.equalsIgnoreCase("ok")) {
				driver.switchTo().alert().sendKeys(arg0);
				driver.switchTo().alert().accept();
			}
			else if(ok_or_cancel.equalsIgnoreCase("cancel")){
				driver.switchTo().alert().dismiss();
			}
		}
		
		
		
//key board Actions		
		
		public static void action(WebElement element) {
			//WebElement element  = driver.findElement(arg);
			Actions ac = new Actions(driver);
			ac.contextClick(element).build().perform();
		}
		
		
	//ROBOT CLASS
		public static void robot(String press_or_release) throws AWTException   {
			Robot r=new Robot();
			if(press_or_release.equalsIgnoreCase("press_down")) {
				r.keyPress(KeyEvent.VK_DOWN);
			}
			else if(press_or_release.equalsIgnoreCase("press_enter")) {
				r.keyPress(KeyEvent.VK_ENTER);
			}
			else if(press_or_release.equalsIgnoreCase("release_down")) {
				r.keyRelease(KeyEvent.VK_DOWN);
			}
			else if(press_or_release.equalsIgnoreCase("release_enter")) {
				r.keyRelease(KeyEvent.VK_ENTER);
			}
			else if(press_or_release.equalsIgnoreCase("press_up")) {
				r.keyPress(KeyEvent.VK_UP);
			}
			else if(press_or_release.equalsIgnoreCase("release_up")) {
				r.keyRelease(KeyEvent.VK_UP);
		}
		
		}
	//Mouse_action
		public static void Mouse_over( WebElement element) {
			Actions ac = new Actions(driver);
			ac.moveToElement(element).build().perform();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
		
	