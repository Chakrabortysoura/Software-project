package com.first.software_project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoftwareProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftwareProjectApplication.class, args);


		Faculty teacher1=new Faculty();
		teacher1.setfaculty_id(1);
		teacher1.setname("SC");

		Subjects s1=new Subjects();
		s1.setsubject_code(1);
		s1.setsubject_name("DBMS");

		Subjects s2=new Subjects();
		s2.setsubject_code(2);
		s2.setsubject_name("LINUX");

		Scheduled_class class1=new Scheduled_class();
		class1.setbatch("BCS3B");
		class1.setday_of_week("WED");
		class1.setteacher(teacher1);
		class1.settopic(s1);
		
		teacher1.setclass_list(class1);

		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Subjects.class);
		config.addAnnotatedClass(Faculty.class);
		config.addAnnotatedClass(Scheduled_class.class);

		SessionFactory builder=config.buildSessionFactory();
		Session session1=builder.openSession();
		Transaction t1=session1.beginTransaction();

		// session1.persist(s1);
		// session1.persist(s2);
		// session1.persist(class1);
		// session1.persist(teacher1);
		Faculty result=session1.get(Faculty.class,1);

		t1.commit();
		session1.close();
		
		System.out.println("The result will be: \n");
		for(Scheduled_class i:teacher1.getclass_list()){
			System.out.println("Baych: "+i.getbatch()+"Day: "+i.getday_of_week());
		}
		
	}

}
