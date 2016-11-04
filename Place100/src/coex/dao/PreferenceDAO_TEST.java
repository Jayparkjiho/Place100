package coex.dao;

import coex.vo.Preference;

public class PreferenceDAO_TEST {
	
	public static void main(String[] args) {
		PreferenceDAO prefDAO = new PreferenceDAO();
		Preference pref = null;
		pref = prefDAO.findPreference(20001); 
		System.out.println(pref.toString());
		/*pref.setPref_10(20);
		prefDAO.updatePreference(pref);
		pref = prefDAO.findPreference(20001); 
		System.out.println(pref.toString());*/
		
		int prefNo = prefDAO.getPrefNo();
		System.out.println(prefNo);
	}//TEST SUCCEED

}

