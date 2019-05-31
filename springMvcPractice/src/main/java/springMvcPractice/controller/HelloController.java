package springMvcPractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

 /*   @RequestMapping("/")
////    @ResponseBody
    public String Hello(){
//        return "Hello world!";

        return "resultPage";
    }*/

//    @Autowired
//    private Twitter twitter;
    @RequestMapping("/")
    public String home() {
        return "searchPage";
    }
    @RequestMapping("/result")
    public String hello(@RequestParam(defaultValue =
            "masterSpringMVC4") String search, Model model) {
//        SearchResults searchResults = twitter.searchOperations().
//                search(search);
//        List<Tweet> tweets = searchResults.getTweets();
        model.addAttribute("tweets", "tweets information ");
        model.addAttribute("search", "search information ");
        return "resultPage";
    }
    @RequestMapping(value = "/postSearch", method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request,
                             RedirectAttributes redirectAttributes) {
        String search = request.getParameter("search");
        if (search.toLowerCase().contains("struts")) {
            redirectAttributes.addFlashAttribute("error", "Try using spring instead!");
            return "redirect:/";
        }
        redirectAttributes.addAttribute("search", search);
        return "redirect:result";
    }
    @RequestMapping("/default")
    public String getDefaultPage(){
//        ModelAndView mvs = new ModelAndView();
//        mvs.addObject("default", "/layout/default");
//        return mvs;
        return "/layout/default";
    }
}



