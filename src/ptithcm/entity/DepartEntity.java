package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Departs")
public class DepartEntity {
	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Name")
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<StaffEntity> getStaffs() {
		return staffs;
	}

	public void setStaffs(Collection<StaffEntity> staffs) {
		this.staffs = staffs;
	}

	@OneToMany (mappedBy = "depart", fetch = FetchType.EAGER)
	private Collection<StaffEntity> staffs;
	
}