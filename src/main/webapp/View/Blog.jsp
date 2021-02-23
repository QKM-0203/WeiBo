<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weibo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Blog.css">
</head>
<body>

    <div id="header">
        <ul id="header_ul">
            <li><a href="${pageContext.request.contextPath}/findBlogServlet?bossId=1">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/findInformationServlet?id=look&bossId=${name.id}">用户:${name.name}</a></li>
        </ul>
    </div>

    <div id="content">
        <div id="left">
            <ul id="left_group">
                <li><a href="${pageContext.request.contextPath}/findInformationServlet?id=look&bossId=${name.id}" methods="post">个人信息</a></li>
                <li><a>我的关注</a></li>
                <li><a>我的草稿箱</a></li>
            </ul>
        </div>
        <div id="middle">
            <div id="send_weibo">
                <form action="${pageContext.request.contextPath}/addBlogServlet" method="post" enctype="multipart/form-data">
                    <textarea name="think" id="send_input" cols="60" rows="7"></textarea>
                    <ul id="send_ul">
                        <li>
                            <div class="send-choice">
                                <input type="file" name = "filename" value = "选择图片" multiple="multiple"/><br>
                            </div>
                        </li>
                    </ul>
                    <div id="form-submit">
                        <button type="submit">发布</button>
                    </div>
                </form>
            </div>


            <div class="article_group">
                <c:forEach items="${AllBlogs}" var="blog">
                <div class="article">
                    <div class="article_top">
                        <ul>
                            <a href="${pageContext.request.contextPath}/findInformationServlet?id=look&bossId=${blog.bossId}">
                            <li><img   style="width:40px;height:40px;" src="${pageContext.request.contextPath}${blog.head}" alt=""></li>
                            <li><a>${blog.name}</a></li><br>
                            </a>
                            <li><a style="font-size: small ;margin-left:0px">${fn:substring(blog.creatAtAndName,0,19)}</a></li>


                            <c:if test="${blog.bossId != sessionScope.name.id}">

                            <li class="attention">
                                <form action="${pageContext.request.contextPath}/addAttentionServlet?peopleId=${blog.bossId}"  method="post">
                                    <input class="attention_a1"   type="button" value="+关注">
                                </form>
                                <form action="${pageContext.request.contextPath}/deleteAttentionServlet?peopleId=${blog.bossId}" method="post">
                                    <input class="attention_a2"   type="button" value="已关注">
                                </form>
                            </li>

                            </c:if>
                            <c:if test="${blog.bossId == sessionScope.name.id}">

                            <li class="delete_weibo" >
                                <div class="delete_box">
                                    <ul>
                                        <li style="border-top:0px;"><a href="${pageContext.request.contextPath}/deleteBlogServlet?time=${blog.creatAtAndName}&id=all">删除</a></li>
                                    </ul>
                                </div>
                            </li>
                            </c:if>

                        </ul>
                    </div>
                    <div class="article_content">
                        ${blog.think}<br>
                            <c:forEach  var="PicBean" items="${blog. listPic}">
                        <img style="width: 140px;height: 190px;"  src="${pageContext.request.contextPath}${PicBean.pictureUri}">
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
                        <form action="${pageContext.request.contextPath}/addCommentServlet?blogCreatAtAndName=${blog.creatAtAndName}&id=all" method="post">
                            <textarea name="comment"  cols="62" rows="3" ></textarea>
                            <button type="submit">评论</button>
                        </form>
                        <div class="reply">
                            <c:forEach items="${blog.listCom}" var="com">
                            <ul>
                                <form action="${pageContext.request.contextPath}/deleteCommentServlet?creatAt=${com.creatAt}&blogCreatAtAndName=${blog.creatAtAndName}&id=all" method="post">
                                <div class="reply_box" >
                                    <a href="${pageContext.request.contextPath}/findInformationServlet?id=look&bossId=${com.bossId}">
                                    <img   src="${pageContext.request.contextPath}${com.head}" alt="">
                                        <p class="reply_user">${com.name}:</p></a><p>${com.comment}</p>

                                </div>
                                <span class="reply_time">${com.creatAt}</span>
                                    <c:if test="${com.bossId == sessionScope.name.id}">
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
        <div id="right">
            <div class="right_header">

                <a href="${pageContext.request.contextPath}/findInformationServlet?id=look&bossId=${name.id}">
                <img class="pic"  src="${pageContext.request.contextPath}${sessionScope.name.head}" alt="">
                <p class="name" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.name.name}</p>
                </a>
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

        var attention_a1 = document.getElementsByClassName('attention_a1')
        var attention_a2 = document.getElementsByClassName('attention_a2')
        for( let i in attention_a1){
            attention_a1[i].onclick = function(){
                attention_a1[i].style.display ='none'
                attention_a2[i].style.display = 'inline-block'
            }
        }
        var attention_a1 = document.getElementsByClassName('attention_a1')
        var attention_a2 = document.getElementsByClassName('attention_a2')
        for( let i in attention_a2){
            attention_a2[i].onclick = function(){
                attention_a2[i].style.display ='none'
                attention_a1[i].style.display = 'inline-block'
            }
        }
    </script>
    <script>
        window.onload = function(){
            var delete_box = document.getElementsByClassName('delete_box')
            var delete_btn = document.getElementsByClassName('delete_weibo')
            var count = 0

            for(var i = 0; i<delete_box.length;i++){
                delete_box[i].style.display = "none"
            }

            for(let i in delete_btn){
                delete_btn[i].onclick = function(){
                    if(count % 2 === 0){
                        delete_box[i].style.display = "block"
                    }
                    else{
                        delete_box[i].style.display = "none"
                    }
                    count++
                }
            }

        }

    </script>

</body>
</html>