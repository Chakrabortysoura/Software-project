package com.first.software_project;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.Query;


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

		Scheduled_class class2=new Scheduled_class();
		class2.setclass_id(2);
		class2.setbatch("BCS3C");
		class2.setday_of_week("THU");
		class2.setteacher(teacher1);
		class2.settopic(s1);
		
		Rooms r1=new Rooms(105);
		Rooms r2=new Rooms(200);
		
		teacher1.setclass_list(class2);

		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Subjects.class);
		config.addAnnotatedClass(Faculty.class);
		config.addAnnotatedClass(Scheduled_class.class);
		config.addAnnotatedClass(Room_allocation.class);
		config.addAnnotatedClass(Rooms.class).addAnnotatedClass(allocation_done.class);

		Room_allocation allocation1=new Room_allocation();
		allocation1.setdate(19);
		allocation1.setassigned_class(class2);
		
		
		SessionFactory builder=config.buildSessionFactory();
		Session session1=builder.openSession();
		Transaction t1=session1.beginTransaction();

		// session1.persist(s1);
		// session1.persist(s2);
		// session1.persist(class2);
		// session1.persist(teacher1);
		// session1.persist(allocation1);
		// session1.persist(r1);
		
		// allocation_done search=session1.get(allocation_done.class, 152);
		NativeQuery<Room_allocation> search=session1.createNativeQuery("select * from Room_allocation where assigned_class_class_id= :class_id",Room_allocation.class);
		search.setParameter("class_id", 3);
		List<Room_allocation> result=search.getResultList();
		System.out.println("The result will be: \n");
		if(result.size()==0){
			System.out.println("The reuslt is empty");
		}
		for(Room_allocation i:result){
			System.out.println("Date: "+i.getdate()+" Class id: "+i.getassigned_class().getclass_id());
		}
		
		// System.out.println("Class id: "+search.getclass_id()+" room_no: "+search.getroom_no());
		// result=session1.get(room_allocation.class,20);
		// System.out.println("The result will be for the search query: ");
		// System.out.println("Day of the week: "+result.getassigned_class().getday_of_week()+"Batch: "+result.getassigned_class().getbatch());
		
		t1.commit();
		session1.close();
		
	}

}
