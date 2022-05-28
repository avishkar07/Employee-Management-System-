package EMS;

import java.sql.SQLException;
import java.util.Scanner;

public class User extends Admin implements Runnable{
	
	public static void main(String[] args) {
	
		User user= new User();
		Thread thread1 =new Thread(user," user1");
		Thread thread2 =new Thread(user," user2");
		thread1.start();
		try {
			thread1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread2.start();
	
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" Start");
		try {
			userlogin();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(Thread.currentThread().getName()+"Stop.");
	}

	public synchronized void userlogin() {
		System.out.println(Thread.currentThread().getName()+" Running.....");
		Scanner scanner = new Scanner(System.in);
//		User user = new  User();
//		Thread thread1 = new Thread(user);
		System.out.println("Choose :");
		System.out.println("1 for Admin ");
		System.out.println("2 for Employee");
		int key = scanner.nextInt();
		switch (key) {
		case 1: {
			System.out.println("------Admin------");
			try {
				Thread.currentThread().setName("Admin Thread..");
				Admin.Adminmethod();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (adminException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}
		case 2: {
			System.out.println("------Employee------");
			Employee employee = new Employee();
			Thread.currentThread().setName("Employee Thread..");
			try {
				employee.EmployeeMethod();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}



}
