<%--
  Created by IntelliJ IDEA.
  User: qikaimeng
  Date: 2021/2/22
  Time: 下午8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weibo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Information.css">
</head>
<body>

<div class="box">
    <nav>
        <ul class="nav1">
            <li> <a href="${pageContext.request.contextPath}/findBlogServlet?bossId=1">首页</a>
            <li><a href="${pageContext.request.contextPath}/findInformationServlet?id=look&bossId=${name.id}">用户:${name.name}</a>
        </ul>
    </nav>

    <div class="header">
        <img  src="${pageContext.request.contextPath}/img/2.jpg" alt="">
        <input type="file" id="btn_file" style="display:none">
    </div>
    <div id="right">
        <div class="right_header">

            <img class="pic"  onclick="popBox()" style="width: 100px ; height:100px; " src="${pageContext.request.contextPath}${blogs.get(0).head}" alt="">
            <p class="name" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${userInformation.name}</p>


        </div>
    </div>

    <div class="left-box">
        <li class="td">
            <strong>${sum.attention}</strong>
            <span>关注</span>
        </li>
        <li class="td">
            <strong>${sum.fans}</strong>
            <span>粉丝</span>
        </li>
        <li class="td" id="td1">
            <strong>${sum.totalBlogs}</strong>
            <span>微博</span>
        </li>

    </div>
        <div id="information">
           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <input type="hidden" name="bossId" value="${name.id}">
                <tr class="information_tr">
                    <td>昵称:
                        <span class="form1">${userInformation.name}</span>
                        </td>
                </tr>
                <tr class="information_tr">
                    <td>性别：
                        <span class="form1">${userInformation.sex}</span>

                    </td>
                </tr>
                <tr class="information_tr">
                    <td>年龄：
                        <span class="form1">${userInformation.age}</span>
                        </td>
                </tr>
                <tr class="information_tr"><td>出生地：
                    <span class="form1">${userInformation.birthArea}</span>
                    </td>
                </tr>
                <tr class="information_tr">
                    <td>生日：
                        <span class="form1">${userInformation.birth}</span>
                       </td>
                </tr>
                <tr class="information_tr">
                    <td>qq：
                        <span class="form1">${userInformation.qq}</span>
                       </td>
                </tr>
                <tr class="information_tr">
                    <td>Email:
                        <span class="form1">${userInformation.mail}</span>
                       </td>
                </tr>
                <tr class="information_tr">
                    <td>签名:
                        <span class="form1">${userInformation.own}</span>
                        </td>
                </tr>

            </table>
        </div>
    <div id="content">
        <div id="middle">
            <div class="article_group">
                <c:forEach items="${blogs}" var="blog">
                    <div class="article">
                        <div class="article_top">
                            <ul>
                                <li><img href="findInformationServlet?id=look&bossId=${blog.bossId}"style="width:40px;height:40px;" src="${pageContext.request.contextPath}${blog.head}" alt=""></li>
                                <li><a>${blog.name}</a></li><br>
                                <li><a style="font-size: small ;margin-left:0px">${fn:substring(blog.creatAtAndName,0,19)}</a></li>
                            </ul>
                        </div>
                        <div class="article_content">
                                ${blog.think}<br>
                            <c:forEach  var="PicBean" items="${blog.listPic}">
                                <img style="width: 140px;height: 190px;" src="${pageContext.request.contextPath}${PicBean.pictureUri}">
                            </c:forEach>
                        </div>
                        <div class="article_bottom">
                            <ul>
                                <li style="text-align: center; border-right: 1px solid #808080;">
                                    <a  class="pingLun" >评论</a>
                                    <a  class="hold" >^</a>
                                </li>
                                <li><a href="">点赞</a></li>
                            </ul>
                        </div>
                        <div class="comment">
                            <form action="${pageContext.request.contextPath}/addCommentServlet?blogCreatAtAndName=${blog.creatAtAndName}&id=own" method="post">
                                <textarea name="comment" id="send_input" cols="70" rows="3"></textarea>
                                <button type="submit">评论</button>
                            </form>
                            <div class="reply">
                                <c:forEach items="${blog.listCom}" var="com">
                                    <ul>
                                        <form action="${pageContext.request.contextPath}/deleteCommentServlet?creatAt=${com.creatAt}&blogCreatAtAndName=${blog.creatAtAndName}&id=own" method="post">
                                            <div class="reply_box" >
                                                <img href="findInformationServlet?id=look&bossId=${com.bossId}" src="${pageContext.request.contextPath}${com.head}" alt="">
                                                <p class="reply_user">${com.name}:${com.comment}</p>
                                            </div>
                                            <span class="reply_time">${com.creatAt}</span>
                                            <c:if test="${com.bossId == name.id}">
                                                <button type="submit" class="reply_delete">删除</button>
                                            </c:if>
                                        </form>

                                    </ul>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>

    </div>
</div>

<script>
    var comment = document.getElementsByClassName('comment')
    var hold = document.getElementsByClassName('hold')
    var pinglun = document.getElementsByClassName('pingLun')
    for( let i in pinglun){
        pinglun[i].onclick = function(){
            comment[i].style.display = "inline-block"
            pinglun[i].style.display = "none"
            hold[i].style.display = "inline-block"
        }
    }

    var comment = document.getElementsByClassName('comment')
    var hold = document.getElementsByClassName('hold')
    var pinglun = document.getElementsByClassName('pingLun')
    for( let i in hold){
        hold[i].onclick = function(){
            comment[i].style.display = "none"
            pinglun[i].style.display = "inline-block"
            hold[i].style.display = "none"
        }
    }


</script>

</body>
</html>