<%--
  Created by IntelliJ IDEA.
  User: qikaimeng
  Date: 2021/1/26
  Time: 下午3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style type="text/css">

        /* 背景圆样式 */

        .box .circle {
            position: absolute;
            background: rgba(255, 255, 255, 0.1);
            /* backdrop-filter属性为一个元素后面区域添加模糊效果 */
            backdrop-filter: blur(5px);
            box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.5);
            border-right: 1px solid rgba(255, 255, 255, 0.2);
            border-bottom: 1px solid rgba(255, 255, 255, 0.2);
            border-radius: 50%;
            /* 使用filter(滤镜) 属性，改变颜色。
            hue-rotate(deg)  给图像应用色相旋转
            calc() 函数用于动态计算长度值
            var() 函数调用自定义的CSS属性值x*/
            filter: hue-rotate(calc(var(--x) * 70deg));
            /* 调用动画animate，需要10s完成动画，
            linear表示动画从头到尾的速度是相同的，
            infinite指定动画应该循环播放无限次*/
            animation: animate 10s linear infinite;
            /* 动态计算动画延迟几秒播放 */
            animation-delay: calc(var(--x) * -1s);
        }

        /* 背景圆动画 */

        @keyframes animate {
            0%,100%{
                transform: translateY(-50px);
            }
            50% {
                transform: translateY(50px);
            }
        }

        .box .circle:nth-child(1) {
            top: -50px;
            right: -60px;
            width: 100px;
            height: 100px;
        }

        .box .circle:nth-child(2) {
            top: 150px;
            left: -100px;
            width: 120px;
            height: 120px;
            z-index: 2;
        }

        .box .circle:nth-child(3) {
            bottom: 50px;
            right: -60px;
            width: 80px;
            height: 80px;
            z-index: 2;
        }

        .box .circle:nth-child(4) {
            bottom: -80px;
            left: 100px;
            width: 60px;
            height: 60px;
        }

        .box .circle:nth-child(5) {
            top: -80px;
            left: 140px;
            width: 60px;
            height: 60px;
        }
        html,body{
            height:100%;
            color:rgb(51,51,51);
            font-size:12px ;
        }
        *{
            padding: 0px;
            margin: 0px;
        }
        .content{
            position: absolute;
            left: 25%;
            top: 15%;
            margin: 5px;
            padding:0px;
            border: 5px;
            font-size:100%;
            height: 410px;
            width: 730px;
            border: solid 1px #CCCCCC;
        }
        .pic{
            width: 350px;
            height: 375px;
            margin: 14px 5px;
            padding: 0px;
            display: inline-block;
        }
        .login-box{
            width: 360px;
            background: #FFFFFF;
            padding: 0px;
            margin: 0px;
            display: inline-block;
        }
        .login-form{
            width: 360px;
            margin: 0px;
            padding: 0px;
        }
        .login-title{
            font-size: 18px;
            text-align: center;
            color: #888888;
            padding: 15px 0;
            width: 300px;
            margin: 0 auto;
            border-bottom: solid 1px #CCCCCC;
        }
        .text1{
            width: 300px;
            height: 40px;
            border: 1px  #CCCCCC;
            margin: 20px auto 0 auto;
            background: #FFFFFF;
        }
        .text1 input{
            width: 290px;
            height: 45px;
            margin: 5px;
            padding: 5px;
            font-size: 15px;
            font-family: "微软雅黑";
            color:rgb(102,102,102);
            box-sizing: border-box;
        }
        input:focus {
            outline: none;
            border:5px solid white;
            box-shadow: rgb(187,187,187)0px 0px 3px;
        }
        .warning1{
            font-size: 13px;
            color: red;
            width: 300px;
            margin: 0 auto;
            display: none;
        }
        .text2{
            width: 300px;
            margin:20px auto 0px auto;
            overflow: hidden;
        }
        .left{
            float: left;
            margin: 5px;
        }
        .right{
            float:right;
        }
        .text2 label{
            cursor: pointer;
        }
        .text2 label input{
            position: relative;
            top: 5px;
        }
        a{
            color:#3cf;
            text-decoration: none;
        }
        .btn{
            width: 300px;
            height: 40px;
            margin: 10px auto;
        }
        .btn button{
            width:100%;
            height:100%;
            color:white;
            font-size:14px;
            cursor:pointer;
            background:red;
            border:none;
        }
        .botm{
            width: 300px;
            margin: 0 auto;
            font-size: 14px;
            color: #666;
        }

        .warning1 span.active{
            color:red;
        }
    </style>

    <script type="text/javascript">
        //切换验证码
        function refreshCode(){
            var  code = document.getElementById("code");
            code.src = "${pageContext.request.contextPath}/CheckCode?time="+new Date().getTime();
        }
    </script>
</head>
<body>
<form action = "${pageContext.request.contextPath}/LoginServlet" method = "post" >
    <!--要居中-->
    <div class="head">
        <div class="logo1">微博</div>
        <div class="logo2">--随时随地发现新鲜事</div>
    </div>

    <div class="content">
        <!-- 背景颜色 -->
        <div class="color"></div>
        <div class="color"></div>
        <div class="color"></div>
        <div class="box">
            <!-- 背景圆 -->
            <div class="circle" style="--x:0"></div>
            <div class="circle" style="--x:1"></div>
            <div class="circle" style="--x:2"></div>
            <div class="circle" style="--x:3"></div>
            <div class="circle" style="--x:4"></div>
        <div class="pic">
            <img src="${pageContext.request.contextPath }/img/pic.jpg" alt="" width="350px" height="375px" >
        </div>

        <div class="login-box">
            <div class="login-form">
                <div class="login-title">登录</div>
                <form action="" method="post">
                    <div class="text1" >
                        <input name="boss" type="text" placeholder="手机号/邮箱" value="${cookie.Name.value}" >
                    </div>
                    <div class="warning1">
                        <span>数据不能为空</span>
                    </div>
                    <div class="text1" >
                        <input name="password" type="password" placeholder="密码" value="${cookie.password.value}" >
                    </div>
                    <div class="warning1"></div>
                    <div class="text2">
                        <div class="left">
                            <label><input name="remember" value="23" type="checkbox" checked="checked" >自动登录</label>
                        </div>
                        <div class="right">
                            <a href="findPassword.jsp">忘记密码</a>
                        </div>
                    </div>
                    <div style="text-align: center" ><br><input type ="text" placeholder="验证码" name = "checkBoard" style="width: 100px ;height: 30px">
                        <img src="${pageContext.request.contextPath}/CheckCode" title="看不清点击刷新" id="code"/>
                        <a href="javascript:refreshCode();">看不清?换一张</a>
                    </div>
                    <div class="btn" >
                        <button type="submit">登录</button>
                    </div>
                    <div class="botm">
                        <span>还没有账号?</span>
                        <a href="registration.jsp">马上注册</a>
                    </div>
                </form>
                <div class="login-others">
                    <div class="left-other">
                        <span></span>
                    </div>
                    <div style="text-align:center; font-size: medium" >
                        ${sessionScope.ku}
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>