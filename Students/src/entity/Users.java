package entity;
//用户类
public class Users {
	private int uid;
	private String username;
	private String password;
	
	public Users() {
		
	}
	public Users(int uid, String username, String password) {
		//super();
		this.uid = uid;
		this.username = username;
		this.password = password;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
