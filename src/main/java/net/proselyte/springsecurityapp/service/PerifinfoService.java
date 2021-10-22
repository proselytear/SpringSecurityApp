package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.calculation.MyException.AddDeleteException;
import net.proselyte.springsecurityapp.calculation.MyException.CreateMaineqException;
import net.proselyte.springsecurityapp.model.EventList;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.sf.json.JSONObject;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface PerifinfoService {

	public Perifinfo findObject(long idinfo);

	public List<Perifinfo> findSensor(long idinfo);

	public List<Perifinfo> findKey(long idinfo);

	public List<Perifinfo> findBan();

	Perifinfo findBanIdinfo(long idinfo);

	public Perifinfo findBanIdinfoStand(long idinfo);

	List<Perifinfo> findBanIdinfoFull(long idinfo);

	List<Perifinfo> findBanFull();

	List<Perifinfo> findBanIdinfoFullObj(long idinfo);

	public List<Perifinfo> findSensorFull();

	public List<Perifinfo> getIdStand(String accountid, Integer grouprel, Integer paramnum);

	public List<Perifinfo> getIdStandGroup(String accountid, Integer grouprel);

	public List<Perifinfo> getIdStandObject(String accountid);

	public Perifinfo getIdAlarm(@Param("accountid") String accountid, @Param("grouprel") Integer grouprel);

	public List<Perifinfo> getGroupbyIdinfo(Long idinfo);

	public List<Perifinfo> getZonebyIdinfo(Long idinfo);

	public Perifinfo getGroup(@Param("obinfoid") Long obinfoid, @Param("grouprel") Integer grouprel);

	public Perifinfo getGroupCreateMaineq(Long obinfoid, Integer grouprel) throws CreateMaineqException;

	public List<Perifinfo> getParamnum(String accountid, Integer grouprel);

	public void save(Perifinfo perifinfo);

	public void saveGroup(Perifinfo perifinfo);

	public void save(List<Perifinfo> perifinfo);

	public void save(List<Perifinfo> perifinfo, EventList eventlistStand);

	public void delete(Perifinfo perifinfo);

	public Perifinfo getParamnumOne(String accountid, Integer grouprel, Integer paramnum);

	public void addDeleteRerifinfo(JSONObject jsonObject, long ideqval, String nameFunc, String accountKardSet)
			throws AddDeleteException;

	public Perifinfo getPerifinfoForCard(String accountid_card, Long paramnum);

	public Long getIdinfoForCard(String accountid_card, Long paramnum);

	public int getUserLast(String accaountid_card, Long groupumun);

	public Perifinfo getPerifinfoForZone(String accountid, Long paramnum);

}
