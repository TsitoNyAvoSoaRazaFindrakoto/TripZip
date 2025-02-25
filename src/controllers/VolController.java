package controllers;

import java.sql.Connection;
import java.util.List;

import database.Connect;
import mg.itu.prom16.annotations.parameter.Param;
import mg.itu.prom16.annotations.request.Controller;
import mg.itu.prom16.annotations.request.RequestMapping;
import mg.itu.prom16.embed.EmbedSession;
import mg.itu.prom16.types.returnType.ModelAndView;
import models.vol.DetailsPlace;

@Controller
public class VolController {

	@RequestMapping(path = "/TripZip/vols")
	public ModelAndView toClient(EmbedSession embedSession, @Param Integer page) throws Exception {
		ModelAndView mv = new ModelAndView("/views/frontend/index.jsp");
		try (Connection c = Connect.getConnection()) {
			List<DetailsPlace> detailsPlace = DetailsPlace.getAllDispo(c, page, 8,false);
			detailsPlace.stream().forEach(d -> {
				try {
					d.getData(c);
				} catch (Exception e) {
					mv.setView("/views/error.jsp");
					mv.setAttribute("error", e);
					System.out.println(e.getMessage());
					return;
				}
			});
			mv.setAttribute("count", Connect.getCount(c, "details_place"));
			mv.setAttribute("page", page == null ? 1 : page);
			mv.setAttribute("vols", detailsPlace);
		} catch (Exception e) {
			throw e;
		}

		return mv;
	}

	
}
