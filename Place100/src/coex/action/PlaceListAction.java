package coex.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import coex.dao.PlaceDAO;
import coex.util.PageNavigator;
import coex.vo.Place;

public class PlaceListAction extends ActionSupport {
	
	Place place;
	List<Place> placeList;
	
	int currentPage = 1;
	
	PageNavigator pagenavi;
	
	/**
	 * dao를 호출하여 보여즐 상점 리스트를 가져옴 
	 * 스크롤이 내려갈때마다 8개의 상점list를 가져온다
	 * @return arraylist<Place>
	 * @throws Exception
	 */
	public String getPlaces() throws Exception {
		System.out.println("getPlaces()메소드 호출");
		System.out.println(place.getPlace_type() +" " + currentPage);
		
		int countPerPage = Integer.parseInt(getText("place.countperpage"));
		int pagePerGroup = Integer.parseInt(getText("place.pagepergroup"));
		
		System.out.println(countPerPage + " " + pagePerGroup);
		
		PlaceDAO placedao = new PlaceDAO();
		int total = placedao.countAll(place.getPlace_type());
		System.out.println(total);
		
		pagenavi = new PageNavigator(countPerPage, pagePerGroup, currentPage, total);
		
		placeList = placedao.getPlaces(place.getPlace_type(), pagenavi.getStartRecord(), pagenavi.getCountPerPage());
		System.out.println(placeList);
		return SUCCESS;
	}

	

	
	//getter setter 생성
	
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Place> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<Place> placeList) {
		this.placeList = placeList;
	}

	
	
	

}
