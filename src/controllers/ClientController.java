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
import models.vol.DetailsPlace;

@Controller
public class ClientController {
	/*
	 * public ModelAndView toClient(EmbedSession embedSession) throws Exception {
	 * ModelAndView mv = new ModelAndView(true, "views/frontend/index.jsp");
	 * try (Connection c = Connect.getConnection()) {
	 * List<DetailsPlace> detailsPlace = DetailsPlace.getAllDispo(c, 1, 10);
	 * detailsPlace.stream().forEach(d -> {
	 * try {
	 * d.getVilleArrivee(c);
	 * d.getVilleDepart(c);
	 * d.getAvion(c);
	 * } catch (Exception e) {
	 * mv.setAttribute("error", e.getMessage());
	 * }
	 * });
	 * mv.setAttribute("count", Connect.getCount(c, "details_place"));
	 * mv.setAttribute("vols", detailsPlace);
	 * } catch (Exception e) {
	 * throw e;
	 * }
	 * 
	 * return mv;
	 * }
	 */
	@RequestMapping(path = "/TripZip/client")
	public ModelAndView toClient(EmbedSession embedSession) throws Exception {
		ModelAndView mv = new ModelAndView(true, "views/frontend/index.jsp");
		Utilisateur utilisateur = ((Utilisateur)embedSession.get("utilisateur"));
		try (Connection c = Connect.getConnection()) {
			List<Reservation> reservations = Reservation.getByIdUtilisateur(c, utilisateur.getIdUtilisateur());
			reservations.stream().forEach(d -> {
				try {
					d.getSiegeVol(c);
					d.getSiegeVol().getSiege(c);
					d.getSiegeVol().getVol(c);
					d.getSiegeVol().getVol().getData(c);
				} catch (Exception e) {
					mv.setAttribute("error", e.getMessage());
				}
			});
			mv.setAttribute("reservations", reservations);
		} catch (Exception e) {
			throw e;
		}

		return mv;
	}
}
