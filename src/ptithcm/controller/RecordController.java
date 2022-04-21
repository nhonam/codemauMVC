package ptithcm.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.RecordEntity;
import ptithcm.entity.StaffEntity;


@Transactional
@Controller
@RequestMapping("/record/")
public class RecordController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model, @ModelAttribute("record") RecordEntity record) {
		List<RecordEntity> records = this.getRecords();
		model.addAttribute("records", records);
		return "record/index";
	}
	
	@RequestMapping(value = "index", method = RequestMethod.POST)
	public String insert(HttpServletRequest request, ModelMap model, @ModelAttribute("record") RecordEntity record) {
		Integer temp = this.insertRecord(record);
		if(temp != 0) {
			model.addAttribute("message","Thêm mới thành công");
			record.setReason(null);
			record.setType(null);
		}else {
			model.addAttribute("message","Thêm mới thất bại");
		}
		List<RecordEntity> records = this.getRecords();
		model.addAttribute("records", records);
		return "record/index";
	}
	
	public Integer insertRecord(RecordEntity record) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			record.setDate(new Date());
			session.save(record);
			t.commit();			
		}
		catch (Exception e) {
			t.rollback();
			return 0;			
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	@RequestMapping(value = "index/{id}.htm", params = "linkDelete")
	public String deleteRecord (HttpServletRequest request, ModelMap model, @ModelAttribute("record") RecordEntity record,
			@PathVariable("id") Long id) {
		Integer temp = this.deleteRecord(this.getRecord(id));
		List<RecordEntity> records = this.getRecords();
		model.addAttribute("records", records);	
		if(temp != 0) {
			model.addAttribute("message","Delete thành công");
		}
		else {
			model.addAttribute("message", "Delete không thành công");
		}
		return "record/index";
	}
	public Integer deleteRecord (RecordEntity record) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(record);
			t.commit();
		}
		catch (Exception e){
			t.rollback();
			return 0;
		}
		finally{
			session.close();
		}
		return 1;
	}
	
	public static Date temp_date;
	public static Long temp_id;
	@RequestMapping(value = "index/{id}", params = "linkEdit" )
	public String editRecord (HttpServletRequest request, ModelMap model, @ModelAttribute("record") RecordEntity record, 
			@PathVariable("id") Long id) {
		temp_id = id;
		temp_date = this.getRecord(id).getDate();
		List<RecordEntity> records = this.getRecords();
		model.addAttribute("records", records);
		model.addAttribute("record", this.getRecord(id));
		model.addAttribute("btnupdate","true");
		return "record/index";
	}

	@RequestMapping(value = "index", params = "btnupdate")
	public String edit_Record(HttpServletRequest request, ModelMap model, 
			@ModelAttribute("record") RecordEntity record) {
		record.setDate(temp_date);
		record.setId(temp_id);
		Integer temp = this.updateRecord(record);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
			record.setReason(null);
			record.setType(null);
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");
		}
		List<RecordEntity> records = this.getRecords();
		model.addAttribute("records", records);
		return "record/index";
	}
	
	public Integer updateRecord(RecordEntity record) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.update(record);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	@ModelAttribute("staffsSel")
	public List<StaffEntity> getStaffs(){
		Session session = factory.getCurrentSession();
		String hql = "FROM StaffEntity";
		Query query = session.createQuery(hql);
		List<StaffEntity> list = query.list();
		return list;
	}
	
	public RecordEntity getRecord (Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM RecordEntity where id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		RecordEntity list = (RecordEntity) query.list().get(0);
		return list;
	}
	
	public List<RecordEntity> getRecords(){
		Session session = factory.getCurrentSession();
		String hql = "FROM RecordEntity";
		Query query = session.createQuery(hql);
		List<RecordEntity> list = query.list();
		return list;
	}
	
	
}