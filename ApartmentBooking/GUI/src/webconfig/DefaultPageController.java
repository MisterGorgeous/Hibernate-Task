package webconfig;

import cache.ApartmentCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class DefaultPageController {

    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        //System.out.println("Handler");
        ApartmentCache apartmentCache;
        return "index";
    }

  /*  @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        model.setViewName("login");

        return model;

    }*/
}
