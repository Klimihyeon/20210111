package com.spring.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.MemberVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/root-context.xml")
@Transactional
public class TestMemberService {
	

	@Autowired
	private MemberService service;
	
	
	@Test
	public void testGetMember()throws Exception{
		String id="mimi";
		
		MemberVO member =service.getMember(id);
		
		Assert.assertEquals(id, member.getId());
	}
}









