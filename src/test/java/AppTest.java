import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("puzzle");
  }

  @Test
  public void makesAPuzzle() {
      goTo("http://localhost:4567/");
      fill("#inputPhrase").with("I hope this works!");
      submit(".btn");
      assertThat(pageSource()).contains("- h-p- th-s w-rks!");
  }

  @Test
  public void puzzleMaker_returnPhrase_rhythm() {
    App testApp = new App();
    assertEquals("rhythm", testApp.puzzleMaker("rhythm"));
  }

  @Test
  public void puzzleMaker_replaceLowercaseAWithADash_DASHnt() {
    App testApp = new App();
    assertEquals("-nt", testApp.puzzleMaker("ant"));
  }

  @Test
  public void puzzleMaker_replaceAllvowelsWithADash_DASHdCDASHtDASHcDASHDASHpDDASHmb() {
    App testApp = new App();
    assertEquals("-d c-t -c- -p D-mb.", testApp.puzzleMaker("Ed cat Ice Up Dumb."));
  }
}
