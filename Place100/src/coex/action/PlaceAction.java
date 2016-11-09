package coex.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import coex.dao.PlaceDAO;
import coex.util.FileService;
import coex.util.PageNavigator;
import coex.vo.Place;

public class PlaceAction extends ActionSupport {
	
	Place place;
	List<Place> list_place;
	PlaceDAO dao = new PlaceDAO();
	
	private File upload;						//업로드할 파일
	private String uploadFileName;				//업로드할 파일의 파일명
	private String uploadContentType;			//업로드할 파일의 컨텐츠

	int currentPage = 1;						//게시물 목록의 현 페이지
	PageNavigator pagenavi;
	
	
	public String insertPlace(){
		System.out.println("insertPlace()메소드 정의");
		System.out.println(place);
		if (upload != null) {
			FileService fs = new FileService();
			String basePath = getText("place.uploadpath");		//저장될 파일이 실제 저장될 주소의 이름
			System.out.println(basePath);
			String savedfile = null;
			try {
				savedfile = fs.saveFile(upload, basePath, place.getPlace_name());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(savedfile);
			place.setPlace_photo_name(savedfile);
		}
		dao.insertPlace(place);
		return SUCCESS;
	}
	
	/**
	 * dao를 호출하여 보여즐 상점 리스트를 가져옴 
	 * 스크롤이 내려갈때마다 10개의 상점list를 가져온다
	 * @return arraylist<Place>
	 * @throws Exception
	 */
	public String getPlaceList() throws Exception{
		System.out.println("메소드");
		
		/*int countPerPage = Integer.parseInt(getText("place.countperpage"));
		int pagePerGroup = Integer.parseInt(getText("place.pagepergroup"));
		
		System.out.println(currentPage);
		System.out.println(countPerPage +" "+ pagePerGroup);
		*/
		PlaceDAO dao = new PlaceDAO();
		/*int total = dao.countAll(place.getPlace_type());
		System.out.println("전체 상점 수 : " + total);
		//pagenavigator 객체 생성(페이지당 글수, 그룹당 페이지 수, 현재페이지, 젠체 글수)
		pagenavi = new PageNavigator(countPerPage, pagePerGroup, currentPage, total);*/
		list_place= dao.getList(place.getPlace_type());
		System.out.println(list_place.toString());
		return SUCCESS;
	}
	

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<Place> getList_place() {
		return list_place;
	}

	public void setList_place(List<Place> list_place) {
		this.list_place = list_place;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
}


