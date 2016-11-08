package coex.vo;
import java.util.Comparator;

//장소번호와 점수로 이루어진 객체
public class PlaceAndScore implements Comparator<PlaceAndScore>,Comparable<PlaceAndScore> {
	private int placeID;
	private int prefScore;

	public PlaceAndScore() {
	}

	public PlaceAndScore(int placeID, int prefScore) {
		super();
		this.placeID = placeID;
		this.prefScore = prefScore;
	}

	public int getPlaceID() {
		return placeID;
	}

	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}

	public int getPrefScore() {
		return prefScore;
	}

	public void setPrefScore(int prefScore) {
		this.prefScore = prefScore;
	}


	@Override
	public int compareTo(PlaceAndScore o) {
		return ((Integer)this.getPrefScore()).compareTo((Integer)o.getPrefScore());
	}

	@Override
	public int compare(PlaceAndScore o1, PlaceAndScore o2) {
		return o1.prefScore - o1.prefScore;
	}

	@Override
	public String toString() {
		return "PlaceAndScore [placeID=" + placeID + ", prefScore=" + prefScore + "]";
	}
	
	

}
