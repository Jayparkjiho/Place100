package coex.dao;

import java.util.List;

import coex.vo.PlaceAndPref;

public class PlaceAndPrefsDAO_TEST {
	public static void main(String[] args) {
		PlaceAndPrefDAO dao = new PlaceAndPrefDAO();
		List<PlaceAndPref> pref = null;
		for(PlaceAndPref p : dao.getAllList()){
			System.out.println(p.toString());
		}
	}
}
