import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JDBCConnection {

	public static void main(String[] args) throws SQLException {
		String host = "localhost";
		String port = "3306";

		// jdbc:mysql://localhost:3306/Demo or jdbc:mysql://@localhost:3306/Demo (Demo is DB Name)
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/Demo", "root", "root");

		Statement s = con.createStatement();

		ResultSet rs = s.executeQuery("select * from Credentials where Scenario = 'ZeroBalanceCard';");

		// usually result set will sit at 1st index, to move to 1st index give rs.next();
		rs.next();
		System.out.println(rs.getString(2)); // getting value by Column Index
		System.out.println(rs.getString("Password")); // getting value by Column Name
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys(rs.getString(2));
		driver.findElement(By.id("userPassword")).sendKeys(rs.getString("Password"));
		
		driver.close();
	}
}
