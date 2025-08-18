package com.example.spring04;

//Assertions 클래스에 존재하는 모든 static 자원을 import 해서 마치 이 클래스의 멤버 메소드처럼 사용할 수 있다.
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.repository.MemberDao;

@SpringBootTest

@Transactional //트랜잭션을 관리 하도록 하고
@Rollback // insert,update,delete 등의 동작은 rollback 되도록 한다(DB 건드리는 동작들)
public class MemberDaoTest {
	//필요한 의존 객체 주입받기
	@Autowired
	private MemberDao dao;
	
	@Test
	@DisplayName("MemberDao 가 bean 으로 관리 되는지 테스트")
	void test01() {
		assertNotNull(dao);
		
	}
	
	//이름과 주소를 전달하면 전달된 정보를 DB 에 저장하고 해당 정보의 PK 를 리턴하는 메소드
	int insertFixture(String name,String addr) {
		MemberDto dto = new MemberDto();
		dto.setName("acorn");
		dto.setAddr("seoul");
		//mybatis 에서 selectKey 설정을 했기 때문에 insert 하고 나면 dto 객체에 추가한 회언의 번호가 들어 있다.
		dao.insert(dto);
		
		return dto.getNum();
	}
	
	
	@Test
	@DisplayName("회원정보 추가 테스트")
	void insert() {
		//미리 만들어둔 메소드로 데이터를 넣고 PK 를 리턴 받아서 사용한다.
		int num=insertFixture("acorn", "seoul");
		
		//데이터가 잘 들어 갔는지 select 해보기
		MemberDto found=dao.getByNum(num);
		
		//select 된 정보가 null 이 아니여야 한다.
		assertNotNull(found);
		
		//select 된 정보의 name 이 반드시 "acorn" 이어야 한다.
		assertEquals("acorn", found.getName());
		
		//select 된 정보의 addr 이 반드시 "seoul" 이어야 한다
		assertEquals("seoul", found.getAddr());
	}
	
	@Test
	@DisplayName("회원정보 삭제 테스트")
	void delete() {
		int num=insertFixture("acron", "seoul");
		//삭제하고 삭제된 row 의 갯수를 리턴 받는다.
		int rows = dao.deleteByNum(num);
		//삭제된 row 의 갯수는 반드시 1이어야 한다.
		assertEquals(1, rows);
		//select 된 정보는 반드시 null 이어야 한다.
		assertNull(dao.getByNum(num));
		
	}
	
	@Test
	@DisplayName("회원정보 업데이트 테스트")
	void update() {
		//데이터를 추가하고
		int num=insertFixture("acron", "seoul");
		//불러온다음
		MemberDto dto=dao.getByNum(num);
		//필드를 수정하고
		dto.setName("acorn2");
		dto.setAddr("seoul2");
		//수정 반영
		int rows=dao.update(dto);
		//수정된 데이터 불러와서
		MemberDto dto2=dao.getByNum(num);
		
		//삭제된 row 의 갯수는 반드시 1이어야 한다.
		assertEquals(1, rows);
		assertEquals("acorn2", dto2.getName());
		assertEquals("seoul2", dto2.getAddr());
	}
	@Test
	@DisplayName("회원 목록 테스트")
	void selectAll() {
		//데이터 추가
		int num=insertFixture("acron", "seoul");
		//회원 목록을 select 해서 목록에 방금 추가된 정보가 잘 들어 있는지 확인
		List<MemberDto> list = dao.selectAll();
		boolean isExist=false;
		for(MemberDto tmp:list) {
			if(tmp.getNum() == num) {
				isExist=true; // 하나라도 true가 나오면 값을 변수에 담고
				break;//반복문 탈출
			}
		}
		assertTrue(isExist);
	}
	
	/*
	 * 위의 selectAll() 한줄 코딩 방법
	 */
	@Test
	@DisplayName("회원 목록 테스트")
	void selectAll2() {
		//데이터 추가
		int num=insertFixture("acron", "seoul");
		//회원 목록을 select 해서 목록에 방금 추가된 정보가 잘 들어 있는지 확인
		List<MemberDto> list = dao.selectAll();
		boolean isExist = list.stream().anyMatch(item->item.getNum() == num);
		assertTrue(isExist);
	}
}
