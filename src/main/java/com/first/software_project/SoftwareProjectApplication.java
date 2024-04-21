package com.first.software_project;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SoftwareProjectApplication {
	// public static List<Rooms> get_room_list(Session s){
	// 	return s.get
	// }
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
		class1.setclass_id(1);
		class1.setbatch("BCS3B");
		class1.setday_of_week("WED");
		class1.setteacher(teacher1);
		class1.settopic(s1);
		
		Rooms r1=new Rooms(105);
		Rooms r2=new Rooms(200);
		
		teacher1.setclass_list(class1);
		Room_allocation result;

		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Subjects.class);
		config.addAnnotatedClass(Faculty.class);
		config.addAnnotatedClass(Scheduled_class.class);
		config.addAnnotatedClass(Room_allocation.class);
		config.addAnnotatedClass(Rooms.class);

		Room_allocation allocation1=new Room_allocation();
		allocation1.setdate(20);
		allocation1.setassigned_class(class1);
		
		
		SessionFactory builder=config.buildSessionFactory();
		Session session1=builder.openSession();
		Transaction t1=session1.beginTransaction();

		// session1.persist(s1);
		// session1.persist(s2);
		// session1.persist(class1);
		// session1.persist(teacher1);
		// session1.persist(allocation1);
		// session1.persist(r1);

		// result=session1.get(Faculty.class,1);
		// System.out.println("The result will be: \n");
		// for(Scheduled_class i:result.getclass_list()){
		// 	System.out.println("Batch: "+i.getbatch()+" Day of the week: "+i.getday_of_week());
		// }

		// result=session1.get(room_allocation.class,20);
		// System.out.println("The result will be for the search query: ");
		// System.out.println("Day of the week: "+result.getassigned_class().getday_of_week()+"Batch: "+result.getassigned_class().getbatch());
		
		t1.commit();
		session1.close();
		
	}

}
