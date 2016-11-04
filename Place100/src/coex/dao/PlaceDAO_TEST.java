package coex.dao;

public class PlaceDAO_TEST {
	public static void main(String[] args) {
		PlaceDAO dao = new PlaceDAO();
		System.out.println(dao.selectPlace(10152).toString());
	}
}
