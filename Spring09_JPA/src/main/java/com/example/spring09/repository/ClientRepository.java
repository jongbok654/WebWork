package com.example.spring09.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring09.entity.Client;
																		//Entity에서 id 역할하는 변수의 타입 => num이 long 타입이므로 Long 삽입
public interface ClientRepository extends JpaRepository<Client,Long>{

}
