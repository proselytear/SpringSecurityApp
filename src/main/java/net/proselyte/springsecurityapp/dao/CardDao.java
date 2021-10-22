package net.proselyte.springsecurityapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Card;

@Repository
public interface CardDao extends JpaRepository<Card, Long> {

	@Query(value = "SELECT * from card c inner join maineq m   on (m.ideqval=c.object_id)    inner join perifinfo p on p.obinfoid=c.object_id WHERE p.INFOTYPE=0 and idinfo=?1", nativeQuery = true)
	public Card findObject(@Param("obinfoid") long obinfoid);

	@Query(value = "SELECT * from card  where object_id=?1", nativeQuery = true)
	public Card getObjectId(@Param("objectid") long objectid);

	@Query(value = "SELECT *\r\n" + "FROM card c\r\n" + "WHERE c.CARD_ID=?1 limit 1", nativeQuery = true)
	public Long getMeineqForObjectId(@Param("card_id") long card_id);

	@Query(value = "SELECT *\r\n" + "FROM card c\r\n" + "WHERE c.OBJECT_ID=?1 limit 1", nativeQuery = true)
	public Long getCardforObject_id(@Param("OBJECT_ID") long object_id);

	@Query(value = "SELECT * FROM card c\r\n"
			+ "	WHERE c.ACCOUNTID_CARD=?1 AND groupnum=?2 limit 1", nativeQuery = true)
	public Card getCardforObjectName(@Param("ACCOUNTID_CARD") String accountid_card, @Param("groupnum") long groupnum);

}
