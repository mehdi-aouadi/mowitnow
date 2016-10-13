package com.xebia.mowitnow.web;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.xebia.mowitnow.mower.Commander;
import com.xebia.mowitnow.mower.Mower;
import com.xebia.mowitnow.io.Loader;

@Controller
public class MowerController implements Observer {

	private static Logger logger = LoggerFactory.getLogger(Loader.class);

	@Autowired
	private MessageSendingOperations<String> messagingTemplate;

	private Commander commander;

	@MessageMapping("/execute")
	public void execute(final String message) throws Exception {

		// Load a commander from the instructions
		commander = new Loader().fromText(message);

		// Send the inital state
		notifyAndWait("/mowers/init", commander);

		// Add an observer (this) for each mower
		for (Mower mower : commander.getMowers()) {
			mower.addObserver(this);
		}

		// Start mowing
		commander.startMowing();
		
	}

	@MessageMapping("/receive")
	public synchronized void receive() {
		notify();
	}

	@MessageExceptionHandler(Exception.class)
	public void error(final Exception e) {
		notifyAndWait("/mowers/error", e.getMessage());
		logger.warn("An error occured", e);
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyAndWait("/mowers/update", o);
	}

	// @TODO : Check try catch block
	private synchronized void notifyAndWait(String subscription, Object o) {
		this.messagingTemplate.convertAndSend(subscription, o);
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
