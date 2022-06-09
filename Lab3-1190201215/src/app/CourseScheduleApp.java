package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import check.PeriodicConflictException;
import set.IntervalSet;
import l.Course;
import specific.CourseIntervalSet;

public class CourseScheduleApp {

	private static final long[][] dayOfMonth = { { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
			{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
	private static final Scanner input = new Scanner(System.in);
	private static final Map<String, Course> courseMap = new HashMap<>();
	private static final Map<String, Integer> courseTime = new HashMap<>();

	private static int readNum() {
		String str = input.next();
		while (!str.matches("[0-9]+")) {
			System.err.println("����Ƿ������������룺");
			str = input.next();
		}
		return Integer.parseInt(str);
	}

	private static long timeInput(String S) {
		System.out.println(S);
		System.out.print("��������ݣ�");
		int year = readNum();

		System.out.print("�������·ݣ�");
		int month = readNum();
		while (month < 0 || month > 12) {
			System.err.println("���·ݲ����ڣ����������룺");
			month = readNum();
		}

		System.out.print("���������ڣ�");
		int day = readNum();
		if (year % 4 == 0) {
			while (day > dayOfMonth[0][month - 1] || day <= 0) {
				System.err.println("�����������·ݲ�ƥ�䣩�����������룺");
				day = readNum();
			}
		} else {
			while (day > dayOfMonth[1][month - 1] || day <= 0) {
				System.err.print("�����������·ݲ�ƥ�䣩�����������룺\n");
				day = readNum();
			}
		}
		return timeToLong(year, month, day);
	}

	private static long timeToLong(int year, int month, int day) {
		long sum = 0;
		sum += year * 365L + (year + 3) / 4;
		for (int i = 0; i < month - 1; i++) {
			if (year % 4 == 0)
				sum += dayOfMonth[0][i];
			else
				sum += dayOfMonth[1][i];
		}
		sum += day;
		return sum;
	}

	private static int menu() {
		System.out.print("�˵���\n1.����һ��γ�\n2.�ֶ�ѡ��γ̡��Ͽ�ʱ��\n" + "3.�鿴�γ̰������\n4.�鿴ĳ��Ŀα�\n�����������˳�\n");
		return readNum();
	}

	private static void insertCourse() {
		System.out.println("������γ�ID��");
		String courseID = input.next();
		System.out.println("������γ����ƣ�");
		String courseName = input.next();
		System.out.println("�������ʦ���֣�");
		String teacherName = input.next();
		System.out.println("�������Ͽεص㣺");
		String place = input.next();
		System.out.println("������ÿ��ѧʱ����ż������");
		int timeOfWeek = Integer.parseInt(input.next());
		Course course = new Course(courseID, courseName, teacherName, place, timeOfWeek);
		courseMap.put(courseID, course);
		courseTime.put(courseID, 0);
	}

	private static void arrangeCourse(CourseIntervalSet<Course> courseIntervalSet) throws PeriodicConflictException {
		System.out.println("������Ҫ���ŵĿγ�ID");
		String id = input.next();
		Course target = null;

		if (!courseMap.containsKey(id))
			System.out.println("δ�ҵ���ؿγ�ID");

		else {
			target = courseMap.get(id);
			if (courseTime.get(id) == target.getTimeOfWeek()) {
				System.out.println("�γ̰���ʱ������");
				return;
			}
			System.out.println("������Ҫ���γ̰��ŵ�λ�ã���һ�ܷ�Ϊ35��ʱ��Σ���");
			int w = readNum();
			while (w < 1 || w > 35) {
				System.out.println("����������������룺");
				w = readNum();
			}
            for (Course course : courseIntervalSet.labels()){
                for (int i = 0; i < courseIntervalSet.intervals(course).labels().size(); i++) {
                    if (w == courseIntervalSet.intervals(course).start(i)){
                        System.out.println("����ʧ�ܣ���ʱ������пγ�");
                        return;
                    }
                }
            }		
			courseIntervalSet.Insert(w, w + 1, target);
			courseTime.put(id, courseTime.get(id) + 2);
		}
	}

	public static int timeChange(long i) {
		switch ((int) i) {
		case 1:
			return 8;
		case 2:
			return 10;
		case 3:
			return 13;
		case 4:
			return 15;
		case 5:
			return 19;
		}
		return -1;
	}

	public static void main(String[] args) throws PeriodicConflictException {
		long start = timeInput("������ѧ�ڿ�ʼʱ�䣺");
		System.out.println("��������������");
		int length = readNum();
		CourseIntervalSet<Course> courseIntervalSet = new CourseIntervalSet<>(start, 7L * length);
		while (true) {
			switch (menu()) {
			case 1:
				insertCourse();
				break;
			case 2:
				arrangeCourse(courseIntervalSet);
				break;
			case 3:
                System.out.println("δ�����ŵĿγ��У�");
                for (String ID : courseTime.keySet()){
                    if (courseTime.get(ID) == 0){
                        Course c = courseMap.get(ID);
                        System.out.println(c.getCourseName()
                                + "(" + c.getCourseID() + ")"
                                + ", " + c.getTeacherName() + ", "
                                + c.getPlace());
                    }
                }
                int []exist = new int[36];
                for (Course c : courseIntervalSet.labels()) {
                    IntervalSet<Integer> tempInterval = courseIntervalSet.intervals(c);
                    for (int i = 0; i < tempInterval.labels().size(); i++){
                        exist[(int) tempInterval.start(i)]++;
                    }
                }
                int sum = 0;
                int sum1 = 0;
                for (int i = 1; i < 36; i++) {
                    if (exist[i] == 0) sum++;
                    else if(exist[i] > 1) sum1++;
                }
                System.out.println("����ʱ�����Ϊ��" + sum + "/" + 35);
                System.out.println("�ظ�ʱ�����Ϊ��" + sum1 + "/" + 35);
                break;
			case 4:
				long targetDay = timeInput("������Ҫ��ѯ�����ڣ�");
				if (targetDay > start + 7L * length) {
					System.out.println("����ʱ�䲻�ڱ�ѧ�ڷ�Χ��");
					break;
				} else {
					System.out.println("��������Ŀγ̺�ʱ���Ϊ��");
					int dayOfWeek = (int) ((targetDay - start) % 7);
					for (Course c : courseIntervalSet.labels()) {
						IntervalSet<Integer> tmpInterval = courseIntervalSet.intervals(c);
						for (int i = 0; i < tmpInterval.labels().size(); i++) {
							if (tmpInterval.start(i) > 5L * dayOfWeek && tmpInterval.start(i) <= 5L * dayOfWeek + 5) {
								System.out.println(c.getCourseName() + "{" + c.getCourseID() + "}" + "		"
										+ c.getTeacherName() + "		" + c.getPlace() + "		"
										+ timeChange(tmpInterval.start(i) - dayOfWeek) + ":00" + " ~ "
										+ (timeChange(tmpInterval.start(i) -5 * dayOfWeek)+2) + ":00");
							}
						}
					}
				}
				break;
			default:
				System.exit(0);
			}
		}
	}
}
