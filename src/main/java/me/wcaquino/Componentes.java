package me.wcaquino;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Componentes {

    public WebDriver driver;
    private DSL dsl;

    @Before
    public void inicio(){
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("https://wcaquino.me/selenium/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void TextField() {
        dsl.escreve("elementosForm:nome", "Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.verificatexto("elementosForm:nome"));
    }

    @Test
    public void Textarea() {
        dsl.escreve("elementosForm:sugestoes","Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.verificatexto("elementosForm:sugestoes"));

    }

    @Test
    public void RadioButton() {
        dsl.clickarnoelemento("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void Checkbox() {
        dsl.clickarnoelemento("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void Combobox() {
        dsl.SelecionarCombo("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.ObterValorAtualcombo("elementosForm:escolaridade"));
    }

    @Test
    public void VerificarValoresComboBox() {
        Assert.assertEquals(8, dsl.QuantidadeDeOpcoes("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
    }
    @Test
    public void VerificarComboMulti() {
        dsl.SelecionarCombo("elementosForm:esportes", "Natacao");
        dsl.SelecionarCombo("elementosForm:esportes", "Natacao");
        dsl.SelecionarCombo("elementosForm:esportes", "O que eh esporte?");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());

    }
    @Test
    public void Botao() {
        driver.findElement(By.id("buttonSimple")).click();
        Assert.assertEquals("Obrigado!", driver.findElement(By.id("buttonSimple")).getAttribute("value") );

    }
    @Test
    public void Links() {
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
    }
}
