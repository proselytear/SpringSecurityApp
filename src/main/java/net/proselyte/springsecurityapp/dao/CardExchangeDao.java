package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.model.CardExchange;

public interface CardExchangeDao extends JpaRepository<CardExchange, Long>{
	@Query(value =  "SELECT  ce.cardexchange_id, exchange_description, ce.exchange_date, c.CARD_ID from cardexchange  ce inner join card c  ON ce.card_id=c.CARD_ID  inner join perifinfo p on p.obinfoid=c.object_id where idinfo=?1",  nativeQuery = true)
	public List <CardExchange> findObject(@Param("idinfo") long idinfo);
}
