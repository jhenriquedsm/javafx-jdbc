package jhenriquedsm.javafxjdbc.model.dao;


import jhenriquedsm.javafxjdbc.db.DB;
import jhenriquedsm.javafxjdbc.model.dao.impl.DepartmentDaoJDBC;
import jhenriquedsm.javafxjdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
