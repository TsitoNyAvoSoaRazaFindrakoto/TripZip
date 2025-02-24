package controllers;

import java.sql.Connection;

import database.Connect;
import mg.itu.prom16.annotations.auth.Auth;
import mg.itu.prom16.annotations.parameter.Param;
import mg.itu.prom16.annotations.parameter.ParamObject;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.annotations.validation.Fallback;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.avion.Avion;
import models.reservation.ConfigReservation;
import models.vol.Ville;
import models.vol.Vol;

@Controller
@Auth(role = "admin")
public class StaffController {
	@RequestMapping(path = "/TripZip/vols/form")
	public ModelAndView toForm(@Param Integer idVol) throws Exception {
		ModelAndView mv = new ModelAndView("/views/backend/vol/form.jsp");
		try (Connection c = Connect.getConnection()) {
			if (idVol != null && idVol > 0) {
				Vol v = new Vol().getById(c, idVol);
				v.getData(c);
				mv.setAttribute("vol", v);
				mv.setView("/views/backend/vol/formUpdate.jsp");
			}
			mv.setAttribute("villes", Ville.getAll(c));
			mv.setAttribute("avions", Avion.getAll(c));
			mv.setAttribute("config", ConfigReservation.getAll(c));
		}
		return mv;
	}

	@RequestMapping(path = "/TripZip/vols/detail")
	public ModelAndView toDetail(@Param int idVol) {
		return new ModelAndView("/views/backend/detail.jsp");
	}

	@Fallback(method = "GET", verb = "/vols/form")
	@RequestMapping(path = "/TripZip/vols", method = "POST")
	public ModelAndView createVol(@ParamObject Vol vol) throws Exception {
		ModelAndView mv = new ModelAndView("/vols");
		if (vol.getIdVol() == 0) {
			vol.save(null);
		} else {
			vol.update(null);
		}
		return mv;
	}

	@RequestMapping(path = "/TripZip/config")
	public ModelAndView toConfig(@Param String libelle) throws Exception {
		ModelAndView mv = new ModelAndView("/views/backend/config.jsp");
		try (Connection c = Connect.getConnection()) {
			mv.setAttribute("config", new ConfigReservation().getById(c, libelle));
		}
		return mv;
	}

	@RequestMapping(path = "/TripZip/config", method = "POST")
	public ModelAndView updateConfig(@ParamObject ConfigReservation conf) throws Exception {
		ModelAndView mv = new ModelAndView("/vols");
		try (Connection c = Connect.getConnection()) {
			conf.update(c);
			c.close();
		}
		return mv;
	}
}
