package br.com.resource.catalogoconhecimento.tecnologia;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TecnologiaTeste {

	private WebDriver driver;
	private long t = new Timestamp(System.currentTimeMillis()).getTime();

	@Test
	public void adicionarTecnologiaComSucesso() {
		String tecnologia = "Selenium" + t;
		driver.get(
				"http://localhost:8080/catalogoconhecimento/mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica");
		driver.findElement(By.name("nome")).sendKeys(tecnologia);
		driver.findElement(By.id("submit")).click();

		Assert.assertTrue("Deveria conter: " + tecnologia, driver.getPageSource().contains(tecnologia));
	}

	@Test
	public void alterarTecnologiaComSucesso() {
		String tecnologia = "Selenium" + t;
		driver.get(
				"http://localhost:8080/catalogoconhecimento/mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica");
		driver.findElement(By.name("nome")).sendKeys(tecnologia);
		driver.findElement(By.id("submit")).click();

		driver.findElement(By.name("alt" + tecnologia)).sendKeys(tecnologia);

		long t = new Timestamp(System.currentTimeMillis()).getTime();
		tecnologia = "Selenium" + t;
		driver.findElement(By.name("nome")).sendKeys(tecnologia);
		driver.findElement(By.id("submit")).click();

		Assert.assertTrue("Deveria conter: " + tecnologia, driver.getPageSource().contains(tecnologia));
	}

	@Before
	public void setup() {
		ArrayList<String> cliArgsCap = new ArrayList<String>();
		DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
		cliArgsCap.add("--web-security=false");
		cliArgsCap.add("--ssl-protocol=any");
		cliArgsCap.add("--ignore-ssl-errors=true");
		cliArgsCap.add("--webdriver-loglevel=INFO");
		cliArgsCap.add("--load-images=false");

		capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
		capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
		driver = new PhantomJSDriver(capabilities);
		// driver = new PhantomJSDriver();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
