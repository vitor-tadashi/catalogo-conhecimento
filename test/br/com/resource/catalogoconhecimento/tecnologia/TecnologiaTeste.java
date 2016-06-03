package br.com.resource.catalogoconhecimento.tecnologia;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class TecnologiaTeste {

	private WebDriver driver;
	private long t = new Timestamp(System.currentTimeMillis()).getTime();
	private WebElement campoNome = driver.findElement(By.name("nome"));
	private WebElement submit = driver.findElement(By.id("submit"));

	@Test
	public void adicionarTecnologiaComSucesso() {
		String tecnologia = "Selenium" + t;
		driver.get(
				"http://localhost:8080/catalogoconhecimento/mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica");
		campoNome.sendKeys(tecnologia);
		submit.click();

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
		driver = new PhantomJSDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
