package ForPdf;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadPdf 
{
	WebDriver webDriver;
	String output = "";
	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Arpan\\JavaWorkspace\\Sample-WS1\\MyNewProject\\resources\\chromedriver.exe");
		webDriver = new ChromeDriver();
	}
	
	@Test
	public void readPdf() throws Exception
	{
		webDriver.get("file:///C:/Users/Arpan/Downloads/invoice.pdf");
		URL url = new URL(webDriver.getCurrentUrl());
		InputStream inputStream = url.openStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		PDDocument doc = null;
		try
		{
			doc = PDDocument.load(bis);
			output = new PDFTextStripper().getText(doc);
		}
		
		finally
		{
			if(doc !=null)
			{
				doc.close();
			}
		}
		
		bis.close();
		inputStream.close();
		
		System.out.println(output);
	}
	
	@AfterTest
	public void closeDriver()
	{
		if(webDriver == null)
			webDriver.close();
		webDriver.quit();
	}
}
