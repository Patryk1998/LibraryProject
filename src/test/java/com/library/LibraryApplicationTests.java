package com.library;

import com.library.dao.ReaderDao;
import com.library.dao.TitleDao;
import com.library.domain.Piece;
import com.library.domain.Reader;
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


	@Test
	public void contextLoads() {
	}



}
