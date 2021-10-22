package net.proselyte.springsecurityapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.CardPhoto;
@Repository
public interface CardPhotoDao extends JpaRepository<CardPhoto, Long> {
	@Query(value =  "SELECT c.CARDPHOTO_ID, c.NAME, c.PHOTO, c.CARD_ID FROM cardphoto c WHERE c.CARDPHOTO_ID=25;",  nativeQuery = true)
	CardPhoto findObject();
	


	
}

