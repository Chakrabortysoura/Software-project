package com.first.software_project;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import jakarta.persistence.Query;
@SpringBootApplication
public class SoftwareProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(SoftwareProjectApplication.class, args);

		// Faculty teacher1=new Faculty();
		// teacher1.setfaculty_id(1);
		// teacher1.setname("SC");

		// Faculty teacher2=new Faculty();
		// teacher2.setfaculty_id(2);
		// teacher2.setname("PK");

		// Subjects s1=new Subjects();
		// s1.setsubject_code(1);
		// s1.setsubject_name("DBMS");

		// Subjects s2=new Subjects();
		// s2.setsubject_code(2);
		// s2.setsubject_name("LINUX");

		// Subjects s3=new Subjects();
		// s3.setsubject_code(3);
		// s3.setsubject_name("NETWORKING");

		// Scheduled_class class1=new Scheduled_class();
		// class1.setclass_id(1);
		// class1.setbatch("BCS3A");
		// class1.setday_of_week("THU");
		// class1.setteacher(teacher1);
		// class1.setstart(11);
		// class1.setend(13);
		// class1.settopic(s2);

		// Scheduled_class class2=new Scheduled_class();
		// class2.setclass_id(2);
		// class2.setbatch("BCS3B");
		// class2.setday_of_week("WED");
		// class2.setteacher(teacher1);
		// class2.setstart(11);
		// class2.setend(13);
		// class2.settopic(s1);
		
		// Scheduled_class class3=new Scheduled_class();
		// class3.setclass_id(3);
		// class3.setbatch("BCS3A");
		// class3.setday_of_week("THU");
		// class3.setteacher(teacher2);
		// class3.setstart(14);
		// class3.setend(15);
		// class3.settopic(s2);

		// Scheduled_class class4=new Scheduled_class();
		// class4.setclass_id(4);
		// class4.setbatch("BCS2B");
		// class4.setday_of_week("WED");
		// class4.setteacher(teacher2);
		// class4.setstart(10);
		// class4.setend(11);
		// class4.settopic(s1);
		//Rooms r1=new Rooms(105);
		//Rooms r2=new Rooms(200);
		
		// teacher1.setclass_list(class2);

		//Configuration config=new Configuration().configure("hibernate.cfg.xml");
		// class4.settopic(s3);
		// Rooms r1=new Rooms(105);
		// Rooms r2=new Rooms(200);
		
		// teacher1.setclass_list(class2);

		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Subjects.class);
		config.addAnnotatedClass(Faculty.class);
		config.addAnnotatedClass(Scheduled_class.class);
		config.addAnnotatedClass(Room_allocation.class);
		config.addAnnotatedClass(Rooms.class);
		config.addAnnotatedClass(Rooms.class);

		// Room_allocation allocation1=new Room_allocation();
		// allocation1.setdate(10);
		// allocation1.setassigned_class(class2);
		
		
		SessionFactory builder=config.buildSessionFactory();
		Session session1=builder.openSession();
		Transaction t1=session1.beginTransaction();

		// session1.persist(s1);
		// session1.persist(s2);
		// session1.persist(s3);
		// session1.persist(teacher1);
		// session1.persist(teacher2);
		// session1.persist(class1);
		// session1.persist(class2);
		// session1.persist(class3);
		// session1.persist(class4);
		// session1.persist(r1);
		// session1.persist(r2);
		
		// allocation_done search=new allocation_done();
		// search.setclass_id(2);
		// search.setdate(19);
		// allocation_done result=session1.get(allocation_done.class,search);

		// System.out.println("The search result: ");
		// System.out.println("Date: "+result.getdate()+" Class id: "+result.getclass_id()+" Assigned room:"+result.getroom_no());
		// System.out.println("Class id: "+search.getclass_id()+" room_no: "+search.getroom_no());
		// result=session1.get(room_allocation.class,20);
		// System.out.println("The result will be for the search query: ");
		// System.out.println("Day of the week: "+result.getassigned_class().getday_of_week()+"Batch: "+result.getassigned_class().getbatch());
		
		t1.commit();
		session1.close();
		
	}

}
