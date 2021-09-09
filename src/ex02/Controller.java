package ex02;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Controller implements Initializable{
	Parent root;
	MediaService ms;
	public void setRoot(Parent root) {
		this.root = root;
		//미디어 어떤 영상을 쓸건지 경로적기(절대경로로 썼다)
		ms.setMedia(root, "/media/video.m4v"); //안될땐 ..을 써서 상대경로를 해야한다
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ms = new MediaServiceImpl();//초기화 해주기
	}
	public void myPlay() {//플레이버튼을 누르면
		ms.myStart();  //스타트로 넘겨준다
	}
	public void myPause() {
		ms.myPause();
	}
	public void myStop() {
		ms.myStop();
	}
	
	public void volumeControl() {
		//호출
		ms.volumeControl();
	}
}







