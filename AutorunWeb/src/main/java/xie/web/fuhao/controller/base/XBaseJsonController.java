package xie.web.fuhao.controller.base;

/**
 * Created by hp on 2015/10/13.
 */
public class XBaseJsonController extends XBaseController {

	protected JsonResponseBean createResponseJsonMap() {
		return createResponseJsonMap(false);
	}

	protected JsonResponseBean createResponseJsonMap(boolean initSuccess) {
		JsonResponseBean jsonMap = new JsonResponseBean();
		jsonMap.setSuccess(initSuccess);
		return jsonMap;
	}
}
