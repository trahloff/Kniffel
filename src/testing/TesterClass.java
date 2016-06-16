package testing;

import java.io.IOException;
import java.util.Iterator;

import poi.*;

public class TesterClass {

	public static void main(String[] args) throws IOException {
		
//		POI_Service.resetSaveFile();
		POI_Service.deletePlayer("Harthmuasdasht");
		POI_Service.createPlayer("Harthmuasdasht");
		
		Iterator<String> i = POI_Service.getPlayerList().iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}

	}

}
