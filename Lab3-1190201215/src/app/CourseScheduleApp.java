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
			System.err.println("输入非法，请重新输入：");
			str = input.next();
		}
		return Integer.parseInt(str);
	}

	private static long timeInput(String S) {
		System.out.println(S);
		System.out.print("请输入年份：");
		int year = readNum();

		System.out.print("请输入月份：");
		int month = readNum();
		while (month < 0 || month > 12) {
			System.err.println("该月份不存在，请重新输入：");
			month = readNum();
		}

		System.out.print("请输入日期：");
		int day = readNum();
		if (year % 4 == 0) {
			while (day > dayOfMonth[0][month - 1] || day <= 0) {
				System.err.println("天数错误（与月份不匹配），请重新输入：");
				day = readNum();
			}
		} else {
			while (day > dayOfMonth[1][month - 1] || day <= 0) {
				System.err.print("天数错误（与月份不匹配），请重新输入：\n");
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
		System.out.print("菜单：\n1.增加一组课程\n2.手动选择课程、上课时间\n" + "3.查看课程安排情况\n4.查看某天的课表\n输入其他：退出\n");
		return readNum();
	}

	private static void insertCourse() {
		System.out.println("请输入课程ID：");
		String courseID = input.next();
		System.out.println("请输入课程名称：");
		String courseName = input.next();
		System.out.println("请输入教师名字：");
		String teacherName = input.next();
		System.out.println("请输入上课地点：");
		String place = input.next();
		System.out.println("请输入每周学时数（偶数）：");
		int timeOfWeek = Integer.parseInt(input.next());
		Course course = new Course(courseID, courseName, teacherName, place, timeOfWeek);
		courseMap.put(courseID, course);
		courseTime.put(courseID, 0);
	}

	private static void arrangeCourse(CourseIntervalSet<Course> courseIntervalSet) throws PeriodicConflictException {
		System.out.println("请输入要安排的课程ID");
		String id = input.next();
		Course target = null;

		if (!courseMap.containsKey(id))
			System.out.println("未找到相关课程ID");

		else {
			target = courseMap.get(id);
			if (courseTime.get(id) == target.getTimeOfWeek()) {
				System.out.println("课程安排时间已满");
				return;
			}
			System.out.println("请输入要将课程安排的位置（将一周分为35个时间段）：");
			int w = readNum();
			while (w < 1 || w > 35) {
				System.out.println("输入错误，请重新输入：");
				w = readNum();
			}
            for (Course course : courseIntervalSet.labels()){
                for (int i = 0; i < courseIntervalSet.intervals(course).labels().size(); i++) {
                    if (w == courseIntervalSet.intervals(course).start(i)){
                        System.out.println("安排失败，该时间段已有课程");
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
		long start = timeInput("请输入学期开始时间：");
		System.out.println("请输入总周数：");
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
                System.out.println("未被安排的课程有：");
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
                System.out.println("空闲时间比例为：" + sum + "/" + 35);
                System.out.println("重复时间比例为：" + sum1 + "/" + 35);
                break;
			case 4:
				long targetDay = timeInput("请输入要查询的日期：");
				if (targetDay > start + 7L * length) {
					System.out.println("输入时间不在本学期范围内");
					break;
				} else {
					System.out.println("当天包含的课程和时间段为：");
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
