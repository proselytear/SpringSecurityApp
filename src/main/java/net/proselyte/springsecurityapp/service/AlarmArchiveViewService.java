package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.model.AlarmArchiveView;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface AlarmArchiveViewService {

	public void save(AlarmArchiveView alarmcancelnew);

	public void saveList(List<AlarmArchiveView> alarm);

	public List<AlarmArchiveView> findAlarmArchive();

	public List<AlarmArchiveView> findObjectData(@Param("dataEvdata") String dataEvdata,
			@Param("dataEvdata") String dataEvdata1);

	// Непрочитанные тревоги оператором которые в архиве находятся
	public List<AlarmArchiveView> findAlarmNotRead();

}
