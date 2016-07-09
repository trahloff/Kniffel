package helper;

public class SystemUtil {

	public static String getAppPath(){

		if ((System.getProperty("os.name")).toUpperCase().contains("WIN")){
			return System.getenv("AppData");
		}else{
			return System.getProperty("user.home") + "/.Kniffel"; // don't make linux users angry
		}

	}

}
