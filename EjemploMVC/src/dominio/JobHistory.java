package dominio;
// default package
// Generated 09-jun-2015 15:28:25 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * JobHistory generated by hbm2java
 */
public class JobHistory implements java.io.Serializable {

	private JobHistoryId id;
	private Departments departments;
	private Jobs jobs;
	private Employees employees;
	private Date endDate;

	public JobHistory() {
	}

	public JobHistory(JobHistoryId id, Jobs jobs, Employees employees,
			Date endDate) {
		this.id = id;
		this.jobs = jobs;
		this.employees = employees;
		this.endDate = endDate;
	}

	public JobHistory(JobHistoryId id, Departments departments, Jobs jobs,
			Employees employees, Date endDate) {
		this.id = id;
		this.departments = departments;
		this.jobs = jobs;
		this.employees = employees;
		this.endDate = endDate;
	}

	public JobHistoryId getId() {
		return this.id;
	}

	public void setId(JobHistoryId id) {
		this.id = id;
	}

	public Departments getDepartments() {
		return this.departments;
	}

	public void setDepartments(Departments departments) {
		this.departments = departments;
	}

	public Jobs getJobs() {
		return this.jobs;
	}

	public void setJobs(Jobs jobs) {
		this.jobs = jobs;
	}

	public Employees getEmployees() {
		return this.employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
