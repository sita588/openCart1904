package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties prop;
	
	@BeforeClass(groups= {"Sanity","Master","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//loading config properties
		prop=new Properties();
		FileReader fp=new FileReader("./src//test//resources//config.properties");
		prop.load(fp);
		
		logger =LogManager.getLogger(this.getClass());
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WINDOWS);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			
			case "default" : System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
		}
		
		
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) //if execution env is local
		{
			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver(); break;
			
			case "edge" : driver=new EdgeDriver(); break;
			
			default: System.out.println("Invalid browser..."); return;
			
			}
		}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appURL"));
		
	}
	
	@AfterClass(groups= {"Sanity","Master","Regression"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public static String randomString()
	{
		return RandomStringUtils.randomAlphabetic(6);
	}
	
	public static String randomNumber()
	{
		return RandomStringUtils.randomNumeric(9);
	}
	
	public  static String randomAlphaNumeric()
	{
		String pwd= RandomStringUtils.randomAlphabetic(3)+"@"+RandomStringUtils.randomNumeric(3);
		return pwd;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
