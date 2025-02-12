package jhenriquedsm.javafxjdbc.model.services;

import jhenriquedsm.javafxjdbc.model.dao.DaoFactory;
import jhenriquedsm.javafxjdbc.model.dao.DepartmentDao;
import jhenriquedsm.javafxjdbc.model.entities.Department;

import java.util.List;

public class DepartmentService {

    private DepartmentDao dao = DaoFactory.createDepartmentDao();

    public List<Department> findAll() {
        return dao.findAll();
    }

}