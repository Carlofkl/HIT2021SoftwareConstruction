package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MagicSquare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("hello world");
		// boolean flag1 = MagicSquare.isLeagalMagicSquare("src/P1/txt/1.txt");
		System.out.println("The magic square in text.1 is " + MagicSquare.isLeagalMagicSquare("src/P1/txt/1.txt"));
		System.out.println("The magic square in text.2 is " + MagicSquare.isLeagalMagicSquare("src/P1/txt/2.txt"));
		System.out.println("The magic square in text.3 is " + MagicSquare.isLeagalMagicSquare("src/P1/txt/3.txt"));
		System.out.println("The magic square in text.4 is " + MagicSquare.isLeagalMagicSquare("src/P1/txt/4.txt"));
		System.out.println("The magic square in text.5 is " + MagicSquare.isLeagalMagicSquare("src/P1/txt/5.txt"));
		Scanner scan = new Scanner(System.in);
		System.out.println("\nplease input n:");
		int n = scan.nextInt();
		System.out.println(generateMagicSquare(n));
		scan.close();
		System.out.println("The magic square in text.6 is " + MagicSquare.isLeagalMagicSquare("src/P1/txt/6.txt"));
	}

	public static boolean isLeagalMagicSquare(String fileName) {
		int i, j;
		int row, column;
		// �õ�����row
		File txt = new File(fileName);
		row = 0;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(txt));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (reader.readLine() != null) {
				row++;
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(row);

		// ��һ�ִ��������������
		column = 0;
		String readrow = null;
		String[] everyRowNum;
		reader = null;
		try {
			reader = new BufferedReader(new FileReader(txt));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((readrow = reader.readLine()) != null) {
				everyRowNum = readrow.split("\t");
				column = everyRowNum.length;
				if (column != row) {
					System.out.print("�����������\n");
					return false;
				}
			}
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// �ڶ��ִ�������Ԫ�ز���������
		try {
			reader = new BufferedReader(new FileReader(txt));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((readrow = reader.readLine()) != null) {
				everyRowNum = readrow.split("\t");
				column = everyRowNum.length;
				for (j = 0; j < column; j++) {
					if (everyRowNum[j].contains(".")||everyRowNum[j].contains("-")) {
						System.out.print("���ڷ�������\n");
						return false;
					}
				}
			}
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// �����ִ���Ԫ�ز�����\tΪ�ָ���
		int[][] matrix = new int[row][row];
		try {
			reader = new BufferedReader(new FileReader(txt));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((readrow = reader.readLine()) != null) {
				everyRowNum = readrow.split("\t");
				column = everyRowNum.length;
				for (i = 0; i < row; i++) {
					for (j = 0; j < column; j++) {
						matrix[i][j] = Integer.valueOf(everyRowNum[j]); // �����ո񴥷��쳣��ɸѡ����"\t"�����
					}
				}
			}
			reader.close();
		} catch (Exception e1) {
			System.out.print("���ּ䲢δʹ��'\t'����\n");
			e1.printStackTrace();
			return false;
		}

		// �����Ƿ�Ϊmagic square

		int[] rowSum = new int[row];
		int[] columnSum = new int[row];
		int[] diagonalSum = new int[2];

		boolean flag = true;
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (i = 0; i < row; i++) {
			for (j = 0; j < row; j++) {
				matrix[i][j] = sc.nextInt();
				// System.out.print(matrix[i][j]+ " ");
			}
			// System.out.print("\n");
		}
		// �ж���
		for (i = 0; i < row; i++) {
			rowSum[i] = 0;
			for (j = 0; j < row; j++) {
				rowSum[i] += matrix[i][j];
			}
		}
		for (i = 0; i < row; i++) {
			// System.out.print(rowSum[i]+" ");
			if (rowSum[i] != rowSum[0])
				flag = false;
		}
		// System.out.print("\n");
		// �ж���
		for (i = 0; i < row; i++) {
			columnSum[i] = 0;
			for (j = 0; j < row; j++) {
				columnSum[i] += matrix[j][i];
			}
		}
		for (i = 0; i < row; i++) {
			// System.out.print(columnSum[i]+" ");
			if (columnSum[i] != columnSum[0])
				flag = false;
		}
		// System.out.print("\n");
		// �ж϶Խ���
		for (i = 0; i < 2; i++) {
			diagonalSum[i] = 0;
			for (j = 0; j < row; j++) {
				diagonalSum[i] += matrix[j][j];
			}
			// System.out.print(diagonalSum[i]+" ");
		}
		if (diagonalSum[0] != diagonalSum[1])
			flag = false;

		if (flag == true)
			return true;
		return false;
	}

	public static boolean generateMagicSquare(int n) {
		if (n%2==0 || n<0) {//�ж�����n�Ƿ���������
			return false;
		}
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;//ÿ��row--��col++����ʵ��б������
			if (i % n == 0)//ÿ��б������һ��֮�󣬷�ֹ���ǵ�ǰ��ģ�row++��һ�����¿�ʼ
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");//���MagicSquare
			System.out.println();
		}
		//FileWriter writer = null;
		try {
			File file = new File("src/p1/txt/6.txt");
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8");
			BufferedWriter writer = new BufferedWriter(out);
			for (i=0; i<n; i++) {
				for(j=0; j<n; j++) {
					writer.write(magic[i][j] + "" + '\t');
				}
				writer.write("\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return true;
	}
}
