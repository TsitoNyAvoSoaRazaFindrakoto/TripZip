package controllers;

import java.sql.Connection;
import java.util.List;

import database.Connect;
import mg.itu.prom16.annotations.parameter.Param;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.embed.EmbedSession;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.Utilisateur;
import models.reservation.Reservation;

@Controller
public class ProfilController {
	@RequestMapping(path = "/TripZip/login")
	public ModelAndView toLogin() {
		return new ModelAndView("/views/login.jsp");
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
		mv.setView(u.getRole().equalsIgnoreCase("client") ? "/profil" : "/vols");
		return mv;
	}

	@RequestMapping(path = "/TripZip/logout")
	public ModelAndView toLogout(EmbedSession embedSession) {
		embedSession.remove("utilisateur");
		embedSession.remove("role");
		return new ModelAndView("/login");
	}

	@RequestMapping(path = "/TripZip/profil")
	public ModelAndView toClient(EmbedSession embedSession) throws Exception {
		ModelAndView mv = new ModelAndView("/views/frontend/profil.jsp");
		Utilisateur utilisateur = ((Utilisateur) embedSession.get("utilisateur"));
		try (Connection c = Connect.getConnection()) {
			List<Reservation> reservations = Reservation.getByIdUtilisateur(c, utilisateur.getIdUtilisateur());
			reservations.stream().forEach(d -> {
				try {
					d.getSiegeVol(c);
					d.getSiegeVol().getSiege(c);
					d.getSiegeVol().getVol(c);
					d.getSiegeVol().getVol().getData(c);
				} catch (Exception e) {
					mv.setView("/views/error.jsp");
					mv.setAttribute("error", e);
					System.out.println(e.getMessage());
					return;
				}
			});
			mv.setAttribute("reservations", reservations);
		} catch (Exception e) {
			throw e;
		}
		return mv;
	}

}