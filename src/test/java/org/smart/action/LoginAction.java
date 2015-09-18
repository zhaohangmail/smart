/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.action;

import java.util.Map;

import org.smart.framework.dao.DatabaseHelper;
import org.smart.framework.mvc.annotation.Action;
import org.smart.framework.mvc.annotation.Request.Get;
import org.smart.framework.mvc.annotation.Request.Post;
import org.smart.framework.mvc.bean.Params;
import org.smart.framework.mvc.bean.Result;
import org.smart.framework.mvc.bean.View;
import org.smart.service.LoginService;

/**
 * @author zhaohang
 *
 */
@Action
public class LoginAction {
	
	@Get("/login")
	public View login(Params param) {
		
		Map<String,Object> map = new LoginService().login(param);
		
		return new View("login.vm").data("data", map);
		
	}
	@Get("/logout")
	public View logout(Params param) {
		
		Map<String,Object> map = new LoginService().login(param);
		
		return new View("logout").data("data", map);
		
	}
	@Post("/add")
	public View add(Params param) {
		
		DatabaseHelper.add("users", param);
		
		return new View("login.vm").data("data", param);
	}
	@Get("/add1")
	public View add1(Params param) {
		
		DatabaseHelper.add("users", param);
		
		return new View("login.vm").data("data", param);
	}
	@Get("/update")
	public View update(Params param) {
		
		DatabaseHelper.update(" update users set age = :age where id = :id ", param);
		
		return new View("login.vm").data("data", param);
	}
	@Post("/pager")
	public Result pager(Params param) {
		return new Result(true).data(DatabaseHelper.pager(" select * from users ", param));
	}
	@Get("/pager1")
	public Result pager1(Params param) {
		return new Result(true).data(DatabaseHelper.pager(" select * from users ", param));
	}
}





