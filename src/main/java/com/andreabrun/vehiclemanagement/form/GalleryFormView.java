package com.andreabrun.vehiclemanagement.form;

import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.utils.MessagesUtils;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class GalleryFormView extends FlexLayout {

	public List<Image> images;
	
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 330;
	private static final int MINWIDTH_IMG = 100;
	

	public GalleryFormView() {
		super();
	}
	
	public GalleryFormView(List<Image> images) {
		setImages(images);
	}
	
	public void init() {
		
		int num = images.size();
		
		if(num == 0) {
			Div errorDiv = new Div(MessagesUtils.INFO_NO_IMAGE_FOUND);
			this.add(errorDiv);
		} else {
			
			float res = ((float)WIDTH) / num;
			float imgWidth = Math.max(res, MINWIDTH_IMG);
			
			for(Image img : images) {
				img.getStyle().set("width", "100%").set("height", "100%");
				Div imageDiv = new Div();
				imageDiv.add(img);
				StyleUtils.applyStyle(imageDiv, StyleUtils.GALLERY_VIEW_IMAGE_DIV_STYLE);
				imageDiv.getStyle().set("width", imgWidth + "px").set("height", imgWidth * 0.75 + "px");
				add(imageDiv);
			}
			
		}
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
		init();
	}
	
	public void setEventHandlers(Map<Image, ComponentEventListener<ClickEvent<Image>>> eventListeners) {
		for(Image img : images) {
			ComponentEventListener<ClickEvent<Image>> listener = eventListeners.get(img);
			if(listener != null) {
				img.addClickListener(listener);
			}
		}
	}
}
