package controllers;

import java.sql.Connection;
import java.util.List;

import database.Connect;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.embed.EmbedSession;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.Utilisateur;
import models.reservation.Reservation;

@Controller
public class ClientController {
	@RequestMapping(path = "/TripZip/profil")
	public ModelAndView toClient(EmbedSession embedSession) throws Exception {
		ModelAndView mv = new ModelAndView("views/frontend/profil.jsp");
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
					mv.setView("views/error.jsp");
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
