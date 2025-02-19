package controllers;

import database.Connect;
import mg.itu.prom16.annotations.parameter.Param;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.vol.DetailsPlace;
import models.vol.Ville;

@Controller
public class ReservationController {
	@RequestMapping(path = "/TripZip/front-office")
	public ModelAndView toList(@Param int page) {
		ModelAndView mv = new ModelAndView("frontend/index.jsp");
		mv.setAttribute("vols", DetailsPlace.getAll(1, 10));
		mv.setAttribute("count", Connect.getCount(null, "details_place"));
		mv.setAttribute("villes", Ville.getAll());
		return mv;
	}

	@RequestMapping(path = "/TripZip/front-office")
	public ModelAndView toReservtion() {
		return new ModelAndView("frontend/reservation/form.jsp");
	}
}
