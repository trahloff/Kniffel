package helper;

public class SystemUtil {

	public static String getAppPath(){

		if ((System.getProperty("os.name")).toUpperCase().contains("WIN")){ // deckt s�mtliche Windowsversionen ab (Stand 21.07.2016)
			return System.getenv("AppData"); // returns '%appdata%'
		}else{
			return System.getProperty("user.home") + "/.Kniffel"; // linux/OSX hat keinen %appdata% Ordner, Programmordner werden durch den .-Pr�fix versteckt im Home Verzeichnis angelegt
		}

	}

}
