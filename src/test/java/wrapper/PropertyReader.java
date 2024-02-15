package wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	
	public static String readDataFromProperty(String fileName,String propertyName)  {
		
		File file = new File("./data/"+fileName+".properties");
		FileInputStream fis;
		String proValue = "";
		try {
			fis = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fis);
			proValue = properties.getProperty(propertyName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return proValue;
		
	}

}
