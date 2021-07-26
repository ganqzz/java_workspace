package sandbox;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DriverTester {
	public static void main(String[] args) {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		Driver driver;

		while (drivers.hasMoreElements()) {
			driver = drivers.nextElement();
			System.out.println("---");
			System.out.println("Driver Name    : " + driver.getClass().getName());
			System.out.println("Version        : " + driver.getMajorVersion() + "."
					+ driver.getMinorVersion());
			System.out.println("JDBC Compliant : " + driver.jdbcCompliant());
		}
	}
}
