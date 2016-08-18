function initPageHeader(type){
	switch(type){
	   case 0:	   
	       $("<a href='/user/toMain'>河畔首页</a>").appendTo($("#page-header"));
	       $("<a href='/user/toLogin'>登录</a>").appendTo($("#page-header"));
	       break;
	   case 1:
		   $("<a href='/user/toMain'>河畔首页</a>").appendTo($("#page-header"));
	       $("<a href='/user/userInfo'>我的资料</a>").appendTo($("#page-header"));
	       $("<a href='/user/logout'>退出</a>").appendTo($("#page-header"));
	       break;
	   default :
		   break;
	}
}