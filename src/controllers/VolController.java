package controllers;

import java.sql.Connection;
import java.util.List;

import database.Connect;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.embed.EmbedSession;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.vol.DetailsPlace;

@Controller
public class VolController {

	public ModelAndView toClient(EmbedSession embedSession) throws Exception {
		ModelAndView mv = new ModelAndView(true, "views/frontend/index.jsp");
		try (Connection c = Connect.getConnection()) {
			List<DetailsPlace> detailsPlace = DetailsPlace.getAllDispo(c, 1, 10);
			detailsPlace.stream().forEach(d -> {
				try {
					d.getVilleArrivee(c);
					d.getVilleDepart(c);
					d.getAvion(c);
				} catch (Exception e) {
					mv.setAttribute("error", e.getMessage());
				}
			});
			mv.setAttribute("count", Connect.getCount(c, "details_place"));
			mv.setAttribute("vols", detailsPlace);
		} catch (Exception e) {
			throw e;
		}

		return mv;
	}
}
