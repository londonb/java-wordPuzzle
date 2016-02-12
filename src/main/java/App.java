import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (reqest,response)-> {
      HashMap model = new HashMap();
      model.put("template","templates/wordentry.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/puzzle", (request,response)-> {
      HashMap model = new HashMap();
      String inputPhrase = request.queryParams("inputPhrase");
      String puzzlePhrase = App.puzzleMaker(inputPhrase);
      model.put("puzzle", puzzlePhrase);
      model.put("template", "templates/makepuzzle.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static String puzzleMaker(String phrase){

    String wordPuzzle = phrase.replaceAll("[aeiouAEIOU]", "-");
    return wordPuzzle;
  }
}
