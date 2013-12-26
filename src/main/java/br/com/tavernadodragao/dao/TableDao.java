package br.com.tavernadodragao.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tavernadodragao.model.Table;

@Repository
public class TableDao extends AbstractDao<Table> {

	public TableDao() {
		super(Table.class);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		super.delete(super.find(id));
	}
}
