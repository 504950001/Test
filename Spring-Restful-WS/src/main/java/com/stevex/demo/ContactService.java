package com.stevex.demo;

import java.util.List;

public interface ContactService {
	public List<Contact> findAll();

	public Contact findById(Long id);

	public Contact save(Contact contact);

	public long countAll();
	
	public void delete(Contact contact);
}
