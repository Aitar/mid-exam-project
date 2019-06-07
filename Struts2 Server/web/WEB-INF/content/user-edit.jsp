<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title> 编辑 ID 为 <s:property value="id"/> 的用户 </title>
    <link href="<%=request.getContextPath() %>/css/demo.css"
          rel="stylesheet" type="text/css" />
</head>
<body>
<s:form method="post" action="%{#request.contextPath}/user/%{id}">
    <!-- 增加 _method 请求参数，参数值为 put 用于模拟 PUT 操作 -->
    <s:hidden name="_method" value="PUT" />
    <table>
        <s:textfield name="id" label="用户 ID" disabled="true"/>
        <s:textfield name="username" label="用户名"/>
        <s:textfield name="password" label="密码" />
        <s:textfield name="nickname" label="昵称" />
        <tr>
            <td colspan="2">
                <s:submit value="修改"/>
            </td>
    </table>
</s:form>
<a href="<%=request.getContextPath() %>/book"> 返回首页 </a>
</body>
</html>