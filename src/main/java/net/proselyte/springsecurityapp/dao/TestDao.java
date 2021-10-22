package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.proselyte.springsecurityapp.model.Test;

public interface TestDao extends JpaRepository<Test, Long> {
	List<Test> findAll();
}
