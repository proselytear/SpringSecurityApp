package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.calculation.JsonConvert.Convert;
import net.proselyte.springsecurityapp.calculation.MyException.AddDeleteException;
import net.proselyte.springsecurityapp.calculation.MyException.CreateMaineqException;
import net.proselyte.springsecurityapp.dao.EventListDao;
import net.proselyte.springsecurityapp.dao.MaineqDao;
import net.proselyte.springsecurityapp.dao.PerifinfoDao;
import net.proselyte.springsecurityapp.model.EventList;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.valid.ValidParametr;
import net.sf.json.JSONObject;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class PerifinfoServiceImpl implements PerifinfoService {

	@Autowired
	private PerifinfoDao perifinfoDao;

	@Autowired
	private EventListDao eventListDao;

	@Autowired
	private MaineqDao maineqDao;

	public Perifinfo findObject(long idinfo) {
		return perifinfoDao.findObject(idinfo);
	}

	public List<Perifinfo> findSensor(long idinfo) {
		return perifinfoDao.findSensor(idinfo);
	}

	@Override
	public List<Perifinfo> findKey(long idinfo) {
		// TODO Auto-generated method stub
		return perifinfoDao.findKey(idinfo);
	}

	public List<Perifinfo> findBan() {
		return perifinfoDao.findBan();
	}

	public Perifinfo findBanIdinfo(long idinfo) {
		return perifinfoDao.findBanIdinfo(idinfo);
	}

	public Perifinfo findBanIdinfoStand(long idinfo) {
		return perifinfoDao.findBanIdinfoStand(idinfo);
	}

	public List<Perifinfo> findBanIdinfoFull(long idinfo) {
		return perifinfoDao.findBanIdinfoFull(idinfo);
	}

	public List<Perifinfo> findBanFull() {
		return perifinfoDao.findBanFull();
	}

	public List<Perifinfo> findBanIdinfoFullObj(long idinfo) {
		return perifinfoDao.findBanIdinfoFullObj(idinfo);
	}

	public List<Perifinfo> findSensorFull() {
		return perifinfoDao.findSensorFull();
	}

	public List<Perifinfo> getIdStand(String accountid, Integer grouprel, Integer paramnum) {
		return perifinfoDao.getIdStand(accountid, grouprel, paramnum);
	}

	public List<Perifinfo> getIdStandGroup(String accountid, Integer grouprel) {
		return perifinfoDao.getIdStandGroup(accountid, grouprel);
	}

	public List<Perifinfo> getIdStandObject(String accountid) {
		return perifinfoDao.getIdStandObject(accountid);
	}

	public Perifinfo getIdAlarm(@Param("accountid") String accountid, @Param("grouprel") Integer grouprel) {
		return perifinfoDao.getIdAlarm(accountid, grouprel);
	}

	public Perifinfo getGroup(@Param("obinfoid") Long obinfoid, @Param("grouprel") Integer grouprel) {

		return perifinfoDao.getGroup(obinfoid, grouprel);
	}

	public Perifinfo getGroupCreateMaineq(Long obinfoid, Integer grouprel) throws CreateMaineqException {
		Perifinfo perifinfo = perifinfoDao.getGroup(obinfoid, grouprel);

		if (perifinfo != null) {
			String message = "Уже создана группа на данном объекте";
			throw new CreateMaineqException(message);

		} else {
			// создаем новую группу для указанного объекта
			perifinfo = new Perifinfo();
			perifinfo.setGrouprel(grouprel);
			perifinfo.setObinfoid(obinfoid);
			perifinfo.setInfotype(0);
			perifinfo.setParamnum(grouprel);
			perifinfo.setPerifname("GROUP ".concat(grouprel.toString()));

			perifinfoDao.save(perifinfo);
		}
		return perifinfo;

	}

	@Override
	public void save(Perifinfo perifinfo) {
		perifinfoDao.save(perifinfo);
	}

	@Transactional
	public void save(List<Perifinfo> perifinfo) {
		perifinfoDao.save(perifinfo);
	}

	@Transactional
	public void save(List<Perifinfo> perifinfo, EventList eventlistStand) {
		eventListDao.save(eventlistStand);
		perifinfoDao.save(perifinfo);
	}

	public List<Perifinfo> getGroupbyIdinfo(Long idinfo) {
		return perifinfoDao.getGroupbyIdinfo(idinfo);
	}

	public List<Perifinfo> getZonebyIdinfo(Long idinfo) {
		return perifinfoDao.getZonebyIdinfo(idinfo);
	}

	@Override
	public List<Perifinfo> getParamnum(String accountid, Integer grouprel) {
		// TODO Auto-generated method stub

		return perifinfoDao.getParamnum(accountid, grouprel);
	}

	@Override
	public Perifinfo getParamnumOne(String accountid, Integer grouprel, Integer paramnum) {
		// TODO Auto-generated method stub
		return perifinfoDao.getParamnumOne(accountid, grouprel, paramnum);
	}

	@Override
	@Transactional
	public void delete(Perifinfo perifinfo) {
		perifinfoDao.delete(perifinfo);
	}

	@Override
	public void saveGroup(Perifinfo perifinfo) {
		perifinfoDao.save(perifinfo);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addDeleteRerifinfo(JSONObject jsonObject, long ideqval, String nameFunc, String accountKardSet)
			throws AddDeleteException {
		// Добавляем шлейф

		String message = "";

		String sensor_typeSet_value = Convert.JsonToString("sensor_typeSet_value", jsonObject);
		String perifdataKardSet = Convert.JsonToString("perifdataKardSet", jsonObject);
		String paramnumPlumeSet = Convert.JsonToString("paramnumPlumeSet", jsonObject);
		String grouprel = Convert.JsonToString("grouprel", jsonObject);
		String mondWithPC = Convert.JsonToString("mondWithPC", jsonObject);
		String mondOnPC = Convert.JsonToString("mondOnPC", jsonObject);

		if (nameFunc.equals("addPlumeSet")) {
			if (!ValidParametr.validParamnum(paramnumPlumeSet)) {

				message = "Номер шлейфа должен быть в диапазоне 1..32";
				throw new AddDeleteException(message);
			} else if (perifdataKardSet.equals("")) {

				message = "Заполните описание шлейфа";
				throw new AddDeleteException(message);
			} else {

				Perifinfo perifinfoOne = this.getParamnumOne(accountKardSet, Integer.parseInt(grouprel),
						Integer.parseInt(paramnumPlumeSet));

				if (perifinfoOne == null) {
					// если данный шлейф не найден в БД то добавляем новый
					perifinfoOne = new Perifinfo();

				}

				perifinfoOne.setGrouprel(Integer.parseInt(grouprel));
				perifinfoOne.setSensorType(Integer.parseInt(sensor_typeSet_value));
				perifinfoOne.setInfotype(1);// infotype=1 for plume
				perifinfoOne.setPerifdata(perifdataKardSet);
				perifinfoOne.setObinfoid(ideqval);
				perifinfoOne.setParamnum(Integer.parseInt(paramnumPlumeSet));

				if (perifinfoOne.getPerifname() == null)
					perifinfoOne.setPerifname("");

				this.save(perifinfoOne);

			}
		} else {

			Perifinfo perifinfoOne = this.getParamnumOne(accountKardSet, Integer.parseInt(grouprel),
					Integer.parseInt(paramnumPlumeSet));
			if (perifinfoOne == null) {

				message = "Данный шлейф не найден ";
				throw new AddDeleteException(message);

			} else {
				if (perifinfoOne.getPerifname().equals("") || perifinfoOne.getPerifname() == null) {

					this.delete(perifinfoOne);
					// Если название введенное с прибора Perifname не введено то удаляем строку

				} else {
					//// Если название введенное с прибора Perifname введено то обновляем строку
					//// обнуляя лишь название введенное инженером
					perifinfoOne.setPerifdata(null);
					this.save(perifinfoOne);

				}
			}

		}

	}

	@Override
	public Perifinfo getPerifinfoForCard(String accountid_card, Long paramnum) {
		// TODO Auto-generated method stub
		return perifinfoDao.getPerifinfoForCard(accountid_card, paramnum);
	}

	public Long getIdinfoForCard(String accountid_card, Long paramnum) {
		Perifinfo perifinfo = getPerifinfoForCard(accountid_card, paramnum);
		if (perifinfo != null)
			return perifinfo.getIdinfo();
		else
			return null;
	}

	@Override
	public int getUserLast(String accaountid_card, Long groupumun) {
		return perifinfoDao.getUserLast(accaountid_card, groupumun);
	}

	@Override
	public Perifinfo getPerifinfoForZone(String accountid, Long paramnum) {
		return perifinfoDao.getPerifinfoForZone(accountid, paramnum);
	}

}
