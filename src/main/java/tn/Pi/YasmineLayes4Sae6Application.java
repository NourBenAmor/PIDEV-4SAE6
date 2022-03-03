package tn.Pi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import tn.Pi.Service.EmailSenderService;

@SpringBootApplication
public class YasmineLayes4Sae6Application {
@Autowired
private EmailSenderService service;
	public static void main(String[] args) {
		SpringApplication.run(YasmineLayes4Sae6Application.class, args);
	}
@EventListener(ApplicationReadyEvent.class)
public void triggerMail()
{
//service.sendSimpleEmail("medcherif.lakhoua@esprit.tn", "test", "bonjour");
}
}
