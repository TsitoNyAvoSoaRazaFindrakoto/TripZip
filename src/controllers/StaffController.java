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
import models.reservation.RuleConfig;
import models.vol.Ville;
import models.vol.Vol;
import models.vol.SiegeVol;
import models.Siege;

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
		}
		return mv;
	}

	@RequestMapping(path = "/TripZip/vols/detail")
	public ModelAndView toDetail(@Param int idVol) throws Exception {
		ModelAndView mv = new ModelAndView("/views/backend/vol/detail.jsp");
		try (Connection c = Connect.getConnection()) {
			Vol v = new Vol().getById(c, idVol);
			v.getData(c);
			mv.setAttribute("vol", v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Fallback(method = "GET", verb = "/vols/form", parameters = { "idVol" })
	@RequestMapping(path = "/TripZip/vols", method = "POST")
	public ModelAndView createVol(@ParamObject Vol vol) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (vol.getIdVol() == 0) {
			vol.save(null);
			vol.getData(null);
		} else {
			vol.update(null);
		}
		mv.setView("/vols/details?idVol=" + vol.getIdVol());
		return mv;
	}

	@RequestMapping(path = "/TripZip/config")
	public ModelAndView toConfig(@Param String libelle) throws Exception {
		ModelAndView mv = new ModelAndView("/views/backend/config.jsp");
		try (Connection c = Connect.getConnection()) {
			mv.setAttribute("config", new RuleConfig().getById(c, libelle));
		}
		return mv;
	}

	@RequestMapping(path = "/TripZip/vols/sieges/edit")
	public ModelAndView modifySiegeVolDet(@Param Integer idSiegeVol) throws Exception {
		ModelAndView mv = new ModelAndView("/views/backend/siegeVol/edit.jsp");
		try (Connection c = Connect.getConnection()) {
			if (idSiegeVol != null) {
				SiegeVol siegeVol = new SiegeVol().getById(c, idSiegeVol);
				mv.setAttribute("siegeVol", siegeVol);
				mv.setAttribute("sieges", Siege.getAll(c));
			}
		}
		return mv;
	}

	@Fallback(method = "GET", verb = "/vols/sieges/edit", parameters = { "idSiegeVol" })
	@RequestMapping(path = "/TripZip/vols/sieges/edit", method = "POST")
	public ModelAndView updateSiegeVol(@ParamObject SiegeVol siegeVol) throws Exception {
		ModelAndView mv = new ModelAndView();
		try (Connection c = Connect.getConnection()) {
			siegeVol.saveOrUpdate(c);
		}
		mv.setView("/vols/detail?idVol=" + siegeVol.getIdVol());
		return mv;
	}

	@RequestMapping(path = "/TripZip/config", method = "POST")
	public ModelAndView updateConfig(@ParamObject RuleConfig conf) throws Exception {
		ModelAndView mv = new ModelAndView("/vols");
		try (Connection c = Connect.getConnection()) {
			conf.update(c);
			c.close();
		}
		return mv;
	}
}
