package com.library;

import com.library.dao.TitleDao;
import com.library.domain.Piece;
import com.library.domain.Title;
import org.hibernate.cache.spi.TimestampsRegion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTests {

	@Autowired
	private TitleDao titleDao;

	@Test
	public void contextLoads() {
	}

//	@Test
//	public void testTitleDao() {
//		//Given
//		Piece piece1 = new Piece("rental");
//		Piece piece2 = new Piece("rental");
//
//
//		Title title = new Title("title", "author", "spend date");
//		title.getPieces().add(piece1);
//		title.getPieces().add(piece2);
//		piece1.setTitle(title);
//		piece2.setTitle(title);
//
//		titleDao.save(title);
//
//
//		System.out.println("!!!!!!!!!!!!!!!!!!!");
//		titleDao.findByTitle("title").getPieces().stream()
//		.forEach(piece -> System.out.println(piece.getPieceId() + piece.getStatus()));
//
//
//
//
//	}

}
