package cli;

/**
 * @author Stefan Polydor
 * @version 13.1.2015
 */
public class ArgumentParser {

	private String[] arguments;

	public ArgumentParser(String[] args) {


		System.out.println(System.getProperty("user.name")); // Username auslesen
	}

	public String[] getArguments() {
		return arguments;
	}

}
