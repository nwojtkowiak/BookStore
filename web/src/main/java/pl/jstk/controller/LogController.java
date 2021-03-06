package pl.jstk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;

@Controller
public class LogController {

    private static final String INFO_TEXT = "You are logged";
    protected static final String WELCOME = "This is a welcome page";

    /**
     * This method redirects to a page with a form to login.
     *
     * @return view for login
     */
    @GetMapping(value = "/login")
    public String login() {

        return ViewNames.LOGIN;
    }

    /**
     * This method redirects to a page with a form to login after incorrect login
     *
     * @param ra - redirect attributes enables transfer info about wrong login to view
     * @return redirect to page with login form
     */
    @GetMapping(value = "/loginError")
    public String loginError(RedirectAttributes ra) {
        ra.addFlashAttribute("error", true);
        return "redirect:/" + ViewNames.LOGIN;
    }

    /**
     * This method redirect to home page after success login
     *
     * @param model - this model accpets info about successfully login
     * @return view tih home page
     */
    @PostMapping(value = "/loginSuccess")
    public String loginSuccess(Model model) {
        model.addAttribute(ModelConstants.MESSAGE, WELCOME);
        model.addAttribute(ModelConstants.INFO, INFO_TEXT);
        return ViewNames.WELCOME;
    }


}
