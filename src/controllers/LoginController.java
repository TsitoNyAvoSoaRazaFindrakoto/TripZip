package controllers;

import mg.itu.prom16.annotations.parameter.Param;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.embed.EmbedSession;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.Utilisateur;

@Controller
public class LoginController {
	@RequestMapping(path = "/TripZip/login")
	public ModelAndView toLogin() {
		return new ModelAndView("views/login.jsp");
	}

	@RequestMapping(path = "/TripZip/login", method = "POST")
	public ModelAndView toRegister(EmbedSession embedSession, @Param String email, @Param String mdp) {
		ModelAndView mv = new ModelAndView();
		Utilisateur u = Utilisateur.login(email, mdp);
		if (u == null) {
			mv.setAttribute("error", "Email ou mot de passe incorrect");
			mv.setView("index.jsp");
			return mv;
		}
		embedSession.add("utilisateur", u);
		embedSession.add("role", u.getRole());
		mv.setRedirect(true);
		mv.setView(u.getRole().equalsIgnoreCase("client") ? "/TripZip/front-office": "/TripZip/staff");
		return mv;
	}

	@RequestMapping(path = "/TripZip/logout")
	public ModelAndView toLogout(EmbedSession embedSession) {
		embedSession.remove("utilisateur");
		embedSession.remove("role");
		return new ModelAndView("TripZip/login");
	}

}