package br.com.resource.catalogoconhecimento.tecnologia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TecnologiaTeste {

	private WebDriver driver;
	
//	@Test
//	public void adicionarTecnologiaComSucesso() {
//		// String tecnologia = adicionarTecnologia();
//		long t = new Timestamp(System.currentTimeMillis()).getTime();
//		String tecnologia = "Selenium" + t;
//		driver.get(
//				"http://localhost:8080/catalogoconhecimento/mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica");
//		driver.findElement(By.name("nome")).sendKeys(tecnologia);
//		driver.findElement(By.id("submit")).click();
//		WebElement altTecnologia = driver.findElement(By.name("alt" + tecnologia));
//		WebElement delTecnologia = driver.findElement(By.name("del" + tecnologia));
//
//		Assert.assertTrue("Deveria conter: " + tecnologia, altTecnologia.isDisplayed() && delTecnologia.isDisplayed());
//	}

	@Test
	public void adicionarCargoComSucesso() {
		// String tecnologia = adicionarTecnologia();
		long t = new Timestamp(System.currentTimeMillis()).getTime();
		String cargo = "Selenium" + t;
		driver.get(
				"http://localhost:8080/catalogoconhecimento/mvc?logica=cargo.FormularioAdicionarCargoLogica");
		driver.findElement(By.name("nome")).clear();
		driver.findElement(By.name("nome")).sendKeys(cargo);
		driver.findElement(By.id("submit")).click();
//		WebElement altCargo = driver.findElement(By.name("alt" + cargo));
//		WebElement delCargo = driver.findElement(By.name("del" + cargo));
//
//		Assert.assertTrue("Deveria conter: " + cargo, altCargo.isDisplayed() && delCargo.isDisplayed());
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/catalogoconhecimento/mvc?logica=cargo.ListarCargoLogica"));
	}
	
	// @Test
	// public void alterarTecnologiaComSucesso() {
	// String tecnologia = adicionarTecnologia();
	//
	// driver.findElement(By.name("alt" + tecnologia)).sendKeys(tecnologia);
	//
	// long t = new Timestamp(System.currentTimeMillis()).getTime();
	// tecnologia = "Selenium" + t;
	// driver.findElement(By.name("nome")).sendKeys(tecnologia);
	// driver.findElement(By.id("submit")).click();
	//
	// Assert.assertTrue("Deveria conter: " + tecnologia,
	// driver.getPageSource().contains(tecnologia));
	// }

	// @Test
	// public void removerTecnologiaComSucesso() {
	// String tecnologia = adicionarTecnologia();
	//
	// driver.findElement(By.name("del" + tecnologia)).sendKeys(tecnologia);
	//
	// Assert.assertFalse("Não deveria conter: " + tecnologia,
	// driver.getPageSource().contains(tecnologia));
	// }

	// public String adicionarTecnologia() {
	// long t = new Timestamp(System.currentTimeMillis()).getTime();
	// String tecnologia = "Selenium" + t + " ";
	// driver.get(
	// "http://localhost:8080/catalogoconhecimento/mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica");
	// driver.findElement(By.name("nome")).sendKeys(tecnologia);
	// driver.findElement(By.id("submit")).click();
	//
	// return tecnologia;
	// }

	@Before
	public void setup() {
		ArrayList<String> cliArgsCap = new ArrayList<String>();
		DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
		cliArgsCap.add("--web-security=false");
		cliArgsCap.add("--ssl-protocol=any");
		cliArgsCap.add("--ignore-ssl-errors=true");
		cliArgsCap.add("--webdriver-loglevel=DEBUG");
		cliArgsCap.add("--load-images=false");

		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
		capabilities.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
		capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
		capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, false);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
		driver = new PhantomJSDriver(capabilities);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
