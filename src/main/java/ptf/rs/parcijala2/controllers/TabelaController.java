package ptf.rs.parcijala2.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ptf.rs.parcijala2.Config;
import ptf.rs.parcijala2.models.Product;
import ptf.rs.parcijala2.repository.ProductRepository;
import ptf.rs.parcijala2.utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class TabelaController implements Initializable {
    public TableView<Product> tabela;

    private final ProductRepository productRepository;
    public TableColumn<Product, Boolean> popustColumn;
    public TableColumn<Product, Double> cijenaColumn;

    public TabelaController() {
        this.productRepository = Config.productRepository;
    }

    public void loadItems() {
        tabela.setItems(FXCollections.observableArrayList(productRepository.getAll()));
        tabela.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popustColumn.setCellFactory(cellData -> new TableCell<>(){
            @Override
            protected void updateItem(Boolean value, boolean empty) {
                super.updateItem(value, empty);
                if (value == null || empty) setText("");
                else setText(value ? "Da" : "Ne");
            }
        });
        cijenaColumn.setCellFactory(param -> new TableCell<>(){
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) setText("");
                else setText(String.format("%.02f KM", item));
            }
        });
        loadItems();
    }

    public void prikazPodataka(ActionEvent actionEvent) {
        //double ukupno = tabela.getItems().stream().mapToDouble(Product::getCijena).sum();
        double ukupno = 0;
        for (int i = 0; i < tabela.getItems().size(); i++) {
            Product product = tabela.getItems().get(i);
            if (product.isPopust()) ukupno += (product.getCijena() - (product.getCijena() * 0.2)) * product.getKolicina();
            else ukupno += product.getCijena() * product.getKolicina();
        }
        Utils.displayAlert(String.format("Ukupno je kreirano %d proizvoda\nUkupna vrijednost proizvoda s popustom je %.02f KM", tabela.getItems().size(), ukupno), Alert.AlertType.INFORMATION);
    }
}
