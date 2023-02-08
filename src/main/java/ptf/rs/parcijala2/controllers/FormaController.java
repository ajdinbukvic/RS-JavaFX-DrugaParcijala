package ptf.rs.parcijala2.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ptf.rs.parcijala2.models.Kategorija;
import ptf.rs.parcijala2.repository.ProductRepository;
import ptf.rs.parcijala2.Config;
import ptf.rs.parcijala2.models.Product;
import ptf.rs.parcijala2.utils.Utils;
import ptf.rs.parcijala2.utils.Validators;

import java.net.URL;
import java.util.ResourceBundle;

public class FormaController implements Initializable {
    private final ProductRepository productRepository;
    public TextField nazivField;
    public TextArea opisArea;
    public TextField cijenaField;
    public TextField kolicinaField;
    public ComboBox<Kategorija> kategorijaBox;
    public CheckBox popustCheck;

    public FormaController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public FormaController() {
        this(Config.productRepository);
    }

    public void dodajProizvod(ActionEvent actionEvent) {
        try {
            Validators.checkRequiredField(nazivField.getText(), "Naziv");
            Validators.checkRequiredField(cijenaField.getText(), "Cijena");
            Validators.checkRequiredField(kolicinaField.getText(), "Količina");

            Validators.checkMaxLength(nazivField.getText(), 50, "Naziv");
            Validators.checkMaxLength(opisArea.getText(), 100, "Opis");

            Validators.checkValidDoubleNumber(cijenaField.getText(), "Cijena");
            Validators.checkNegativeNumber(cijenaField.getText(), "Cijena");

            Validators.checkValidIntNumber(kolicinaField.getText(), "Količina");
            Validators.checkNegativeNumber(kolicinaField.getText(), "Količina");

            Product product = new Product();
            product.setNaziv(nazivField.getText());
            product.setOpis(opisArea.getText());
            product.setCijena(Double.parseDouble(cijenaField.getText()));
            product.setKolicina(Integer.parseInt(kolicinaField.getText()));
            product.setKategorija(kategorijaBox.getSelectionModel().getSelectedItem());
            product.setPopust(popustCheck.isSelected());

            productRepository.addProduct(product);
            resetujPolja(null);
        } catch (Exception e) {
            Utils.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void resetujPolja(ActionEvent actionEvent) {
        nazivField.setText("");
        opisArea.setText("");
        cijenaField.setText("");
        kolicinaField.setText("");
        kategorijaBox.getSelectionModel().selectFirst();
        popustCheck.setSelected(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetujPolja(null);
        kategorijaBox.setItems(FXCollections.observableArrayList(Kategorija.values()));
        kategorijaBox.getSelectionModel().selectFirst();
    }
}
