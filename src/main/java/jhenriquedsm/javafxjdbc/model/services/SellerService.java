package jhenriquedsm.javafxjdbc.model.services;

import jhenriquedsm.javafxjdbc.model.dao.DaoFactory;
import jhenriquedsm.javafxjdbc.model.dao.SellerDao;
import jhenriquedsm.javafxjdbc.model.entities.Seller;

import java.util.List;

public class SellerService {

    private SellerDao dao = DaoFactory.createSellerDao();

    public List<Seller> findAll() {
        return dao.findAll();
    }

    public void saveOrUpdate(Seller seller) {
        if (seller.getId() == null) {
            dao.insert(seller);
        } else {
            dao.update(seller);
        }
    }

    public void remove(Seller seller) {
        dao.deleteById(seller.getId());
    }
}