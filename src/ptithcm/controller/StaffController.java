package ptithcm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.RecordEntity;
import ptithcm.entity.StaffEntity;

@Controller
@RequestMapping("/staff/")
@Transactional
public class StaffController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM StaffEntity";
		Query query = session.createQuery(hql);
		List<StaffEntity> list = query.list();
		model.addAttribute("staffs", list);
		return "staff/index";
	}

	@RequestMapping("report")
	public String report(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT rc.staffs.id," + " SUM(case when rc.type=1 then 1 else 0 end),"
				+ " SUM(case when rc.type=0 then 1 else 0 end)" + " FROM RecordEntity rc" + " GROUP BY rc.staffs.id";
		Query query = session.createQuery(hql);
		List<RecordEntity> list = query.list();
		model.addAttribute("arrays", list);

		return "staff/report";
	}

}