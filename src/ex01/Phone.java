package ex01;

import javafx.beans.property.SimpleStringProperty;

//맵핑작업하는 클래스(둘을 매치시키기위한것)

public class Phone {
	//SimpleStringProperty : fx에서 쓰는 일반적인 String. 이걸 쓰지 않으면 동작하지 않는게 있어서 써준다
	private SimpleStringProperty smartPhone;//실질적이름
	private SimpleStringProperty image;     //해당이미지
	public Phone(String smartPhone, String image) {
		this.smartPhone = new SimpleStringProperty(smartPhone);
		this.image = new SimpleStringProperty(image);
	}
	
	public String getSmartPhone() {//문자열만 쓸거라 String
		return smartPhone.get();
	}
	public void setSmartPhone(String smartPhone) {
		this.smartPhone = new SimpleStringProperty(smartPhone);
	}
	public String getImage() {
		return image.get();
	}
	public void setImage(String image) {
		this.image = new SimpleStringProperty(image);
	}

}
