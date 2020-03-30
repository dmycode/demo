package com.example.demo.vo.web;

/**
 * 
    * @ClassName: ServiceResult
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author dy
    * @date 2020年3月27日
 */
public class ServiceResult {
	/**
	 * 成功
	 */
	public static final int STATE_SUCCESS = 0;
	/**
	 * 应用异常
	 */
	public static final int STATE_APP_EXCEPTION = 1;

	private Object result;

	private int state;

	private String resCode;
	private String msg;

	/**
	 * 一个请求的调用链，唯一
	 */
	private String trackId;

	private int type = 0;
	private int pageStatus = 0;

	public int getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(int pageStatus) {
		this.pageStatus = pageStatus;
	}

	public ServiceResult() {
	}

	public ServiceResult(Object result, int state) {
		this.result = result;
		this.state = state;
		// 不需要前端国际化转换
		this.type = 1;
	}

	public ServiceResult(Object result, int state, int type) {
		this.result = result;
		this.state = state;
		this.type = type;
	}

	public ServiceResult(int state) {
		this.result = "";
		this.state = state;
	}

	public Object getResult() {
		return result == null ? "" : result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public void setSuccess() {
		this.state = STATE_SUCCESS;
	}

	public void setFail() {
		this.state = STATE_APP_EXCEPTION;
	}

	public void setFail(String message) {
		this.state = STATE_APP_EXCEPTION;
		this.result = message;
	}

	public void setAppFail(String message, int type) {
		this.type = type;
		this.state = STATE_APP_EXCEPTION;
		this.result = message;
	}

	public String getTrackId() {
		return this.trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public boolean isSuccess() {
		return this.state == STATE_SUCCESS;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ServiceResult [result=" + result + ", state=" + state
		        + ", resCode=" + resCode + ", trackId=" + trackId + "]";
	}

}
