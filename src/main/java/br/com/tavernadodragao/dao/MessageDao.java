package br.com.tavernadodragao.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tavernadodragao.model.Message;

@Repository
public class MessageDao extends AbstractDao<Message>{

	public MessageDao() {
		super(Message.class);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		super.delete(super.find(id));
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Message save(Message message) {
		return super.save(message);
	}
}
