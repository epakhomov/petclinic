package org.springframework.samples.petclinic.system;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

@Controller
class WelcomeController {

    @GetMapping("/")
    public String welcome(HttpServletRequest request, HttpServletResponse response, Map<String,Object> model)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i=0; i<cookies.length; i++) {
                if (cookies[i].getName().equals("session-id")) {
                    model.put("sessionId", cookies[i].getValue());
                }
            }
        }

        if (model.get("sessionId") == null) {
            model.put("sessionId", "<undefined>");

            Cookie cookie = new Cookie("session-id", "133-6685278-2004532");
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
        }

        return "welcome";
    }
}
