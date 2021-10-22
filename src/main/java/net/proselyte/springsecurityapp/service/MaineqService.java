package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.calculation.MyException.CreateMaineqException;
import net.proselyte.springsecurityapp.model.Maineq;
import net.sf.json.JSONObject;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface MaineqService {

	List<Maineq> getAll();

	public Maineq findObject(long idinfo);

	public Maineq findObject2(long idinfo);

	public List<Maineq> findProjects(String accountId);

	public Maineq findMaineqByAccount(@Param("accountId") String accountId);

	public Maineq createMaineq(JSONObject jsonObject) throws CreateMaineqException;

}
