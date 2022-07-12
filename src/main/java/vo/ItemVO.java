package vo;

public class ItemVO {
	private int item_id, item_price, item_like , item_count ,item_count_all , item_price_all;
	private String item_name , item_color , item_size, item_type , file_name ,user_id;
	
	public int getItem_price_all() {
		return item_price_all;
	}

	public void setItem_price_all(int item_price_all) {
		this.item_price_all = item_price_all;
	}

	
	public int getItem_count() {
		return item_count;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getItem_count_all() {
		return item_count_all;
	}

	public void setItem_count_all(int item_count_all) {
		this.item_count_all = item_count_all;
	}

	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_name() {
		return item_name;
	}

	public String getItem_color() {
		return item_color;
	}

	public void setItem_color(String item_color) {
		this.item_color = item_color;
	}

	public String getItem_size() {
		return item_size;
	}

	public void setItem_size(String item_size) {
		this.item_size = item_size;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public int getItem_like() {
		return item_like;
	}

	public void setItem_like(int item_like) {
		this.item_like = item_like;
	}
	
}
