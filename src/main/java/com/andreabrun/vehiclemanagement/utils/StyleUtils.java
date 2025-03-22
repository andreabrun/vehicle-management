package com.andreabrun.vehiclemanagement.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Style;

public class StyleUtils {
	
	public static Style EMPTY_STYLE = new Div().getStyle();
	
	public static Style INPUT_DISABLED = new Div().getStyle().set("pointer-events", "none")
			.set("opacity", "0.7");
	
	public static Style INPUT_ENABLED = new Div().getStyle().set("pointer-events", "auto")
			.set("opacity", "1");
	
	public static Style VEHICLE_SUMMARY_VIEW_STYLE = new Div().getStyle().set("overflow", "hidden")
			.set("align-items", "center").set("margin-left", "5px").set("margin-right", "5px")
			.set("margin-bottom", "5px").set("border-radius", "15px").set("background-color", "#F5F5F5").set("text-align", "left !important");
	
	public static Style VEHICLE_SUMMARY_VIEW_COVER_IMAGE_DIV_STYLE = new Div().getStyle().set("overflow", "hidden")
			.set("vertical-align", "center").set("border-radius", "15px")
			.set("display", "flex").set("justify-content", "center");
	
	public static Style VEHICLE_DOCUMENT_VIEW_STYLE = new Div().getStyle().set("overflow", "hidden")
			.set("margin-left", "5px").set("margin-right", "5px")
			.set("margin-bottom", "5px").set("border-radius", "15px").set("background-color", "#F5F5F5");
	
	public static Style VIEW_UTILS_LABEL_STYLE = new Div().getStyle().set("font-weight", "bold").set("float", "left").set("width", "50%");
	public static Style VIEW_UTILS_VALUE_STYLE = new Div().getStyle().set("float", "right").set("width", "50%");
	public static Style VIEW_UTILS_CONTAINER_STYLE = new Div().getStyle().set("display", "flex").set("width", "100%");
	
	public static Style FORM_LEFT_BUTTON_STYLE = new Div().getStyle().set("float", "left").set("width", "40%").set("margin-left", "5px").set("margin-right", "5px");
	public static Style FORM_RIGHT_BUTTON_STYLE = new Div().getStyle().set("float", "left").set("width", "40%").set("margin-left", "5px").set("margin-right", "5px");
	public static Style FORM_CONTAINER_BUTTON_STYLE = new Div().getStyle().set("display", "flex").set("width", "100%");
	
	public static Style VEHICLE_FORM_VIEW_STYLE = new Div().getStyle().set("overflow", "hidden") .set("align-items", "center")
			.set("margin-left", "5px").set("margin-right", "5px").set("margin-bottom", "5px").set("border-radius", "15px")
			.set("width", "330px");
	
	public static Style VEHICLE_FORM_VIEW_INPUT_STYLE = new Div().getStyle().set("width", "100%");
	
	public static Style VEHICLE_GALLERY_IMAGE_DIV_STYLE = new Div().getStyle().set("overflow", "hidden")
			.set("vertical-align", "center").set("border-radius", "15px").set("display", "flex").set("justify-content", "center")
			.set("width", "260px").set("height", "200px").set("margin", "5px").set("position", "relative");
	
	public static Style DELETE_ASSET_DOCUMENT_BUTTON_STYLE = new Div().getStyle().set("position", "absolute").set("top","5px").set("right", "10px")
			.set("background-color", "red").set("border-radius", "10px").set("cursor", "pointer")
			.set("color", "white").set("border", "1px solid white");
	
	public static Style GALLERY_VIEW_IMAGE_DIV_STYLE = new Div().getStyle().set("overflow", "hidden")
			.set("vertical-align", "center").set("border-radius", "15px").set("display", "flex").set("justify-content", "center").set("margin", "5px");
	
	public static Style IMAGE_PICKER_FORM_VIEW_GALLERY_STYLE = new Div().getStyle().set("overflow", "hidden") .set("align-items", "center")
			.set("margin-left", "5px").set("margin-right", "5px").set("margin-bottom", "5px").set("border-radius", "15px")
			.set("width", "100%");
	
	public static Style IMAGE_PICKER_FORM_VIEW_STYLE = new Div().getStyle().set("width", "330px");
	
	public static Style applyStyle(Component component, Style style) {
		style.getNames().forEach(name -> component.getStyle().set(name, style.get(name)));
		return component.getStyle();
	}
}
