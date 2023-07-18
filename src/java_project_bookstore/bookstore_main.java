package java_project_bookstore;

public class bookstore_main {
	public static void main(String args[]) {
		bookstore_frame frame;
		bookstore_DAO db = new bookstore_DAO();
		
		frame = new bookstore_frame(db);
		frame.setSize(960, 680);
		frame.setVisible(true);
	}
}
