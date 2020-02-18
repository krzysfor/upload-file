package com.example.filedemo;

import com.example.filedemo.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class FileDemoApplication {

	public static void main(String[] args) throws IOException, SQLException {
		SpringApplication.run(FileDemoApplication.class, args);

		//	String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=master;user=sa;password=sa";
		//	String connectionUrl = "jdbc:sqlserver://L1SW0206\\MSSQL2008R2;databaseName=wpen;user=sa;password=sa";
		//	String connectionUrl = "jdbc:sqlserver://L1SW0206\\MSSQL2008R2;databaseName=wpen;integratedSecurity=true";
		//	String connectionUrl = "jdbc:sqlserver://L1SW0206\\mssql2017;databaseName=wpen;user=sa;password=sa";
		//	String connectionUrl = "jdbc:sqlserver://L1SW0206\\mssql2017;databaseName=files;user=kf_test;password=kf_test";
		//  String connectionUrl = "jdbc:sqlserver://L1SW0206\\mssql2017;databaseName=files;integratedSecurity=false";
		 // String connectionUrl = "jdbc:sqlserver://L1SW0206\\mssql2017;database=files;integratedSecurity=true";
		 // String connectionUrl = "jdbc:sqlserver://L1SW0206\\mssql2017;database=files;integratedSecurity=true";


/*		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (Connection ignored = DriverManager.getConnection(connectionUrl)) {
				System.out.println("Done.");
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}*/

/*		// Create a Table and insert some sample data
		System.out.print("Creating sample table with data, press ENTER to continue...");
		System.in.read();
		String sql = new StringBuilder().append("USE files; ").append("CREATE TABLE Employees ( ")
				.append(" Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY, ").append(" Name NVARCHAR(50), ")
				.append(" Location NVARCHAR(50) ").append("); ")

				.append("INSERT INTO Employees (Name, Location) VALUES ").append("(N'Jared', N'Australia'), ")
				.append("(N'Nikita', N'India'), ").append("(N'Tom', N'Germany'); ").toString();


		Connection connection = DriverManager.getConnection(connectionUrl);

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(sql);
			System.out.println("Done.");
		}*/
	}
}
