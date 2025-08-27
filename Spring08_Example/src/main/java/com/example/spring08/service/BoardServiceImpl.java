package com.example.spring08.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;
import com.example.spring08.dto.CommentDto;
import com.example.spring08.repository.BoardDao;
import com.example.spring08.repository.CommentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	//하나의 서비스에서 여러개의 dao 에 의존하는 경우도 많다
	private final BoardDao boardDao;
	private final CommentDao commentDao;
	
	
	// pageNum 또는 keyword 에 해당하는 글목록과 추가 정보를 BoardListResponse 객체에 담아서 
	// 리턴하는 메소드 
	@Override
	public BoardListResponse getBoardList(int pageNum, BoardDto dto) {
		
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=5;
		
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;

		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT; //공차수열
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT; //등비수열 
		
		//하단 시작 페이지 번호 (정수를 정수로 나누면 소수점이 버려진 정수가 나온다)
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	
		
		//전체 글의 갯수
		int totalRow = boardDao.getCount(dto);
		
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
				
		if(endPageNum > totalPageCount) {
			endPageNum=totalPageCount;
		}
		
				
		//startRPwNum 과 endROwNum 을 BoardDto 객체에 담아서
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
				
		//글 목록 받아오기
		List<BoardDto> list=boardDao.selectPage(dto);
		
		/*
		 * query 문자열을 미리 구성해서 view page 에 전달하고자 한다
		 * 
		 * 검색 키워드가 없으면 query="" 빈 문자열
		 * 검색 키워드가 있으면 query="&search=검색조건&&keyword=검색어" 형식의 문자열
		 */
		String query="";
		if(dto.getKeyword() != null) {
			query="search="+dto.getSearch()+ "&keyword=" + dto.getKeyword();
		}
		//한줄 coding 으로 BoardListResponse 객체를 만들어서 리턴하기
		return BoardListResponse.builder()
				.list(list)
				.pageNum(pageNum)
				.keyword(dto.getKeyword())
				.search(dto.getSearch())
				.query(query)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.totalRow(totalRow)
				.build();
	}

	@Override
	public void createdContent(BoardDto dto) {
		boardDao.insert(dto);
		//글 정보
	}

	@Override
	public BoardDto getDetail(BoardDto dto) {
		
		return boardDao.getByDto(dto);
	}

	@Override
	public List<CommentDto> getComments(int parentNum) {

		return commentDao.selectList(parentNum);
	}

	@Override
	public void createComment(CommentDto dto) {
		//저장할 댓글의 pk 를 미리 받아낸다
		int num=commentDao.getSequence();
		dto.setNum(num);
		//만일 원글의 댓글이면
		if(dto.getGroupNum() == 0) {
			dto.setGroupNum(num); //원글의 댓글은 자신의 글 번호가 댓글의 그룹번호이고
		}
		//댓글 작성자를 얻어내서 dto에 담는다
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		//대댓글이면 이미 dto에 댓글의 그룹번호가 들어 있따
		commentDao.insert(dto);
	}

	@Override
	public void updateComment(CommentDto dto) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 돌일하지 않으면 예외를 발생시킨다
				String writer = commentDao.getByNum(dto.getNum()).getWriter();
				String userName = SecurityContextHolder.getContext().getAuthentication().getName();
				if(!writer.equals(userName)) {
					throw new RuntimeException("님의 글은 수정할 수 없습니다!");
				}
				//글 삭제하기
				commentDao.update(dto);
		
	}

	@Override
	public void deleteComment(int num) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 돌일하지 않으면 예외를 발생시킨다
		String writer = commentDao.getByNum(num).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("님의 글은 지울 수 없습니다!");
		}
		//글 삭제하기
		commentDao.delete(num);
	}

	@Override
	public void deleteContent(int num) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 동일하지 않으면 예외를 발생시킨다
		String writer =boardDao.getByNum(num).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("님의 글을 지울 수는 없습니다");
		}
		boardDao.delete(num);
		
		
	}

	@Override
	public BoardDto getData(int num) {
		
		return boardDao.getByNum(num);
	}

	@Override
	public void updateContent(BoardDto dto) {
		
		String writer =boardDao.getByNum(dto.getNum()).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("님의 글을 지울 수는 없습니다");
		}
		int rowCount=boardDao.update(dto);
		if(rowCount ==0) {
			throw new RuntimeException("글 수정 실패");
		}
	}

}