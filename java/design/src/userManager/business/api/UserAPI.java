package userManager.business.api;

import userManager.vo.UserVO;

public interface UserAPI {
	//只写一个新增方法，因为数据层也只写了新增方法
	boolean create (UserVO user);
}
