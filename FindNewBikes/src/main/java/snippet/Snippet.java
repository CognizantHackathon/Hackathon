package snippet;

public class Snippet {
	//Create prefs map to store all preferences 
	Map<String, Object> prefs = new HashMap<String, Object>();
	
	//Put this into prefs map to switch off browser notification
	prefs.put("profile.default_content_setting_values.notifications", 2);
	
	//Create chrome options to set this prefs
	ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("prefs", prefs);
}

