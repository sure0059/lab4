package action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DetailInfo extends ActionSupport{
	private String authorid;
	private String title;
	private String[] author = new String[3];
	private String[] book = new String[4];
	public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getAuthor() {
		return author;
	}
	public void setAuthor(String[] author) {
		this.author = author;
	}
	public String[] getBook() {
		return book;
	}
	public void setBook(String[] book) {
		this.book = book;
	}
	/**
	 * 取得作者的详细信息
	 */
	public void GetAuthor() throws Exception {
		DaoCon dao = new DaoCon();
		Connection con = dao.GetConn();
		Statement stmt = con.createStatement() ;
		String sql = "select * from author where authorid = "+authorid;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			author[0] = rs.getString("name");
			author[1] = rs.getString("country");
			author[2] = rs.getString("age");
		}
		rs.close();
		stmt.close();
        con.close();
	}
	/**
	 * 取得书目的详细信息
	 */
	public void GetBook() throws Exception {
		DaoCon dao = new DaoCon();
		Connection con = dao.GetConn();
		Statement stmt = con.createStatement() ;
		 String sql = "select * from book where title = '"+title+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			book[0] = rs.getString("isbn");
			book[1] = rs.getString("publisher");
			book[2] = rs.getString("publishdate");
			book[3] = rs.getString("price");
		}
		rs.close();
		stmt.close();
        con.close();
	}
	public String execute(){
		try {
			GetAuthor();
			GetBook();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return "success";
	}
}
