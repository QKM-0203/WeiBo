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
                   <!--

                       头像限制为圆


                    -->
                   <img class="pic"  onclick="popBox()" style="width: 100px ; height:100px; " src="${pageContext.request.contextPath}${name.head}" alt="">
                   <p class="name" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${name.name}</p>


                   <!-- /*


                   头像更改


                   */ -->
                   <!-- /*背景层*/ -->
                   <div id="popLayer"></div>
                   <!-- /*弹出层*/ -->
                   <div id="popBox">
                       <div class="close">
                           <a onclick="closeBox()">关闭</a>
                       </div>
                       <div class="content">
                           <form action="${pageContext.request.contextPath}/modifyHeadServlet" method="post" enctype="multipart/form-data">
                               <input type="file" name = "filename" value = "选择图片">
                               <button type="submit">提交</button>
                           </form>
                       </div>
                   </div>




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
        <form action="${pageContext.request.contextPath}/updateInformationServlet">
         <div id="information">
            <a onclick="repair1()" id="wri" href="#">编辑</a>
            <button   type="submit" onclick="load1()" id="loa"  href="#">保存</button>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <input type="hidden" name="bossId" value="${name.id}">
                <tr class="information_tr">
                    <td>昵称:
                        <span class="form1">${userInformation.name}</span>
                        <input type="text" class="form_control" name="name"  value="${userInformation.name}">
                    </td>
                </tr>
                <tr class="information_tr">
                    <td>性别：
                        <span class="form1">${userInformation.sex}</span>
                        <c:if test="${userInformation.sex == null}">
                            <div class="form_control" id="sex-c">
                                <input  type="radio" name="sex" value="男" >男
                                <input  type="radio" name="sex" value="女" checked="checked">女
                            </div>
                        </c:if>

                        <c:if test="${userInformation.sex == '女'}">
                        <div class="form_control" id="sex-c">
                            <input  type="radio" name="sex" value="男" >男
                            <input  type="radio" name="sex" value="女" checked="checked">女
                        </div>
                        </c:if>

                        <c:if test="${userInformation.sex == '男'}">
                            <div class="form_control" id="sex-c">
                                <input  type="radio" name="sex" value="男" checked="checked" >男
                                <input  type="radio" name="sex" value="女">女
                            </div>

                        </c:if>
                    </td>
                </tr>
                <tr class="information_tr">
                    <td>年龄：
                        <span class="form1">${userInformation.age}</span>
                        <input type="text" class="form_control" name="age" value="${userInformation.age}" >
                    </td>
                </tr>
                <tr class="information_tr"><td>出生地：
                    <span class="form1">${userInformation.birthArea}</span>
                    <input type="text" class="form_control" name="birthArea" value="${userInformation.birthArea}"  >
                </td>
                </tr>
                <tr class="information_tr">
                    <td>生日：
                        <span class="form1">${userInformation.birth}</span>
                        <input class="form_control" type="date" name="birth" id="date"  value="${userInformation.birth}" />
                    </td>
                </tr>
                <tr class="information_tr">
                    <td>qq：
                        <span class="form1">${userInformation.qq}</span>
                        <input type="text" class="form_control" name="qq" value="${userInformation.qq}" >
                    </td>
                </tr>
                <tr class="information_tr">
                    <td>Email:
                        <span class="form1">${userInformation.mail}</span>
                        <input type="text" class="form_control" name="mail" value="${userInformation.mail}" >
                    </td>
                </tr>
                <tr class="information_tr">
                    <td>签名:
                        <span class="form1">${userInformation.own}</span>
                        <input type="text" class="form_control" name="own" value="${userInformation.own}"  >
                    </td>
                </tr>

            </table>
        </div>
            ${ku}
        </form>
        <div id="content">
        <div id="middle">
            <div class="article_group">
                <c:forEach items="${blogs}" var="blog">
                <div class="article">
                    <div class="article_top">
                        <ul>
                            <a href="${pageContext.request.contextPath}/findInformationServlet?id=look&bossId=${name.id}">

                            <li><img  style="width:40px;height:40px;" src="${pageContext.request.contextPath}${blog.head}" alt=""></li>
                                <li>${blog.name}</a></li><br>
                            <li><a style="font-size: small ;margin-left:-10px">${fn:substring(blog.creatAtAndName,0,19)}</a></li>

                            <li class="delete_weibo" >
                                <div class="delete_box">
                                    <ul>
                                        <li style="border-top:0px;"><a href="${pageContext.request.contextPath}/deleteBlogServlet?time=${blog.creatAtAndName}&id=own">删除</a></li>
                                    </ul>
                                </div>
                            </li>
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
                                            <a href="${pageContext.request.contextPath}/findInformationServlet?id=look&bossId=${com.bossId}">
                                            <img  src="${pageContext.request.contextPath}${com.head}" alt="">
                                                <p class="reply_user">${com.name}:</p></a><p>${com.comment}</p>
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

        function repair1(){
            var form1 = new Array(15);
            var form_control = new Array(15);
            var wri = document.getElementById('wri')
            var loa = document.getElementById('loa')
            form1 = document.getElementsByClassName('form1')
            form_control = document.getElementsByClassName('form_control')
            for (var i = 0; i < form1.length; i++) { 
                form1[i].style.display = "none"
                form_control[i].style.display = "inline-block"
            }
            wri.style.display = "none"
            loa.style.display = "inline-block"
        }
        function load1(){
            var wri = document.getElementById('wri')
            var loa = document.getElementById('loa')
            var form1 = new Array(15);
            var form_control = new Array(15);
            form1 = document.getElementsByClassName('form1')
            form_control = document.getElementsByClassName('form_control')
            for (var i = 0; i < form1.length; i++) {
                form1[i].style.display = "inline-block"
                form_control[i].style.display = "none"
            }
            loa.style.display = "none"
            wri.style.display = "inline-block"
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

        function F_Open_dialog()
        {
            document.getElementById("btn_file").click();
        }
        /*点击弹出按钮*/
        function popBox() {
            var popBox = document.getElementById("popBox");
            var popLayer = document.getElementById("popLayer");
            popBox.style.display = "block";
            popLayer.style.display = "block";
        };

        /*点击关闭按钮*/
        function closeBox() {
            var popBox = document.getElementById("popBox");
            var popLayer = document.getElementById("popLayer");
            popBox.style.display = "none";
            popLayer.style.display = "none";
        }
    </script>
</body>
</html>