
import dao.BasicMapper;
import dao.StudentMapper;
import dao.SubjectMapper;
import entity.Student;
import entity.Subject;
import orm.MyORM;

public class Tester {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		MyORM orm = new MyORM();

		
		orm.scanEntities();
		StudentMapper sm = (StudentMapper) orm.getMapper(StudentMapper.class);
		
		//Step 1
//		orm.init();
//		for (int i = 0; i<10; i++)
//		{
//			Student s = new Student();
//			s.setAge(10+i);
//			s.setFirst("Test"+i);
//			s.setLast("Test"+i);
//			
//			sm.save(s);
//		}
		
		
		//Step 2
//		Student test = new Student();
//		test.setFirst("Gael");
//		test.setLast("Estrera");
//		test.setAge(21);
//		sm.save(test);
		
		//Step 3
//		SubjectMapper subm = (SubjectMapper) orm.getMapper(SubjectMapper.class);
//		Subject s = new Subject();
//		s.setName("Java");
//		s.setNumStudents(100);
//		subm.save(s);
		
		//Step 4
//		Student tobe = new Student();
//		tobe.setId(1);
//		sm.delete(tobe);
		
		
		//Step 5
//		System.out.println(sm.getById(4));
//		
//		System.out.println(sm.getAll());
//		
//		System.out.println(sm.getByFirstNameAndLastName("Test1", "Test1"));
//		
//		
		//Step 6
		SubjectMapper sbm = (SubjectMapper) orm.getMapper(SubjectMapper.class);
		
		Subject sb = new Subject();
		sb.setName("cs124");
		sb.setNumStudents(20);
		
		sbm.save(sb);

	}

}
