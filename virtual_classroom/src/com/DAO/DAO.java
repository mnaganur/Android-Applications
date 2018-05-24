package com.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.Faculty;
import com.bean.Login;
import com.bean.Question;
import com.bean.Student;

public class DAO 
{
	public boolean addStudent(Student std) 
	{
		int i = 0;
		System.out.println("hello");
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();
		Login l = new Login();
		l.setUserName(std.getUserName());
		l.setPassword(std.getDesiredPassword());
		l.setRole("s");
		std.setLogin(l);
		i = (Integer) session.save(std);

		t.commit();

		session.close();
		sessionFactory.close();
		if (i != 0)
			return true;
		else
			return false;

	}

	public boolean addFaculty(Faculty fac) 
	{
		System.out.println("hello");
		int i = 0;
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();
		Query qry=session.createQuery("Select facultyID from Faculty where classroomID=?");
		qry.setString(0, fac.getClassroomID());
		List<Integer> list=qry.list();
		if(list.isEmpty()){
		i = (Integer) session.save(fac);
		}

		t.commit();

		session.close();
		sessionFactory.close();
		if (i != 0)
			return true;
		else
			return false;

	}

	public String verifyLogin(Login l) 
	{
		int i = 0;
		String role="fail";
		System.out.println("hello");
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();

		/*Login login = (Login) session.get(Login.class, l.getUserName());*/
		Query qry=session.createQuery("from Login where userName=? AND password=?");
		qry.setString(0, l.getUserName());
		qry.setString(1, l.getPassword());
		List<Login> list=qry.list();
		if(list.isEmpty())
		 role="fail";
		else{
			
		
		
				 role = list.get(0).getRole();
			}
		
		

		t.commit();

		session.close();
		sessionFactory.close();
		return role;

	}

	public Student viewProfile(String userName) 
	{
		System.out.println("hello");
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();

		Query qry = session.createQuery("select studentID,userName,classroomID,emailID,facultyID,phoneNO from Student where userName=?");
		qry.setString(0, userName);
		Iterator<Object[]> itr = qry.list().iterator();
		Student s = new Student();
		while (itr.hasNext()) {

			Object[] o = itr.next();
			s.setStudentID((Integer) o[0]);
			s.setUserName((String) o[1]);

			s.setClassroomID((String) o[2]);
			s.setEmailID((String) o[3]);
			s.setFacultyID((Integer) o[4]);
			s.setPhoneNO((String) o[5]);
		}

		t.commit();
		
		session.close();
		sessionFactory.close();
		return s;

	}

	public Faculty viewFaculty(String userName) 
	{
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		System.out.println(userName);
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Query qry = session.createQuery("select facultyID, userName,classroomID,emailID,phoneNO from Faculty where userName=?");
		qry.setString(0, userName);

		Iterator<Object[]> itr = qry.list().iterator();
		Faculty fac = new Faculty();

		while (itr.hasNext()) 
		{
			Object[] o = itr.next();
			fac.setFacultyID((Integer) o[0]);
			fac.setUserName((String) o[1]);
			System.out.println(userName);
			fac.setClassroomID((String) o[2]);
			fac.setEmailID((String) o[3]);
			fac.setPhoneNO((String) o[4]);
		}

		t.commit();
		session.close();
		sessionFactory.close();
		return fac;

	}

	public boolean updateStudent(int studentID, Student std) 
	{
		int i = 0;
		System.out.println("hello");
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();

		Query qry = session.createQuery("update Student set emailID=?,classroomID=?,facultyID=?,phoneNO=? where studentID=?");

		qry.setString(0, std.getEmailID());
		qry.setString(1, std.getClassroomID());
		qry.setInteger(2, std.getFacultyID());
		qry.setString(3, std.getPhoneNO());

		qry.setInteger(4, studentID);
		int x = qry.executeUpdate();
		System.out.println(x);
		t.commit();
		/*t.begin();
		
		Query qry1 = session.createQuery("select userName,desiredPassword from Student where studentID=?");
		qry.setInteger(0, studentID);
		List<Object[]> ul=qry.list();
		Login l=new Login();
		l.setUserName((String) ul.get(0)[0]);
		l.setPassword((String) ul.get(0)[1]);
		l.setRole("s");
		System.out.println(l.getUserName());
		session.save(l);
		t.commit();*/
		session.close();
		sessionFactory.close();
		if (i != 0)
			return true;
		else
			return false;

	}

	public String getUsername(int studentID) 
	{
		System.out.println("hello");
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();

		Query qry = session.createQuery("select userName from Student where studentID=?");
		qry.setInteger(0, studentID);
		Iterator<String> itr = qry.list().iterator();

		String username = null;
		while (itr.hasNext()) 
		{
			username = itr.next();
		}

		t.commit();

		session.close();
		sessionFactory.close();
		return username;

	}

	public String getFacultyname(int facultyID) 
	{
		System.out.println("hello");
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();

		Query qry = session.createQuery("select userName from Faculty where facultyID=?");
		qry.setInteger(0, facultyID);
		Iterator<String> itr = qry.list().iterator();

		String username = null;
		while (itr.hasNext()) 
		{
			username = itr.next();
		}

		t.commit();

		session.close();
		sessionFactory.close();
		return username;

	}

