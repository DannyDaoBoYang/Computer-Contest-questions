package librarytester;

public class Book {

    private String title, author, checkOutName;
    private boolean isBookOut;

    public Book(String t, String a) {
        title = t;
        author = a;
        isBookOut = false;
        checkOutName = "";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isOut() {
        return isBookOut;
    }

    public String getCheckOutName() {
        return checkOutName;
    }

    public void setTitle(String t) {
        title = t;
    }

    public void setAuthor(String a) {
        author = a;
    }

    public void setIsOut(boolean b) {
        isBookOut = b;
    }

    public void setCheckOutName(String con) {
        checkOutName = con;
    }

    public String toString() {
        String output = "";
        output += "Title: " + title + "\n";
        output += "Author: " + author + "\n";
        output += "Is Checked Out: " + isBookOut + "\n";
        output += "Checked Out By: " + checkOutName + "\n";
        return output;
    }
}
