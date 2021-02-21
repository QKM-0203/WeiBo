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
            <li><a href="${pageContext.request.contextPath}/findBlogServlet?size=all">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/findInformationServlet?id=look&size=own">用户:${name.name}</a></li>
        </ul>
    </div>

    <div id="content">
        <div id="left">
            <ul id="left_group">
                <li><a href="${pageContext.request.contextPath}/findInformationServlet?id=look&size=own" methods="post">个人信息</a></li>
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
                        <!-- <li><a href="">图片</a></li>
                        <li><a href="">视频</a></li>
                        <li><a href="">话题</a></li>
                        <li><a href="">头条文章</a></li> -->
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
                            <li><img style="width:40px;height:40px;" src="${pageContext.request.contextPath}/img/head.png" alt=""></li>
                            <li><a>${blog.bossId}</a></li><br>
                            <li><a style="font-size: small ;margin-left:0px">${fn:substring(blog.creatAtAndName,0,19)}</a></li>

                            <c:if test="${blog.bossId != name.name}">


                            <li class="attention">
                                <form action="${pageContext.request.contextPath}/addAttentionServlet?peopleId=${blog.bossId}"  method="post">
                                <span class="attention_a attention_a1">+关注</span>
                                </form>
                                <form action="${pageContext.request.contextPath}/deleteAttentionServlet?peopleId=${blog.bossId}" method="post">
                                <span class="attention_a attention_a2">已关注</span>
                                </form>
                            </li>

                            </c:if>
                            <c:if test="${blog.bossId==sessionScope.name.name}">

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
                        <form action="${pageContext.request.contextPath}/addCommentServlet?blogCreatAtAndName=${blog.creatAtAndName}&id=all">
                            <textarea name="comment"  cols="62" rows="3"></textarea>
                            <button type="submit">评论</button>
                        </form>
                        <div class="reply">
                            <ul>
                                <form action="${pageContext.request.contextPath}/deleteCommentServlet?creatAt=${com.creatAt}&blogCreatAtAndName=${blog.creatAtAndName}&id=all">
                                    <div class="reply_box" >
                                        <img src="${pageContext.request.contextPath}/img/1.jpg" alt="">
                                        <p class="reply_user">：评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论
                                            评论评论评论评论评论评论评论评论评论评论评论</p>
                                    </div>

                                    <span class="reply_time">2020-02-03  12：23：33</span>

                                    <button type="button" class="reply_delete">删除</button>
                                </form>

                            </ul>
                            <c:forEach items="${blog.listCom}" var="com">
                            <ul>
                                <form action="${pageContext.request.contextPath}/deleteCommentServlet?creatAt=${com.creatAt}&blogCreatAtAndName=${blog.creatAtAndName}&id=all">
                                <div class="reply_box" >
                                    <img src="${pageContext.request.contextPath}/img/1.jpg" alt="">
                                    <p class="reply_user">${blog.bossId}:${com.comment}</p>
                                </div>

                                <span class="reply_time">${com.creatAt}</span>

                                <button type="button" class="reply_delete">删除</button>
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
                <!-- <img  src="../public/img/1.jpg" alt=""> -->
                <img class="pic"  src="${pageContext.request.contextPath}/img/2.jpg" alt="">
                <p class="name" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${name.name}</p>
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
            var attention = document.getElementsByClassName('attention')
            var attention_a1 = document.getElementsByClassName('attention_a1')
            var attention_a2 = document.getElementsByClassName('attention_a2')

            for(let i in attention){
                let count2 = 0
                attention[i].onclick = function(){
                    if(count2 % 2 === 0){
                        attention_a1[i].style.display = "none"
                        attention_a2[i].style.display = "inline"
                    }
                    else{
                        attention_a1[i].style.display = "inline"
                        attention_a2[i].style.display = "none"
                    }
                    count2++
                }
            }
        }


    </script>
    <head>
        bai<script type="text/javascript">
        function change()
        {
            var btn = document.getElementById("btn");
            btn.value="已关注";
            btn.disabled=true;
        }
    </script>
</body>
</html>