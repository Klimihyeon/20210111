package com.spring.security;

import java.sql.SQLException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String login_id = (String) auth.getPrincipal(); // 로그인 시도한 ID를 가져온다
 		String login_pwd = (String) auth.getCredentials(); //로그인 시도한 Password 를 가져온다.
 		
 		MemberVO member=null;
 		try {
 			member = memberService.getMember(login_id);
 		}catch(SQLException e) {
 			throw new AuthenticationServiceException("Internal server error!");
 		}	
 		
 		UserDetails authUser = new User(member);
 		if(member != null && login_pwd.equals(member.getPwd()) && authUser.isEnabled()) {//로그인 성공
 			
	 		// 스프링 시큐리티 내부 클래스로 인증 토큰 생성한다.
			UsernamePasswordAuthenticationToken result =
					new UsernamePasswordAuthenticationToken(authUser.getUsername(),authUser.getPassword(),
															authUser.getAuthorities());
	 		// 전달할 내용을 설정한 후 
	         result.setDetails(authUser);
	         // 리턴한다. successHandler로 전송된다.   
            return result;
 		}else {
 			throw new BadCredentialsException("아이디 혹은 패스워드가 불일치합니다.");
 		}
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
