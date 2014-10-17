package com.transcare.usermanagement.utils;

public class ResponseObject {
	private Object result;
	private Object warning;
	private Object error;
	private Object info;
	private Object success;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getWarning() {
		return warning;
	}

	public void setWarning(Object warning) {
		this.warning = warning;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public Object getSuccess() {
		return success;
	}

	public void setSuccess(Object success) {
		this.success = success;
	}
}
