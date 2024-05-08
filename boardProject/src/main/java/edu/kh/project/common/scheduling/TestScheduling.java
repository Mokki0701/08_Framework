package edu.kh.project.common.scheduling;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/* Sprung Scheduler : 
 * 	스프링에서 제공하는
 * 
 */

@Component // bean 등록
@Slf4j
public class TestScheduling {

	// @Scheduled() 매개 변수
	
	// 1) fixedDelay : 
	
	// 2) fixedRate :
	
	// 		   cron=”초 분 시 일 월 요일 [년도]” 
//	@Scheduled(cron = "0 * * * * *") // 매 0초 마다 수행 (1분 마다)
//	@Scheduled(cron = "0/10 * * * * *") // 0초부터 10초마다 수행
//	@Scheduled(cron = "59 59 23 * * *") // 다음 날 되기 1초 전	
	public void testMethod() {
		
		log.info("스케쥴러 테스트 중...");
		
	}
	
}












