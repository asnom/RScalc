package runescape.skills;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class GrandExchangeUtil {
	private static final String rsAPIString = "http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json";
	private static HashMap<String, Integer> itemMap = new HashMap<String, Integer>();

	private static void initialize() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("./resources/items.map"));
		String line = "";
		String item = "";
		String value = "";
		while (br.ready()) {
			line = br.readLine();
			if (line.contains("{")) {
				value = br.readLine().split(":")[1].split(",")[0].trim();
				item = br.readLine().split(":")[1].replaceAll("\"", "").trim();
				itemMap.put(item.toLowerCase(), Integer.parseInt(value));
			}
			
		}
		br.close();
	}

	public static double getGrandExchangeCost(String itemName) throws IOException {
		double cost = 0;
		initialize();
		int id = itemMap.get(itemName.toLowerCase());
		HttpURLConnection rsAPI = (HttpURLConnection) new URL(rsAPIString + "?item=" + id).openConnection();
		try {
			BufferedInputStream in = new BufferedInputStream(rsAPI.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while (br.ready() && cost == 0) {
				line = br.readLine();
				if (line.contains("price")) {
					cost = Double.parseDouble(line.substring(line.indexOf("price")).split("}")[0].split(":")[1]);
				}
			}
		} finally {
			rsAPI.disconnect();
		}
		return cost;
	}
}
