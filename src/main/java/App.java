import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {

  }

  public static String puzzleMaker(String phrase){

    String wordPuzzle = phrase.replaceAll("[aeiouAEIOU]", "-");
    return wordPuzzle;
  }
}
