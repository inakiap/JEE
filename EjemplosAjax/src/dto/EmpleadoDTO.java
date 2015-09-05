package dto;


public class EmpleadoDTO {
	private int id;				//1
	private String f_name;			//2
	private String l_name;			//3
	private String email;			//4
	private String n_tlf;			//5
	private String h_date;			//6
	private String job_id;			//7
	private String salary;			//8
	private String commission_pct;	//9
	private String mngr_id;			//10
	private String dpt_id;			//11
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getN_tlf() {
		return n_tlf;
	}
	public void setN_tlf(String n_tlf) {
		this.n_tlf = n_tlf;
	}
	public String getH_date() {
		return h_date;
	}
	public void setH_date(String h_date) {
		this.h_date = h_date;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getCommission_pct() {
		return commission_pct;
	}
	public void setCommission_pct(String commission_pct) {
		this.commission_pct = commission_pct;
	}
	public String getMngr_id() {
		return mngr_id;
	}
	public void setMngr_id(String mngr_id) {
		this.mngr_id = mngr_id;
	}
	public String getDpt_id() {
		return dpt_id;
	}
	public void setDpt_id(String dpt_id) {
		this.dpt_id = dpt_id;
	}
	public EmpleadoDTO(int id, String f_name, String l_name, String email,
			String n_tlf, String h_date, String job_id, String salary,
			String commission_pct, String mngr_id, String dpt_id) {
		super();
		this.id = id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.email = email;
		this.n_tlf = n_tlf;
		this.h_date = h_date;
		this.job_id = job_id;
		this.salary = salary;
		this.commission_pct = commission_pct;
		this.mngr_id = mngr_id;
		this.dpt_id = dpt_id;
	}
	
	
	public EmpleadoDTO(int id, String f_name, String l_name, String salary) {
		super();
		this.id = id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.salary = salary;
	}
	public EmpleadoDTO (){
		
	}
	@Override
	public String toString() {
		return " Nombre = " + f_name + "| Apellido = " + l_name + "| Salario = " + salary + "\n";
	}
	
}
