package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import l.Employee;
import check.IntervalConflictException;
import specific.DutyIntervalSet;

public class DutyRosterApp {

	private final static long[][] dayOfMonth = { { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
			{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
	private final static Scanner input = new Scanner(System.in);
	private final static Map<Employee, Integer> People = new HashMap<>();
	static long begin = 0;
	static long end = 0;
	static long totalLength = 0;
	static DutyIntervalSet<Employee> dutyInterval = null;

	private static int menu() {
		System.out.print("�˵���\n1.�Զ��Ű�\n2.�ֶ��Ű�\n" + "3.����Ű����\n4.ɾ��Ա���Ű�\n5.ɾ��Ա����Ϣ\n" + "6.չʾ�Ű��\n�����������˳�\n");
		return readNum();
	}

	private static int readNum() {
		String str = input.next();
		while (!str.matches("[0-9]+")) {
			System.err.println("����Ƿ������������룺");
			str = input.next();
		}
		return Integer.parseInt(str);
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

	private static int getYear(long sum) {
		if (sum % (365 * 3 + 366) == 0)
			return (int) (sum / (365 * 3 + 366)) * 4 - 1;
		int year = (int) (sum / (365 * 3 + 366)) * 4;
		int dayCount = (int) (sum % (365 * 3 + 366));
		if (dayCount > 731 && dayCount <= 1096) {
			year += 2;
		} else if (dayCount > 366 && dayCount <= 731) {
			year += 1;
		} else if (dayCount > 366 + 365 * 2) {
			year += 3;
		}
		return year;
	}

	private static int getMonth(long sum) {

		int monthCount = 0;
		int month = 0;
		int year = getYear(sum);
		int sum1 = year * 365 + (year + 3) / 4;
		int minus = (int) (sum - sum1);

		for (int i = 0; i < 12; i++) {
			if (year % 4 == 0)
				monthCount += dayOfMonth[0][i];
			else
				monthCount += dayOfMonth[1][i];
			month += 1;
			if (monthCount >= minus) {
				break;
			}
		}
		return month;
	}

	private static int getDay(long sum) {
		int year = getYear(sum);
		int month = getMonth(sum);
		int s = 0;
		s += year * 365 + (year + 3) / 4;
		for (int i = 0; i < month - 1; i++) {
			if (year % 4 == 0)
				s += dayOfMonth[0][i];
			else
				s += dayOfMonth[1][i];
		}
		return (int) (sum - s);
	}

	private static void showPeople(Map<Employee, Integer> people, int choice) {
		if (choice == 0)
			System.out.print("�ɰ�����Ա���£�\n");
		else {
			System.out.print("�Ѱ�����Ա���£�\n");
		}
		for (Employee E : people.keySet()) {
			if (people.get(E).equals(choice)) {
				String s = E.getName() + "[" + E.getJob() + "]" + "\n";
				System.out.print(s);
			}
		}
	}

	private static void readFile(int choose) {
		String fileName = "src/APP/text/test" + choose + ".txt";
		File file = new File(fileName);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.err.print("�ļ���ʧ��\n");
			System.exit(0);
		}
		BufferedReader buffReader = new BufferedReader(reader);
		String line = "";
		try {
			while ((line = buffReader.readLine()) != null) {
				checkLine(line);
			}
		} catch (IOException e) {
			System.err.print("�ļ���ʧ��\n");
			System.exit(0);
		}

	}

	private static void checkLine(String str) {
		// ȡ��ʱ����Ϣ
		if (str.startsWith("Period{")) {
			String time = str.substring(7, 17);
			begin = checkFormat(time);
			String next = str.substring(str.length() - 11, str.length() - 1);
			end = checkFormat(next);
			totalLength = end - begin + 1;
			dutyInterval = new DutyIntervalSet<>(begin, end);
		} else if (str.startsWith("Employee{")) {

		} else if (str.startsWith("Roster{")) {

		} else if (str.startsWith("}")) {

		} else {
			// �����������
			String reg1 = "\\d{4}-\\d{2}-\\d{2},\\d{4}-\\d{2}-\\d{2}";
			Pattern pattern1 = Pattern.compile(reg1);
			Matcher matcher1 = pattern1.matcher(str);

			// �����������
			String reg2 = "\\d{3}-\\d{4}-\\d{4}";
			Pattern pattern2 = Pattern.compile(reg2);
			Matcher matcher2 = pattern2.matcher(str);
			if (matcher1.find()) {
				String msg = matcher1.group();
				long startTime = checkFormat(msg.substring(0, 10));
				long endTime = checkFormat(msg.substring(11, 21));
				String reg3 = "[a-zA-Z]+";
				// ����
				Pattern pattern3 = Pattern.compile(reg3);
				Matcher matcher3 = pattern3.matcher(str);
				if (matcher3.find()) {
					String name = matcher3.group();
					boolean flag = false;
					for (Employee E : People.keySet()) {
						if (E.getName().equals(name)) {
							flag = true;
							try {
								dutyInterval.Insert(startTime, endTime, E);
							} catch (IntervalConflictException e) {
								System.err.print("�Ű���" + E.getName() + "ʱ�����ظ�\n");
								System.exit(0);
							}
							People.put(E, 1);
							break;
						}
					}
					if (!flag) {
						System.err.print("û����Ϊ��" + name + "����\n");
						System.exit(0);
					}
				}
			} else if (matcher2.find()) {
				String msg1 = matcher2.group();
				String reg4 = "[a-zA-Z ]+";
				Pattern pattern4 = Pattern.compile(reg4);
				Matcher matcher4 = pattern4.matcher(str);
				String name = null, job = null;
				if (matcher4.find()) {
					name = matcher4.group();
					if (str.charAt(1 + name.length()) != '{') {
						System.err.print("�����к��з���ĸ�ķ��Ż�����\n");
						System.exit(0);
					}
					for (Employee E : People.keySet()) {
						if (E.getName().equals(name)) {
							System.err.print("���ظ�����\n");
							System.exit(0);
						}
					}
				}
				if (matcher4.find()) {
					job = matcher4.group();
				}
				Employee newEmployee = new Employee(name, job, msg1);
				People.put(newEmployee, 0);
			} else {
				System.err.print("����Ƿ����ֻ��Ż����ڰ���ʱδ��yyyy-mm-dd��ʽ��ʾ\n");
				System.exit(0);
			}
		}
	}

	public static long checkFormat(String str) {
		if (str.charAt(4) != '-' || str.charAt(7) != '-') {
			System.err.print("ʱ�䲻Ϊ��Ӧ��ʽ yyyy-mm-dd\n");
			System.exit(0);
		}
		int month = Integer.parseInt(str.substring(5, 7));
		int day = Integer.parseInt(str.substring(8, 10));
		int year = Integer.parseInt(str.substring(0, 4));
		if (month <= 0 || month > 12) {
			System.err.print("�����ڸ���\n");
			System.exit(0);
		}
		if (year % 4 == 0) {
			if (day > dayOfMonth[0][month - 1] || day <= 0) {
				System.err.print("����²���Ϊ������\n");
				System.exit(0);
			}
		} else {
			if (day > dayOfMonth[1][month - 1] || day <= 0) {
				System.err.print("����²���Ϊ������\n");
				System.exit(0);
			}
		}
		return timeToLong(year, month, day);
	}

	public static void main(String[] args) {

		System.out.print("1.�ֶ�¼����Ϣ\n2.���ļ��ж�����Ϣ\n");
		int ch = readNum();
		while (ch <= 0 || ch >= 3) {
			System.out.print("����Ƿ������������룺");
			ch = readNum();
		}
		if (ch == 2) {
			System.out.print("�������ļ���ţ�1-8����");
			int text = readNum();
			while (text <= 0 || text >= 9) {
				System.out.print("����Ƿ������������룺");
				text = readNum();
			}
			readFile(text);
			System.out.print("�����ļ��ɹ���");
		}

		else {
			boolean flag = true;
			while (flag) {
				begin = timeInput("��ʼʱ�䣺");
				end = timeInput("����ʱ�䣺");
				if (begin < end)
					flag = false;
				else
					System.err.println("���뿪ʼʱ�����С�ڽ���ʱ�䣬��������");
			}
			long period = end - begin;

			System.out.println("����������");
			int number = readNum();
			for (int i = 0; i < number; i++) {
				System.out.print("������������");
				String name = input.next();
				System.out.print("������ְ��");
				String job = input.next();
				System.out.print("������绰���룺");
				String phone = input.next();
				Employee person = new Employee(name, job, phone);
				People.put(person, 0);
			}

			dutyInterval = new DutyIntervalSet<>(begin, end);

			do {
				int choice = menu();
				switch (choice) {
				case 1:
					if (People.isEmpty()) {
						System.out.println("û����Ա��Ϣ���޷������Ű�");
						break;
					}
					if (!dutyInterval.labels().isEmpty()) {
						System.err.println("�����Ű���Ϣ��������ֶ�����");
						break;
					}
					long start = begin;
					long timeCount = period;
					List<Employee> employeeList = new ArrayList<>(People.keySet());
					for (int i = 0; i < employeeList.size() - 1; i++) {
						long num = (long) (Math.random() * (timeCount - 1)) + 1;
						People.put(employeeList.get(i), 1);
						try {
							dutyInterval.Insert(start, start + num, employeeList.get(i));
						} catch (IntervalConflictException e) {
						}
						start += num;
						timeCount -= num;
						if (timeCount == 0) {
							break;
						}
					}
					if (timeCount != 0) {
						People.put(employeeList.get(employeeList.size() - 1), 1);
						try {
							dutyInterval.Insert(start, end, employeeList.get(employeeList.size() - 1));
						} catch (IntervalConflictException e) {
						}
					}
					System.out.print("���������Ű�\n");
					break;

				case 2:
					showPeople(People, 0);
					System.out.print("������������");
					String name = input.next();
					Employee employee = null;
					boolean exist = false;
					for (Employee e : People.keySet()) {
						if (People.get(e).equals(0) && e.getName().equals(name)) {
							exist = true;
							employee = e;
							break;
						}
					}

					if (!exist) {
						System.err.print("���˲����ڻ��Ѿ����Ź���\n");
						break;
					}

					long start1 = timeInput("��ʼʱ�䣺\n");
					long end1 = timeInput("����ʱ�䣺\n");
					if (start1 >= end1) {
						System.err.print("��ʼʱ��ӦС�ڵ��ڽ���ʱ��\n");
						break;
					} else if (begin > end1 || end < start1) {
						System.err.print("����������\n");
						break;
					}
					People.put(employee, 1);
					try {
						dutyInterval.Insert(start1, end1, employee);
					} catch (IntervalConflictException e) {
						People.put(employee, 0);
						System.err.print(e.getMessage());
						System.out.print("\n");
					}
					break;

				case 3:
					boolean checkB = dutyInterval.checkNoBlank();
					int sum = 0;
					if (checkB) {
						System.out.print("������\n");
					} else if (!(dutyInterval.labels().isEmpty())) {
						System.out.println("δ�Ű��ʱ���Ϊ��");
						List<Employee> sortedEmployee = dutyInterval.sort();
						if (begin < dutyInterval.start(sortedEmployee.get(sortedEmployee.size() - 1))) {
							long e = dutyInterval.start(sortedEmployee.get(sortedEmployee.size() - 1));
							System.out.println(getYear(begin) + "," + getMonth(begin) + "," + getDay(begin) + "->"
									+ getYear(e) + "," + getMonth(e) + "," + getDay(e));
							sum += e - begin;
						}

						for (int i = sortedEmployee.size() - 1; i > 0; i--) {
							if (dutyInterval.end(sortedEmployee.get(i)) < dutyInterval
									.start(sortedEmployee.get(i - 1))) {
								long s = dutyInterval.end(sortedEmployee.get(i));
								long e = dutyInterval.start(sortedEmployee.get(i - 1));
								System.out.println(getYear(s) + "," + getMonth(s) + "," + getDay(s) + "->" + getYear(e)
										+ "," + getMonth(e) + "," + getDay(e));
								sum += e - s;
							}
						}

						if (dutyInterval.end(sortedEmployee.get(0)) < end) {
							long s = dutyInterval.end(sortedEmployee.get(0));
							System.out.println(getYear(s) + "," + getMonth(s) + "," + getDay(s) + "->" + getYear(end)
									+ "," + getMonth(end) + "," + getDay(end));
							sum += end - s;
						}
						System.out.println("δ���ŵ�ʱ���ռ��ʱ��εı���Ϊ��" + sum + "/" + period);
					} else
						System.out.println("δ���ŵ�ʱ���ռ��ʱ��εı���Ϊ��" + sum + "/" + period);
					break;

				case 4:
					showPeople(People, 1);
					System.out.print("������������");
					String name1 = input.next();
					Employee employee1 = null;
					boolean exist1 = false;
					for (Employee E : People.keySet()) {
						if (People.get(E).equals(1) && E.getName().equals(name1)) {
							exist1 = true;
							employee1 = E;
							break;
						}
					}
					if (!exist1) {
						System.err.print("���˲����ڻ�δ�����Ź���\n");
						break;
					} else {
						People.put(employee1, 0);
						dutyInterval.remove(employee1);
					}
					break;

				case 5:
					showPeople(People, 0);
					System.out.print("������������");
					String name2 = input.next();
					boolean exist2 = false;
					for (Employee E : People.keySet()) {
						if (People.get(E).equals(0) && E.getName().equals(name2)) {
							exist2 = true;
							People.remove(E);
							break;
						}
					}
					if (!exist2) {
						System.err.print("���˲����ڻ��Ѿ������Ź���\n");
						break;
					}
					break;

				case 6:
					System.out.print("�Ű�����£�\n");
					for (Employee E : dutyInterval.labels()) {
						if (E != null) {
							long s = dutyInterval.start(E);
							long e = dutyInterval.end(E);
							String string = E.getName() + "[" + E.getJob() + "]" + ":		" + getYear(s) + "."
								+ getMonth(s) + "." + getDay(s) + " ~ " + getYear(e) + "." + getMonth(e) + "."
								+ getDay(e) + "		�ֻ����룺" + E.getPhoneNum() + "\n";
							System.out.print(string);
						}

					}
					break;
				default:
					System.exit(0);
				}
			} while (true);
		}

	}

}
