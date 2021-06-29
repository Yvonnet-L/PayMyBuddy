package com.oc.ly.PayMyBuddy;

import static org.assertj.core.api.Assertions.assertThat;

import com.oc.ly.PayMyBuddy.controller.HomeController;
import com.oc.ly.PayMyBuddy.controller.TransactionController;
import com.oc.ly.PayMyBuddy.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PayMyBuddyApplicationTests {

	@Autowired
	private HomeController homeController;

	@Autowired
	private TransactionController transactionController;

	@Test
	void contextLoads() {
		assertThat(homeController).isNotNull();
		assertThat(transactionController).isNotNull();
	}

}
