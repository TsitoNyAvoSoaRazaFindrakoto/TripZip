package controllers;

import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.types.returnType.ModelAndView;

@Controller
public class ClientController {
	@RequestMapping(path = "/TripZip/client")
	public ModelAndView toClient() {
		return new ModelAndView(true, "views/frontend/index.jsp");
	}
}
