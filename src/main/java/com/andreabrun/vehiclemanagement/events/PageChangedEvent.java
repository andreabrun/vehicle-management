package com.andreabrun.vehiclemanagement.events;

import org.springframework.context.ApplicationEvent;

public class PageChangedEvent extends ApplicationEvent {

	public PageChangedEvent(Object source) {
		super(source);
	}

}
