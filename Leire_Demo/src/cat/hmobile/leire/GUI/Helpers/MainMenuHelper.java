package cat.hmobile.leire.GUI.Helpers;

import java.util.ArrayList;
import java.util.List;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.menu.MainMenuItem;

public class MainMenuHelper {

	public static final int CATALOG = 0;
	public static final int SEARCH = 1;
	public static final int UPDATES = 2;
	public static final int PARTNER_CHANNEL = 3;
	public static final int INFO_HELP = 4;
	public static final int SETTINGS = 5;
	
	//Create all menu options
	static MainMenuItem miCatalog 			= new MainMenuItem(CATALOG, R.string.mainmenu_footer_catalog, R.drawable.catalogo, true);
	static MainMenuItem miSearch 			= new MainMenuItem(SEARCH, R.string.mainmenu_footer_scanner, R.drawable.ic_menu_search, true);
	static MainMenuItem miUpdates 			= new MainMenuItem(UPDATES, R.string.mainmenu_footer_updates, R.drawable.ic_menu_updates, true);
	static MainMenuItem miPartnerChannel 	= new MainMenuItem(PARTNER_CHANNEL, R.string.mainmenu_footer_partnerchannel, R.drawable.ic_menu_channel, true);
	static MainMenuItem miInfoHelp 			= new MainMenuItem(INFO_HELP, R.string.mainmenu_footer_info, R.drawable.ic_menu_info, true);
	static MainMenuItem miSettings 			= new MainMenuItem(SETTINGS, R.string.menu_settings, R.drawable.ic_menu_settings, true);
	
	static ArrayList<MainMenuItem> menu = new ArrayList<MainMenuItem>();
	
	public static List<MainMenuItem> getMenuItems(){
		
		if(menu.size() == 0){
			menu.add(miCatalog);
			menu.add(miSearch);
			menu.add(miUpdates);
			menu.add(miPartnerChannel);
			menu.add(miInfoHelp);
			menu.add(miSettings);
		}
		
		return menu;
	}
}
