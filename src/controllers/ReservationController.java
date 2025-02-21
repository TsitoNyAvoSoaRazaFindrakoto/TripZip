package controllers;

import java.sql.Connection;

import database.Connect;
import mg.itu.prom16.annotations.parameter.Param;
import mg.itu.prom16.annotations.parameter.ParamObject;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.embed.EmbedSession;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.reservation.Reservation;
import models.vol.DetailsPlace;

@Controller
public class ReservationController {

	@RequestMapping(path = "/TripZip/reservation", method = "POST")
	public ModelAndView saveReservation(@ParamObject Reservation reservation) {
		ModelAndView mv = new ModelAndView("/TripZip/client");
		try (Connection c = Connect.getConnection()) {
			reservation.validateReservation();
			c.setAutoCommit(false);
			reservation.save(c);
			mv.setAttribute("success", "Reservation effectuée avec succès");
			c.commit();
		} catch (Exception e) {
			mv.setAttribute("error", e.getMessage());
			mv.setView("/TripZip/client");
		}
		return mv;
	}

	@RequestMapping(path = "/TripZip/reservation")
	public ModelAndView toReservation(EmbedSession embedSession, @Param int idSiegeVol) {
		ModelAndView mv = new ModelAndView(true, "views/frontend/reservation.jsp");
		DetailsPlace detailsPlace = DetailsPlace.getByIdSiegeVol(idSiegeVol);
		try(Connection c = Connect.getConnection()) {
			detailsPlace.getVilleArrivee(c);
			detailsPlace.getVilleDepart(c);
			detailsPlace.getAvion(c);
		} catch (Exception e) {
		}
		mv.setAttribute("vol", detailsPlace);
		return mv;
	}

}
