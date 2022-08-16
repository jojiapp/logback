package com.jojiapp.logback;

import lombok.extern.slf4j.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
public class MemberAPI {

	@GetMapping
	public void a() {
		for (int i = 0; i < 100000000; i++) {
			log.info("글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자글자");
		}
	}
}
