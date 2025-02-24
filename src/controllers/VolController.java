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
import models.vol.Vol;

@Controller
public class VolController {

	@RequestMapping(path = "/TripZip/vols")
	public ModelAndView toClient(EmbedSession embedSession, @Param Integer page) throws Exception {
		ModelAndView mv = new ModelAndView("views/frontend/index.jsp");
		try (Connection c = Connect.getConnection()) {
			List<DetailsPlace> detailsPlace = DetailsPlace.getAllDispo(c, page, 8);
			System.out.println("we have " + detailsPlace.size() + " detailsPlace");
			detailsPlace.stream().forEach(d -> {
				try {
					d.getData(c);
				} catch (Exception e) {
					mv.setView("views/error.jsp");
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

	@RequestMapping(path = "/TripZip/vols/form")
	public ModelAndView toForm(@Param Integer idVol) throws Exception{
		ModelAndView mv = new ModelAndView("views/backend/vol/form.jsp");
		if (idVol != null) {
			try (Connection c = Connect.getConnection()) {
				Vol v = new Vol().getById(c, idVol);
				mv.setAttribute("vol", v);
			}
		}
		return mv;
	}

	@RequestMapping(path = "/TripZip/vols/detail")
	public ModelAndView toDetail(@Param int idVol) {
		return new ModelAndView("views/backend/detail.jsp");
	}

	@RequestMapping(path = "/TripZip/vols", method = "POST")
	public ModelAndView createVol() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
}
