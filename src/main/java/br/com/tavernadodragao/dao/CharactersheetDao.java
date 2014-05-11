package br.com.tavernadodragao.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tavernadodragao.model.Charactersheet;

@Repository
public class CharactersheetDao extends AbstractDao<Charactersheet> {

	public CharactersheetDao() {
		super(Charactersheet.class);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		super.delete(super.find(id));
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Charactersheet save(Charactersheet charactersheet) {
		return super.save(charactersheet);
	}
}
