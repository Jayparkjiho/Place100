package coex.dao;

import java.util.ArrayList;
import java.util.List;

import coex.vo.Action;

public class ActionDAO_TEST {
	public static void main(String[] args) {
		ActionDAO dao = new ActionDAO();
		Action action = new Action(0,"진홍박람회",10302,"2016/10/31", "2016/11/05","jake.jpg" );
		Action action2 = new Action(0,"고상컨퍼런스",10302,"2016/10/31", "2016/11/05","go.jpg" );
		//dao.insertAction(action2);
		System.out.println(dao.findAction(20000).toString());
		System.out.println(dao.findAction(20001).toString());
		
		action = dao.findAction(20000);
		action.setAction_name("지호박람회");
		dao.updateAction(action);
		System.out.println(dao.findAction(20000).toString());
		List<Action> actions;
		actions = dao.getPlaceActionList(10302);
		for(Action a : actions){
			System.out.println(a.toString());
		}
	}
}
