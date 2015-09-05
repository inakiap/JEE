package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import dominio.Employees;

public class EmpHiberDAO implements Recuperable {

	public Object leerEmpleado(int n) {
		SessionFactory sesion_factory = null;
		Session s = null;
		Employees emp = null;
		Transaction t = null;
		
		try {
			Configuration configuration = new Configuration().configure();
	    	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	    	sesion_factory = configuration.buildSessionFactory(builder.build());
	    	s = sesion_factory.openSession();
	    	t = s.beginTransaction();
			emp = (Employees) s.get(Employees.class, n);
			
			t.commit();
			
			}
		 catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		}
		finally{
			s.close();
			sesion_factory.close();
		}
		return emp;
	}
}
