
package edu.kh.project.websocket.Interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;


/*
 * WebSocketHandler가 동작하기 전/후에 
 * 연결된 클라이언트 세션을 가로채는 동작을 작성할 클래스
 */
@Component
public class SessionHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
		Map<String, Object> attributes) throws Exception {
	
		// ServerHttpRequest  : HttpServletRequest의 부모 인터페이스
		// ServerHttpResponse : HttpServletResponse의 부모 인터페이스
		// attributes : 해당 맵에 세팅된 속성(데이터)은
		// 			    다음에 동작할 Handler 객체에게 전달됨
		// 			  (HandshakeInterceptor -> Handler 데이터 전달하는 역할)
		
		// request가 참조하는 객체가
		// ServletHttpRequest로 다운캐스팅이 가능한가?
		if(request instanceof ServletServerHttpRequest) {
			
			// 다운 캐스팅
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
			
			// 웹소켓 동작을 요청한 클라이언트의 세션을 얻어옴
			HttpSession session = servletRequest.getServletRequest().getSession();
			
			// 가로챈 세션을 Handler에 전달할 수 있게 값 세팅
			attributes.put("session", session); // 흠 이거 모델이나 뭐에 넘겨야 되는거 아냐? 어떻게 전달 하고 전달 받는다는거지?
			// 이게 attributes에 추가를 하고 return 구문에 true로 설정하면 자동적으로 값을 넘기게끔 설정이 내장되어있음.
		}
		
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {

		
	}

}
