import java.util.concurrent.ExecutorService;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class HelloWorld {
	
	private static String fid = "AKIAI44QH8DHBEXAMPLE";
	private static String fpk = "je7MtGbClwBF/2Zp9Utk/h3yCo8nvbEXAMPLEKEY";
	
	// Will remove after testing
	public static void main(String[] args)
	{
		System.out.println("fid = " + fid);
		System.out.println("fpk = " + fpk);
				
		stringEqualityCheckNoncompliant("hello", "hello");
		executeSqlStatementNoncompliant(null, null);
		keyPairGeneratorNoncompliant();
	}
	
	public static void stringEqualityCheckNoncompliant(String string1, String string2) {
    		// Noncompliant: the == operator doesn't compare the contents of the strings.
   		if(string1 == string2) {
        		System.out.println("The strings are equal.");
    		}
	}
	
	static void shutdownNonCompliant(ExecutorService executorService) throws InterruptedException {
	    if (executorService != null) {
		// Noncompliant: shutdownNow is called which suddenly shuts down executorService.
		executorService.shutdownNow();
	    }
	}
	
	public static void executeSqlStatementNoncompliant(HttpServletRequest request, java.sql.Connection connection) {
	    final String favoriteColor = request.getParameter("favoriteColor");
	    try {
		String sql = "SELECT * FROM people WHERE favorite_color='" + favoriteColor + "'";
		java.sql.Statement statement = connection.createStatement();
		// Noncompliant: user-given input is not sanitized before use.
		statement.execute(sql);
	    } catch (java.sql.SQLException e) {
		throw new RuntimeException(e);
	    }
	}
	
	public static void keyPairGeneratorNoncompliant() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		// Noncompliant: keysize too small for this algorithm.
		keyPairGenerator.initialize(128);
		keyPairGenerator.genKeyPair();
	}

}
