package com.test.js.jsper;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 * Hello world!
 *
 */
public class App {

	Connection conn = null;
	ArrayList<User> usersList = new ArrayList<User>();
	ArrayList<Coll> collList = new ArrayList<Coll>();

	public Connection getDBConnection() throws ClassNotFoundException, SQLException {
		String myDriver = "com.mysql.jdbc.Driver";
		String myUrl = "jdbc:mysql://138.197.122.100:3306/sample";
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myUrl, "deepak", "rohan");
		System.out.println("S");
		return conn;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		App app = new App();
		app.getDBConnection();
		app.populateCollegeList();
		app.populateUserList();
		try {
			app.generateJasperFiles();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Hello World!");
	}

	public void populateUserList() throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * from users";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			User user = new User();
			user.setUserName(rs.getString(1));
			user.setEmail(rs.getString(2));
			usersList.add(user);
		}
		System.out.println(usersList.toString());
	}

	public void populateCollegeList() throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * from college";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Coll coll = new Coll();
			coll.setName(rs.getString(1));
			coll.setCollegeName(rs.getString(2));
			collList.add(coll);
		}
		System.out.println(collList.toString());
	}

	public void generateJasperFiles() throws JRException {
		String sourceFile = "D://Eclipse/jsper/src/main/resources/user.jrxml";
		String s = "D://Eclipse/jsper/src/main/resources/report1.jasper";
		String printFileName = null;
		JasperCompileManager.compileReportToFile(sourceFile);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(usersList);
		Map parameters = new HashMap();
		try {
			printFileName = JasperFillManager.fillReportToFile(s, parameters, beanColDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
		 JRXlsExporter exporter = new JRXlsExporter();
		

	            exporter.exportReport();
	}

}
