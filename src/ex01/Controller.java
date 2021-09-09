package ex01;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller implements Initializable{
		Parent root;
		ListView<String> fxListView;
		ImageView fxImageView;
		ObservableList<String> phoneString;
		ArrayList<String> url;
		//ObservableList<Phone> phoneURL;
		
		public void setRoot(Parent root) {
			this.root = root;
			fxListView = (ListView)root.lookup("#fxListView");
			//이미지뷰 가져오기
			fxImageView = (ImageView)root.lookup("#fxImageView");
			setListView();
		}
		
		public void setListView() {
			setList();
			//이벤트설정
			fxListView.getSelectionModel().selectedIndexProperty().addListener((observable,oldValue,newValue)-> {
				System.out.println("observablee(형식) : " +observable);
				System.out.println("oldValud(이전값) : " +oldValue);
				System.out.println("newValue(현재값) : " +newValue); //newValue는 인덱스번호가 나온다
				
				System.out.println(phoneString.get((int)newValue));
				//System.out.println(phoneURL.get((int)newValue).getSmartPhone());
			    //System.out.println(phoneURL.get((int)newValue).getImage());
			    //경로를 넣으면 맞는 해당 이미지를 불러온다
			    //fxImageView.setImage(new Image("/img/phone/phone01.png")); //phone01.png은 고정값
			    //fxImageView.setImage(new Image("/img/phone/" + phoneURL.get((int)newValue).getImage()));//맞는 이미지가 각각 출력됨
				fxImageView.setImage(new Image("/img/phone/"+ url.get( (int)newValue )) );
			});
		}
		
		//목록들 추가
		public void setList() {
			//객체화시키기
			phoneString = FXCollections.observableArrayList(); 
			//phoneURL = FXCollections.observableArrayList();
			url = new ArrayList<String>();
			for(int i =1; i<8;i++) {  //숫자7까지 반복하는 이유는 폰사진이 7개라 사진갯수만큼 반복하는것
				phoneString.add("갤럭시s" +i);
				//폰공간에 들어온다
				//Phone phone = new Phone("갤럭시s" + i, "phone0" + i + ".png");
				//phoneURL.add(phone);
				url.add("phone0"+i+".png");
			}
			
			//fx상에서의 list라 그냥 자료형넣어주라는 의미. 내용을 넣으려면 observableArrayList 자료형이 필요한것
			fxListView.setItems(phoneString);
			
		}
		//버튼클릭하면 들어오게끔 하는것
		public void btn() {
			//파일 가져오고, 팬가져와서 씬에 등록
			System.out.println("버튼 클릭");
			FXMLLoader loader = 
					new FXMLLoader(getClass().getResource("aaa.fxml"));
			Parent newRoot = null;
			Scene scene = null;
			try {
				newRoot = loader.load();
			} catch (Exception e) {
				e.printStackTrace();
			}
			scene = new Scene(newRoot);
			
			Stage stage = (Stage)root.getScene().getWindow();
			System.out.println("stage : "+stage);
			stage.setScene(scene);
			stage.show();
		}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
