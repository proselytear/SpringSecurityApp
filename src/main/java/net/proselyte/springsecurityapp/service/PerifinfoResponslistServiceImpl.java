package net.proselyte.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.calculation.MyException.AddListResponsException;
import net.proselyte.springsecurityapp.dao.PerifinfoResponslistDao;
import net.proselyte.springsecurityapp.model.PerifinfoResponslist;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class PerifinfoResponslistServiceImpl implements PerifinfoResponslistService {

	@Autowired
	private PerifinfoResponslistDao perifinfoResponslistDao;

	@Override
	public void save(PerifinfoResponslist perifinfoResponslist) throws DataIntegrityViolationException

	{
		// TODO Auto-generated method stub
		perifinfoResponslistDao.save(perifinfoResponslist);

	}

	@Override
	public void savePerifinfoRespons(Long idinfoCard, Long perifinfoResponsibleId) throws AddListResponsException

	{

		if (idinfoCard == null) {
			throw new AddListResponsException("Объект не найден в БД");
		}
		PerifinfoResponslist pr = new PerifinfoResponslist();
		pr.setPerifinfoList_id(idinfoCard);
		pr.setResponList_id(perifinfoResponsibleId);

		this.save(pr);

	}

}
