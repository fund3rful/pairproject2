/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import impresario.IModel;
import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Book;
import model.BookCollection;
import model.PatronCollection;
import userinterface.PatronCollectionView.PatronInsert;

/**
 *
 * @author Andrew
 */
public class BookCollectionView extends View {

    static BookCollection b;
    private TableView<BookInsert> table = new TableView<BookInsert>();
    private final ObservableList<BookInsert> data = FXCollections.observableArrayList();

    public BookCollectionView(IModel model) {
        super(model, "BookCollectionView");
        b = (BookCollection) model;

        addData();

        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));

        container.getChildren().add(createFormContent());

        getChildren().add(container);
    }

    public void addData() {
        Vector<Book> bookList = (Vector<Book>) b.getState("books");
        for (Book book : bookList) {
            data.add(new BookInsert((String) book.getState("title"),
                    (String) book.getState("author"), (String) book.getState("pubYear"), (String) book.getState("status"),(String) book.getState("bookId")));
        }
    }

    public VBox createFormContent() {
        //vbox
        VBox vbox = new VBox(10);

        //Label
        final Label label = new Label("Books");
        label.setFont(new Font("Arial", 20));

        //create columns
        TableColumn IDCol = new TableColumn("bookId");
        IDCol.setMinWidth(100);
        IDCol.setCellValueFactory(
                new PropertyValueFactory<BookInsert, String>("bookId"));

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<BookInsert, String>("title"));

        TableColumn authorCol = new TableColumn("Author");
        authorCol.setMinWidth(100);
        authorCol.setCellValueFactory(
                new PropertyValueFactory<BookInsert, String>("author"));

        TableColumn pubYearCol = new TableColumn("Publication Year");
        pubYearCol.setMinWidth(100);
        pubYearCol.setCellValueFactory(
                new PropertyValueFactory<BookInsert, String>("pubYear"));

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setMinWidth(100);
        statusCol.setCellValueFactory(
                new PropertyValueFactory<BookInsert, String>("status"));

        HBox doneCont = new HBox(10);
        doneCont.setAlignment(Pos.CENTER_RIGHT);
        Button cancelButton = new Button("Done");
        cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                Librarian lib = new Librarian();
                lib.start();

                }});

        //setup the data
        table.setItems(data);
        table.getColumns().addAll(IDCol, titleCol, authorCol, pubYearCol, statusCol);

        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));

        vbox.getChildren().addAll(label, table);

        return vbox;
    }

    @Override
    public void updateState(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class BookInsert {

        private final SimpleStringProperty title;
        private final SimpleStringProperty author;
        private final SimpleStringProperty pubYear;
        private final SimpleStringProperty status;
        private final SimpleStringProperty bookId;

        private BookInsert(String title, String author, String pubYear, String status, String bookId) {

            this.title = new SimpleStringProperty(title);
            this.author = new SimpleStringProperty(author);
            this.pubYear = new SimpleStringProperty(pubYear);
            this.status = new SimpleStringProperty(status);
            this.bookId = new SimpleStringProperty(bookId);
        }

        public String getTitle() {
            return title.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getPubYear() {
            return pubYear.get();
        }

        public String getStatus() {
            return status.get();
        }

        public String getBookId() {
            return bookId.get();
        }

        public void setTitle(String s) {
            title.set(s);
        }

        public void setAuthor(String s) {
            author.set(s);
        }

        public void setPubYear(String s) {
            pubYear.set(s);
        }

        public void setStatus(String s) {
            status.set(s);
        }

        public void setBookId(String s) {
            bookId.set(s);
        }
    }
}
