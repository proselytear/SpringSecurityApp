package net.proselyte.springsecurityapp.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.calculation.JsonConvert.Convert;
import net.proselyte.springsecurityapp.calculation.MyException.CreateMaineqException;
import net.proselyte.springsecurityapp.dao.MaineqDao;
import net.proselyte.springsecurityapp.dao.PerifinfoDao;
import net.proselyte.springsecurityapp.model.Maineq;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.valid.TransformParametr;
import net.proselyte.springsecurityapp.valid.ValidParametr;
import net.sf.json.JSONObject;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class MaineqServiceImpl implements MaineqService {

	@Autowired
	private MaineqDao maineqDao;
	@Autowired
	private PerifinfoDao perifinfoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Maineq> getAll() {
		// TODO Auto-generated method stub
		return maineqDao.findAll();
	}

	public Maineq findObject(long idinfo) {
		return maineqDao.findObject(idinfo);
	}

	public Maineq findObject2(long idinfo) {
		return maineqDao.findObject(idinfo);
	}

	public List<Maineq> findProjects(String keyword) {
		// TODO Auto-generated method stub
		return maineqDao.findProjects(keyword);
	}

	public Maineq findMaineqByAccount(String accountId) {
		return maineqDao.findMaineqByAccount(accountId);
	}

	/**
	 * Создание настройки ППК (таблица maineq)
	 * 
	 * @param jsonObject--номер объекта(обязательное поле), две симкарты, координаты
	 *                          объекта
	 * 
	 * @return строка возвращает описание ошибки или пустой ответ в случае успешного
	 *         выполнения
	 * @throws CreateMaineqException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Maineq createMaineq(JSONObject jsonObject) throws CreateMaineqException {

		String message = "";
		String accountPCSetting = Convert.JsonToString("accountPCSetting", jsonObject);
		String sim1PCSetting = Convert.JsonToString("sim1PCSetting", jsonObject);// телефон 1
		String sim2PCSetting = Convert.JsonToString("sim2PCSetting", jsonObject);// телефон 2
		String coordinatesPCSetting = Convert.JsonToString("coordinatesPCSetting", jsonObject);// координаты объекта
																								// в
																								// мировой системе
																								// координат

		String mondWithPC = jsonObject.get("mondWithPC").toString();// время взятия под охрану объекта пнд с
		Time mondWithPCTime = Time.valueOf(mondWithPC);
		String mondOnPC = jsonObject.get("mondOnPC").toString();
		Time mondOnPCTime = Time.valueOf(mondOnPC);

		String tuesdWithPC = jsonObject.get("tuesdWithPC").toString();
		Time tuesdWithPCTime = Time.valueOf(tuesdWithPC);
		String tuesdOnPC = jsonObject.get("tuesdOnPC").toString();
		Time tuesdOnPCTime = Time.valueOf(tuesdOnPC);

		String wendesWithPC = jsonObject.get("wendesWithPC").toString();
		Time wendesWithPCTime = Time.valueOf(wendesWithPC);
		String wendesOnPC = jsonObject.get("wendesOnPC").toString();
		Time wendesOnPCTime = Time.valueOf(wendesOnPC);

		String thursdWithPC = jsonObject.get("thursdWithPC").toString();
		Time thursdWithPCTime = Time.valueOf(thursdWithPC);
		String thursdOnPC = jsonObject.get("thursdOnPC").toString();
		Time thursdOnPCTime = Time.valueOf(thursdOnPC);

		String fridayWithPC = jsonObject.get("fridayWithPC").toString();
		Time fridayWithPCTime = Time.valueOf(fridayWithPC);
		String fridayOnPC = jsonObject.get("fridayOnPC").toString();
		Time fridayOnPCTime = Time.valueOf(fridayOnPC);

		String saturdayWithPC = jsonObject.get("saturdayWithPC").toString();
		Time saturdayWithPCTime = Time.valueOf(saturdayWithPC);
		String saturdayOnPC = jsonObject.get("saturdayOnPC").toString();
		Time saturdayOnPCTime = Time.valueOf(saturdayOnPC);

		String sundayWithPC = jsonObject.get("sundayWithPC").toString();
		Time sundayWithPCTime = Time.valueOf(sundayWithPC);
		String sundayOnPC = jsonObject.get("sundayOnPC").toString();
		Time sundayOnPCTime = Time.valueOf(sundayOnPC);
		// в
		// мировой системе
		// координат
		String paramnum = Convert.JsonToString("numGroupPCSetting", jsonObject);
		String perifdata = Convert.JsonToString("perifdataPCSetting", jsonObject);
		Maineq maineq = this.findMaineqByAccount(accountPCSetting);
		Maineq maineqNew = null;

		if (maineq != null) {
			message = "Данный объект уже заведен в базе данных";
			throw new CreateMaineqException(message);

		} else if (!sim1PCSetting.equals("") && !ValidParametr.validPhone(sim1PCSetting)) {
			message = "Введите корректный номер SIM1 в формате+XXXXXXXXXXXX";
			throw new CreateMaineqException(message);
		} else if (!sim2PCSetting.equals("") && !ValidParametr.validPhone(sim2PCSetting)) {
			message = "Введите корректный номер SIM2 в формате+XXXXXXXXXXXX";
			throw new CreateMaineqException(message);
		} else if (accountPCSetting.equals("")) {
			message = "Введите номер объекта";
			throw new CreateMaineqException(message);
		} else if (paramnum.equals("")) {
			message = "Введите номер группы";
			throw new CreateMaineqException(message);
		} else if (!paramnum.equals("") && !ValidParametr.validNumGroup(paramnum)) {
			message = "Номер группы должен быть в диапазоне от 1 до 8. Вы ввели " + paramnum;
			throw new CreateMaineqException(message);
		} else if (!coordinatesPCSetting.equals("") && !ValidParametr.validCoord(coordinatesPCSetting)) {

			message = "Координаты должны быть в формате 'XX.XXXXXX YY.YYYYYY'";
			throw new CreateMaineqException(message);
		}

		else if (!ValidParametr.validAccountId(accountPCSetting)) {
			message = "Введите корректный номер объекта. Цифры и буквы от A до F. Длина поля от 4 до 16 включительно";
			throw new CreateMaineqException(message);
		} else {
			coordinatesPCSetting = TransformParametr.transformCoord(coordinatesPCSetting);
			Integer grouprel = Integer.parseInt(paramnum);// номер группы
			// если все проверки прошли успешно создаем новый объект
			maineqNew = new Maineq();
			maineqNew.setAccountid(accountPCSetting);
			maineqNew.setEqphone(sim1PCSetting);
			maineqNew.setEqphone2(sim2PCSetting);
			maineqNew.setGeopoint(coordinatesPCSetting);
			maineqNew.setTestper(55l);// Время теста 55с
			maineqNew.setLastcon(System.currentTimeMillis() / 1000);// текущее время
			maineqNew.setFirstcon(System.currentTimeMillis() / 1000);
			maineqNew.setStatusy(
					"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
			maineqDao.save(maineqNew);

			Long obinfoid = maineqNew.getIdeqval();

			Perifinfo perifinfo = perifinfoDao.getGroup(obinfoid, 1);

			if (perifinfo != null) {
				message = "Уже создана группа на данном объекте";
				throw new CreateMaineqException(message);

			} else {
				// создаем новую группу для указанного объекта
				perifinfo = new Perifinfo();
				perifinfo.setGrouprel(grouprel);
				perifinfo.setObinfoid(obinfoid);
				perifinfo.setInfotype(0);
				perifinfo.setParamnum(grouprel);
				perifinfo.setPerifname("GROUP ".concat(grouprel.toString()));
				perifinfo.setPerifdata(perifdata);
				// расписание выезда на тревоги
				perifinfo.setTimefrommonday(mondWithPCTime);
				perifinfo.setTimetomonday(mondOnPCTime);

				perifinfo.setTimefromtuesday(tuesdWithPCTime);
				perifinfo.setTimetotuesday(tuesdOnPCTime);

				perifinfo.setTimefromwednesday(wendesWithPCTime);
				perifinfo.setTimetowednesday(wendesOnPCTime);

				perifinfo.setTimefromthursday(thursdWithPCTime);
				perifinfo.setTimetothursday(thursdOnPCTime);

				perifinfo.setTimefromfriday(fridayWithPCTime);
				perifinfo.setTimetofriday(fridayOnPCTime);

				perifinfo.setTimefromsaturday(saturdayWithPCTime);
				perifinfo.setTimetosaturday(saturdayOnPCTime);

				perifinfo.setTimefromsunday(sundayWithPCTime);
				perifinfo.setTimetosunday(sundayOnPCTime);

				perifinfoDao.save(perifinfo);
			}

		}
		return maineqNew;

	}

}
