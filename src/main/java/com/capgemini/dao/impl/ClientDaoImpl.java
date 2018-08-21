package com.capgemini.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ClientDAO;
import com.capgemini.domain.ClientEntity;

@Repository
@Transactional
public class ClientDaoImpl extends AbstractDao<ClientEntity, Long> implements ClientDAO {

}
