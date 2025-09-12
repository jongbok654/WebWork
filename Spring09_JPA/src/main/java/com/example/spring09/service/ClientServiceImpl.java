package com.example.spring09.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring09.dto.ClientDto;
import com.example.spring09.dto.MemberDto;
import com.example.spring09.entity.Client;
import com.example.spring09.entity.Member;
import com.example.spring09.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

	private final ClientRepository clientRepo;
	@Override
	@Transactional
	public Long addClient(ClientDto dto) {
	
		Client saved = clientRepo.save(dto.toEntity());
		return saved.getNum();
	}

	@Transactional(readOnly =true)
	@Override
	public List<ClientDto> getClients() {
		
		List<ClientDto> list =clientRepo.findAll().stream().map(ClientDto::toDto).toList();
		return list;
	}

	@Transactional(readOnly =true)
	@Override
	public ClientDto getClient(long num) {
		//Client entity=clientRepo.findById(num).get();
		
		Client entity = clientRepo.findById(num)
				.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다 num=" + num));
		return ClientDto.toDto(entity);
	}

	@Override
	@Transactional
	public void updateBirthDay(Long num, LocalDate birthday) {
		Client entity=clientRepo.findById(num).get();
		entity.setBirthday(birthday);
		
		clientRepo.save(entity);
	}

	@Transactional
	@Override
	public void update(ClientDto dto) {
		//번호에 해당하는 entity 를 가져와서
		Client entity=clientRepo.findById(dto.getNum()).get();
		//이름과 생일을 수정(entity 를 수정하는 것 만으로 자동으로 반영된다)
		entity.setUserName(dto.getUserName());
		entity.setBirthday(dto.getBirthday());
		
	}

	@Override
	public ClientDto deleteClient(long num) {
		Client c =clientRepo.findById(num).get();

		// 번호를 이용해서 삭제 (씰패시 예외가 발생하지는 않는다
				clientRepo.deleteById(num);
				
				return ClientDto.toDto(c);
	}

}
