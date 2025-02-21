package controllers;

import mg.itu.prom16.annotations.auth.Auth;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.types.returnType.ModelAndView;

@Controller
@Auth(role = "admin")
public class StaffController {
	@RequestMapping(path = "/TripZip/staff")
	public ModelAndView toAdmin() {
		return new ModelAndView(true, "views/backend/index.jsp");
	}
}
