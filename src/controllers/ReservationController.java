package controllers;

import java.sql.Connection;

import database.Connect;
import mg.itu.prom16.annotations.parameter.Param;
import mg.itu.prom16.annotations.parameter.ParamObject;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.annotations.validation.Fallback;
import mg.itu.prom16.embed.EmbedSession;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.exception.ReservationValidationException;
import models.reservation.Reservation;
import models.vol.DetailsPlace;

@Controller
public class ReservationController {

	@Fallback(method = "GET", verb = "/TripZip/reservation")
	@RequestMapping(path = "/TripZip/reservation", method = "POST")
	public ModelAndView saveReservation(@ParamObject Reservation reservation) throws Exception {
		ModelAndView mv = new ModelAndView(true,"/TripZip/profile");
		try (Connection c = Connect.getConnection()) {
			reservation.validateReservation();
			c.setAutoCommit(false);
			reservation.save(c);
			mv.setAttribute("success", "Reservation effectuée avec succès");
			c.commit();
		} catch (ReservationValidationException e) {
			mv.setAttribute("error", e.getMessage());
			mv.setRedirect(false);
			mv.setView("/TripZip/reservation?idSiegeVol="+reservation.getIdSiegeVol());
			return mv;
		}
		return mv;
	}

	@RequestMapping(path = "/TripZip/reservation")
	public ModelAndView toReservation(EmbedSession embedSession, @Param int idSiegeVol) throws Exception {
		ModelAndView mv = new ModelAndView("views/frontend/reservation.jsp");
		DetailsPlace d = DetailsPlace.getByIdSiegeVol(idSiegeVol);
		try (Connection c = Connect.getConnection()) {
			d.getSiege(c);
			d.getData(c);	
		}
		mv.setAttribute("vol", d);
		return mv;
	}

}