	public boolean editFaculty(int facultyID, Faculty fac) 
	{
		int i = 0;
		System.out.println("hello");
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();

		Query qry = session.createQuery("update Faculty set userName=?, classroomID=?,emailID=?,phoneNO=? where facultyID=?");

		qry.setString(0, fac.getUserName());
		qry.setString(1, fac.getClassroomID());
		qry.setString(2, fac.getEmailID());
		qry.setString(3, fac.getPhoneNO());
		qry.setInteger(4, facultyID);
		int x = qry.executeUpdate();
		System.out.println(x);
		t.commit();

		session.close();
		sessionFactory.close();
		if (i != 0)
			return true;
		else
			return false;

	}
	
	public ArrayList<Question> viewQ(){
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();


			
		Query qry=session.createQuery("from Question ORDER BY questID");
		ArrayList<Question> qlist=(ArrayList<Question>) qry.list();
			
		t.commit();
		
		session.close();
		sessionFactory.close();
		return qlist;
	}
	
	public boolean deleteStudent(int studentID)
	{
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();
		
		Query qry = session.createQuery("select userName from Student where studentID=?");
		qry.setInteger(0, studentID);
		Iterator<String> itr = qry.list().iterator();

		String username = null;
		while (itr.hasNext()) 
		{
			username = itr.next();
		}

		
	Query qry1 = session.createQuery("delete from Login where userName=?");
		qry1.setString(0, username);		
		
		int i1=qry1.executeUpdate();
		
		
		Query qry2 = session.createQuery("delete from Student where studentID=?");
		qry2.setInteger(0, studentID);
		int i2=qry2.executeUpdate();
		
		t.commit();

		session.close();
		sessionFactory.close();
		if ((i2!=0)&&(i1!=0))
			return true;
		else
			return false;
	}
	
	public boolean deleteFaculty(int facultyID)
	{
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();
		
		Query qry = session.createQuery("select userName from Faculty where facultyID=?");
		qry.setInteger(0, facultyID);
		Iterator<String> itr = qry.list().iterator();

		String username = null;
		while (itr.hasNext()) 
		{
			username = itr.next();
		}

		
	Query qry1 = session.createQuery("delete from Login where userName=?");
		qry1.setString(0, username);		
		
		int i1=qry1.executeUpdate();
		
		
		Query qry2 = session.createQuery("delete from Faculty where facultyID=?");
		qry2.setInteger(0, facultyID);
		int i2=qry2.executeUpdate();
		
		t.commit();

		session.close();
		sessionFactory.close();
		if ((i2!=0)&&(i1!=0))
			return true;
		else
			return false;
	}

	
	public List<Faculty> viewFacultyList()
	{
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();


			
		Query qry=session.createQuery("select userName, classroomID,emailID,phoneNO, facultyID from Faculty ORDER BY facultyID");
	
		Iterator<Object[]> itr=qry.list().iterator();
		List<Faculty> facultyList = new ArrayList<Faculty>();
		while(itr.hasNext())
		{
			Faculty f = new Faculty();
			Object[] o = itr.next();
			f.setUserName((String) o[0]);
			f.setClassroomID((String) o[1]);
			f.setEmailID((String) o[2]);
			f.setPhoneNO((String) o[3]);
			f.setFacultyID((Integer) o[4]);
			
			facultyList.add(f);
		}
		
			
		t.commit();
		
		session.close();
		
		return facultyList;
	}
	
	public List<Student> viewStudentList()
	{
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();


			
		Query qry=session.createQuery("select userName, classroomID,emailID,phoneNO, studentID from Student ORDER BY studentID");
	
		Iterator<Object[]> itr=qry.list().iterator();
		List<Student> stuList = new ArrayList<Student>();
		while(itr.hasNext())
		{
			Student s = new Student();
			Object[] o = itr.next();
			s.setUserName((String) o[0]);
			s.setClassroomID((String) o[1]);
			s.setEmailID((String) o[2]);
			s.setPhoneNO((String) o[3]);
			s.setStudentID((Integer) o[4]);
			
			stuList.add(s);
		}
		
			
		t.commit();
		
		session.close();
		
		return stuList;
	}
	public int facID(String classid){
		int id=0;
		
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();


			
		Query qry=session.createQuery("Select facultyID from Faculty where classroomID=?");
		qry.setString(0, classid);
		List<Integer> list=qry.list();
		id=list.get(0);
		
			
		t.commit();
		
		session.close();
		sessionFactory.close();
	
		return id;
	}
	
	public String getUserRole(String name)
	{
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		t.begin();


			
		Query qry=session.createQuery("select role from Login where userName=?");
		qry.setString(0, name);
		Iterator<String> itr=qry.list().iterator();
		String role=null;
		while(itr.hasNext())
		{
			role=itr.next();
		}
		
			
		t.commit();
		
		session.close();
		
		return role;
	}
}

