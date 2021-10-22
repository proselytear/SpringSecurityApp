package net.proselyte.springsecurityapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.LogFile;
@Repository
public interface LogFileDao extends JpaRepository<LogFile, Long> {


}

