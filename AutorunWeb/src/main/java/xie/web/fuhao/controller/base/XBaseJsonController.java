package xie.web.fuhao.controller.base;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hp on 2015/10/13.
 */
public class XBaseJsonController extends XBaseController {

	protected ResponseJsonMap createResponseJsonMap() {
		ResponseJsonMap jsonMap = new ResponseJsonMap();
		return jsonMap;
	}
}
