package controllers;

import database.Connect;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.vol.DetailsPlace;

@Controller
public class ReservationController {
	@RequestMapping(path = "/TripZip/front-office")
	public ModelAndView toList() {
		ModelAndView mv = new ModelAndView("frontend/index.jsp");
		mv.setAttribute("vols", DetailsPlace.getAll(1, 10));
		mv.setAttribute("count", Connect.getCount(null,"details_place"));
		return mv;
	}
}
