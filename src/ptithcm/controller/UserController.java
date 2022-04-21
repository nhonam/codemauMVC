package ptithcm.controller;

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

import ptithcm.bean.User;
import ptithcm.entity.UserEntity;

@Transactional
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model, @ModelAttribute("user") UserEntity user) {
		List<UserEntity> users = this.getUsers();				  
		model.addAttribute("users", users);
		return "user/index";
		
	}
	
	@RequestMapping(value="form", method = RequestMethod.GET) 
    public String index(ModelMap model) {
		model.addAttribute("user",new UserEntity());
          return "user/form";
    }
	
	/*@RequestMapping(value="form", method = RequestMethod.POST) 
    public String index1() {
          return "user/form";
    }*/
	@RequestMapping(value = "form", method = RequestMethod.POST )
	public String addUser(HttpServletRequest request, ModelMap model,@ModelAttribute("user") UserEntity user) {
		Integer temp = this.insertUser(user);
		if(temp != 0) {
			model.addAttribute("message","them thanh cong");
			user.setUsername(null);
			user.setPassword(null);
			user.setFullname(null);
		}else {
			model.addAttribute("message","them that bai");
		}
		List<UserEntity> users = this.getUsers();
		model.addAttribute("users", users);
		return"user/index";
	}
	
	public Integer insertUser(UserEntity user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			t.commit();
		}catch(Exception e) {
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
		return 1;
	} 

	
	@RequestMapping(value = "index/{Username}.htm", params = "linkDelete")
	public String deleteUser (HttpServletRequest request, ModelMap model, @ModelAttribute("user") UserEntity user,
			@PathVariable("Username") String username) {
		Integer temp = this.deleteUser(this.getUser(username));
		
		
		if(temp != 0) {
			model.addAttribute("message","Delete thành công");
		}
		else {
			model.addAttribute("message", "Delete không thành công");
		}
		List<UserEntity> users = this.getUsers();
		model.addAttribute("users", users);
		return"user/index";
	}
	public Integer deleteUser (UserEntity user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(user);
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

	/* phần chỉnh sửa */
	@RequestMapping(value = "form/{Username}", params = "linkEdit" )
	public String editUser (HttpServletRequest request, ModelMap model, @ModelAttribute("user") UserEntity user, 
			@PathVariable("Username") String username) {
		List<UserEntity> users = this.getUsers();
		model.addAttribute("users", users);
		model.addAttribute("user", this.getUser(username));
		model.addAttribute("btnupdate","true");
		return "user/form";
	}

	@RequestMapping(value = "form", params = "btnupdate" , method = RequestMethod.POST )
	public String edit_User(HttpServletRequest requets, ModelMap model, 
			@ModelAttribute("user") UserEntity user) {
		Integer temp = this.updateUser(user);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
			user.setUsername(null);
			user.setPassword(null);
			user.setFullname(null);
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");
		}
		List<UserEntity> users = this.getUsers();
		model.addAttribute("users", users);
		return "user/index";
	}
	
	public Integer updateUser(UserEntity user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
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

	public UserEntity getUser (String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserEntity where username =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		UserEntity list = (UserEntity) query.list().get(0);
		return list;
	}
	
	public List<UserEntity> getUsers(){
		Session session = factory.getCurrentSession();
		String hql = "FROM UserEntity as user order by user.username desc";
		Query query = session.createQuery(hql);
		List<UserEntity> list = query.list();
		return list;
	}
	
	
}