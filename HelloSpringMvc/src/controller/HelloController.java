package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello Spring MVC");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }

    @RequestMapping("/test")
    public ModelAndView handleTestRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav1 = new ModelAndView("test");
        mav1.addObject("message", "Hello Spring MVC test");

        return mav1;
    }
//    @RequestMapping("/param")
//    public ModelAndView getParam(HttpServletRequest request,
//                                 HttpServletResponse response){
//        System.out.println("进入params");
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//
//        System.out.println(userName);
//        System.out.println(password);
//        return null;
//    }

//        @RequestMapping("/param")
//    public ModelAndView getParam(@RequestParam(value="userName11") String userName,
//                                 @RequestParam(value="password11") String password ){
//        System.out.println("进入params");
////        String userName = request.getParameter("userName");
////        String password = request.getParameter("password");
//
//        System.out.println(userName);
//        System.out.println(password);
//        return null;
//    }

    @RequestMapping("/param")
    public ModelAndView getParam(User user ){
        System.out.println("进入params");
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");

        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        return null;
    }
}
