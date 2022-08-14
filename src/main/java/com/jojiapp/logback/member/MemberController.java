package com.jojiapp.logback.member;

import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

	private Logger log = LoggerFactory.getLogger(MemberController.class);

	@GetMapping
	private void getMember() {
		log.trace("trace: member");
		log.debug("debug: member");
		log.info("info: member");
		log.warn("warn: member");
		log.error("error: member");
	}
}
