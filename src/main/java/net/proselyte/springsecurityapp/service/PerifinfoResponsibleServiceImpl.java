package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.calculation.JsonConvert.Convert;
import net.proselyte.springsecurityapp.calculation.MyException.CreateNewResponPeopException;
import net.proselyte.springsecurityapp.dao.PerifinfoResponsibleDao;
import net.proselyte.springsecurityapp.model.PerifinfoResponsible;
import net.proselyte.springsecurityapp.valid.ValidParametr;
import net.sf.json.JSONObject;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class PerifinfoResponsibleServiceImpl implements PerifinfoResponsibleService {

	@Autowired
	private PerifinfoResponsibleDao perifinfoResponsibleDao;

	@Override
	public List<PerifinfoResponsible> findAll() {
		// TODO Auto-generated method stub
		return perifinfoResponsibleDao.findAll();
	}

	@Override
	public List<PerifinfoResponsible> findObject(long idinfo) {
		// TODO Auto-generated method stub
		return perifinfoResponsibleDao.findObject(idinfo);
	}

	@Override
	public List<PerifinfoResponsible> findId(long perifinfoResponsible_id) {
		// TODO Auto-generated method stub
		return perifinfoResponsibleDao.findId(perifinfoResponsible_id);
	}

	@Override
	public PerifinfoResponsible findPhone(String phone) {
		// TODO Auto-generated method stub
		return perifinfoResponsibleDao.findPhone(phone);
	}

	@Override
	public void save(PerifinfoResponsible perifinfoResponsible) {
		// TODO Auto-generated method stub
		perifinfoResponsibleDao.save(perifinfoResponsible);

	}

	@Override
	public List<PerifinfoResponsible> getListForAccount(String account_card, Long groupnum) {
		// TODO Auto-generated method stub
		return perifinfoResponsibleDao.getListForAccount(account_card, groupnum);
	}

	public PerifinfoResponsible saveResponsible(JSONObject jsonObject)
			throws CreateNewResponPeopException, CreateNewResponPeopException {

		String responsPeopFirst = Convert.JsonToString("responsPeopFirst", jsonObject);
		String responsPeopLast = Convert.JsonToString("responsPeopLast", jsonObject);
		String responsPeopPatronom = Convert.JsonToString("responsPeopPatronom", jsonObject);
		String responsPeopAddress = Convert.JsonToString("responsPeopAddress", jsonObject);
		String responsPeopPhone = Convert.JsonToString("responsPeopPhone", jsonObject);
		String responsPeopNote = Convert.JsonToString("responsPeopNote", jsonObject);

		PerifinfoResponsible responsPeop = this.findPhone(responsPeopPhone);
		PerifinfoResponsible perifinfoResponsibNew = null;

		if (responsPeop != null) {

			throw new CreateNewResponPeopException("Уже существует пользователь с данным телефоном. "
					+ responsPeop.getFirstName() + " " + responsPeop.getLastName() + " " + responsPeop.getPatronymic());
		} else if (!responsPeopPhone.equals("") && !ValidParametr.validPhone(responsPeopPhone)) {

			throw new CreateNewResponPeopException("Введите корректный номер SIM1 в формате +XXXXXXXXXXXX");
		} else {

			perifinfoResponsibNew = new PerifinfoResponsible(responsPeopFirst, responsPeopLast, responsPeopPatronom,
					responsPeopNote, responsPeopAddress, responsPeopPhone);
			this.save(perifinfoResponsibNew);
		}
		return perifinfoResponsibNew;
	}

}
