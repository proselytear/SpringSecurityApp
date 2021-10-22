package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.calculation.MyException.CreateNewResponPeopException;
import net.proselyte.springsecurityapp.model.PerifinfoResponsible;
import net.sf.json.JSONObject;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface PerifinfoResponsibleService {

	public List<PerifinfoResponsible> findAll();

	public List<PerifinfoResponsible> findObject(long idinfo);

	public List<PerifinfoResponsible> findId(long perifinfoResponsible_id);

	public PerifinfoResponsible findPhone(String phone);

	public void save(PerifinfoResponsible perifinfoResponsible);

	public List<PerifinfoResponsible> getListForAccount(String account_card, Long groupnum);

	public PerifinfoResponsible saveResponsible(JSONObject jsonObject)
			throws CreateNewResponPeopException, CreateNewResponPeopException;

}
