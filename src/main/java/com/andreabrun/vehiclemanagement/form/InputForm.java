package com.andreabrun.vehiclemanagement.form;

public interface InputForm<T> {
	
	public void update(T obj);
	
	public boolean validate();
	
}
