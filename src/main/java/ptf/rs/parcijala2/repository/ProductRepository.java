package ptf.rs.parcijala2.repository;

import ptf.rs.parcijala2.models.Product;

import java.util.List;

public interface ProductRepository {
    void addProduct(Product prodcut);
    List<Product> getAll();
}
