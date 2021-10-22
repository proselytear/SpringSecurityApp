package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.model.CardNotation;

public interface CardNotationDao extends JpaRepository<CardNotation, Long> {

	@Query(value = "SELECT  cn.cardnotation_id, cn.card_id, cn.notation,cn.notation_date, c.CARD_ID from cardnotation  cn inner join card c  ON cn.card_id=c.CARD_ID inner join perifinfo p on p.obinfoid=c.object_id where idinfo=?1", nativeQuery = true)
	public List<CardNotation> findObject(@Param("idinfo") long idinfo);

}
