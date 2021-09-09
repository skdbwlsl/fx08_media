package ex02;


import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaServiceImpl implements MediaService{
		//MediaPlayer기능 : 컨트롤 담당(동영상 시작,취소,일시정지 등의 다양한 기능)
        MediaPlayer mediaPlayer;
        // MediaView :미디어 올리는 판이라고 생각하자.
        MediaView mediaView;
        //Button만들기
        Button btnPlay, btnPause, btnStop;
        boolean endOfMedia;
        
        
        //씬빌더에서 만들었던 기능 가져오기
        Label labelTime;
        Slider sliderVolume;
        ProgressBar progressBar;
        ProgressIndicator progressindIndicator;
        
        @Override
        public void myStart() {
        	//실행해준다
        	mediaPlayer.play();
                //재생버튼 누르면 영상이 시작되는 것을 확인
        }
        @Override
        public void myStop() {
        	mediaPlayer.stop();
                //종료 버튼 누르면 영상이 멈추는 것을 확인
        }
        @Override
        public void myPause() {
              mediaPlayer.pause();
              //일시중지된다
        }
        
        //기본세팅
        public void setControl(Parent root) {
                mediaView = (MediaView)root.lookup("#fxMediaView");
                btnPlay = (Button)root.lookup("#btnPlay");
                btnPause = (Button)root.lookup("#btnPause");
                btnStop = (Button)root.lookup("#btnStop");
                
                labelTime = (Label)root.lookup("#labelTime");
                sliderVolume = (Slider)root.lookup("#sliderVolume");
                progressBar = (ProgressBar)root.lookup( "#progressBar");
                progressindIndicator = ( ProgressIndicator)root.lookup("#progressIndicator");
        }
        public void setMedia(Parent root, String mediaName) {
                setControl(root);
                //System.out.println( getClass().getResource(mediaName) ); //getResource통해 mediaName넣어주면 mediaName에대한 정보 가져옴
                
                //동영상 자체. 동영상 가지고옴
                Media media = new Media( getClass().getResource(mediaName).toString() );//넣든 말든 상관없고, 그냥 문자열로 처리해라
                mediaPlayer = new MediaPlayer(media); //영상을 mediaPlayer로 만들어라(넣어라)
                
                //해당화면 mediaView에 해당동영상 mediaPlayer을 올리겠다
                mediaView.setMediaPlayer(mediaPlayer);
                
                //setOnReady : 준비
                //영상 재생을 누르지도 않았는데 일시중지, 종료버튼이 비활성화해서 못누르도록 만드는 기능
                mediaPlayer.setOnReady(new Runnable(){  //Runnable :인터페이스
                	public void run() {//그래서 인터페이스 만들어야해서 만듬
                		btnPlay.setDisable(false); //활성화
                		btnStop.setDisable(true);   //비활성화
                		btnPause.setDisable(true);
                		
                		//시간설정
                		mediaPlayer.currentTimeProperty().addListener((a,b,c)-> {
                			//  현재 얼마만큼 진행됐는지 아는방법 : 흐르는 시간 / 최종시간
                			double progress = 
                					mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
                			//시간이 흐르는 것으로 표현된다
                			progressBar.setProgress(progress);
                			progressindIndicator.setProgress(progress);
                			labelTime.setText(
                					//현재시간 바로 출력
                					(int)mediaPlayer.getCurrentTime().toSeconds() +   //setText에 의해서 현재시간 가져오고
                					"/" +  
                					 mediaPlayer.getTotalDuration().toSeconds());     //setText에 의해서 최종시간 가져오고
                		});
                	}
                });
                //미디어가 setOnPlaying 시작돼고있다면 시작버튼은 비활성화, 일시중지, 중지버튼은 활성화 하기
                mediaPlayer.setOnPlaying(() -> {
                	
                	//시작이 될 때에 대한 소리
                	sliderVolume.setValue(50.0);
                	
                	btnPlay.setDisable(true); //시작버튼은 비활성화
                	btnStop.setDisable(false);  //활성화
                	btnPause.setDisable(false); //활성화
                });
                
                //미디어가 setOnStopped 멈췄다면 시작버튼은 활성화, 나머지는 다시 비활성화 시키기
                mediaPlayer.setOnStopped(() -> {
                	btnPlay.setDisable(false);
                	btnStop.setDisable(true);
                	btnPause.setDisable(true);
                });
                
                //미디어가 일시중지 됐을 때 기능
                mediaPlayer.setOnPaused(() ->{
                	btnPlay.setDisable(false);
                	btnStop.setDisable(false);
                	btnPause.setDisable(true);
                });
                
                //동영상이 완전히 종료됐다면 버튼을 처음버튼으로 만들어준다
                mediaPlayer.setOnEndOfMedia(()-> {
                	btnPlay.setDisable(false);
                	btnStop.setDisable(true);
                	btnPause.setDisable(true);
                	myStop();//완전히 영상이 끝났다면 재생시 다시첨부터 실행되도록 호출
                });
        }
        public void volumeControl() {//오버라이드 해줌
        	//setVolume : 볼륨설정하기
        	mediaPlayer.setVolume(sliderVolume.getValue() /100.0);//기본설정값이 들어간다
        };
}







