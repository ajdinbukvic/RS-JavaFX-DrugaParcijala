package ptf.rs.parcijala2.repository;

import ptf.rs.parcijala2.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JPARepository implements ProductRepository {
    private final EntityManager em;

    public JPARepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addProduct(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    @Override
    public List<Product> getAll() {
        CriteriaQuery<Product> cq = em.getCriteriaBuilder().createQuery(Product.class);
        cq.select(cq.from(Product.class));
        return em.createQuery(cq).getResultList();
    }
}
