
package common.login.session;

import java.util.HashMap;

public class SessionFixedMap extends HashMap<String, String> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String[] names = {
			SessionInfo.EMP_CODE,
			SessionInfo.EMP_NAME,
			SessionInfo.DEPT_CODE,
			SessionInfo.DEPT_NAME };

	public SessionFixedMap() {
		for (int i = 0; i < names.length; i++) {
			super.put(names[i], "");
		}
	}

	@Override
	public String put(String key, String obj) {
		if (super.containsKey(key)) return super.put(key, obj);
		return null;
	}
}
