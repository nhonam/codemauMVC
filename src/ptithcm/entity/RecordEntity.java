package ptithcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Records")
public class RecordEntity {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Long id;
	@Column(name = "Type")
	private Boolean type;
	@Column(name = "Reason")
	private String reason;
	@Column(name = "Date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date date;
	@ManyToOne
	@JoinColumn(name = "StaffId")
	private StaffEntity staffs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getType() {
		return type;
	}
	public void setType(Boolean type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public StaffEntity getStaffs() {
		return staffs;
	}
	public void setStaffs(StaffEntity staffs) {
		this.staffs = staffs;
	}
	
	
}