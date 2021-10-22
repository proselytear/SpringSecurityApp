package net.proselyte.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.LogFileDao;
import net.proselyte.springsecurityapp.model.LogFile;

@Service
public class LogFileServiceImpl implements LogFileService {

	@Autowired
	private LogFileDao lofFileDao;

	@Override
	public void save(LogFile logfile) {
		lofFileDao.save(logfile);
	}

}
