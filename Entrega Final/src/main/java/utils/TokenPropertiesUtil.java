package utils;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
public class TokenPropertiesUtil {
  private static Properties properties = new Properties();
  public static TokenPropertiesUtil INSTANCE;

  static {
    try {
      INSTANCE = new TokenPropertiesUtil();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  private TokenPropertiesUtil() throws Exception {
    properties.load(new FileReader(new File("src/main/resources/token.properties")));
  }

  public static String getString(String key) {
    return properties.getProperty(key);
  }

  public static int getInt(String key) {
    return Integer.parseInt(properties.getProperty(key));
  }

}
