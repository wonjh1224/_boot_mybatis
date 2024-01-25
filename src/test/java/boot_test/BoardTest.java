//package boot_test;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.example.demo.domain.BoardVO;
//import com.example.demo.repository.BoardMapper;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
//public class BoardTest {
//
//	@Autowired
//	private BoardMapper mapper;
//	
//	@Test
//	public void insertBoard() {
//		for(int i=0; i<300; i++) {
//			BoardVO bvo = new BoardVO();
//			bvo.setTitle("Test Title " + i);
//			bvo.setWriter("Test Writer " + i);
//			bvo.setContent("Test Content " + i);
//			mapper.insert(bvo);
//		}
//	}
//	
//
//	
//}
