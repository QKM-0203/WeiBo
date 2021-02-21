<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style type="text/css">
        *{
            margin: 0px;
            padding: 0px;
        }
        html,body{
            height:100%;
            background-repeat: no-repeat;
            background-size: 100% 100%;
            font-size: 12px;
        }
        .content{

            margin: 0px;
            padding: 0px;
            border: 0px;
            font-size: 100%;
        }
        .logo{
            width: 500px;
            height: 150px;
            margin: 0 auto;
            background: center no-repeat;
        }
        .login-box{

            width: 360px;
            background: #FFFFFF;
            position: absolute;
            left: 40%;


        }
        .login-title{
            width: 300px;
            color: #888888;
            text-align: center;
            padding: 15px 0;
            margin: 0 auto;
            border-bottom: 1px solid #cccccc;
            font-size: 18px;
        }
        .one{
            width: 300px;
            height: 40px;
            border: 1px  #CCCCCC;
            margin: 20px auto 0 auto;
            background: #FFFFFF;
        }
        .one input{
            width: 100%;
            font-size: 15px;
            font-family: "微软雅黑";
            color:rgb(102,102,102);
            padding: 12px 5px;
            margin:  0px;
            box-sizing: border-box;

        }
        input:focus {
            outline: none;
            border:1px solid #cccccc;
            box-shadow: rgb(187,187,187)0px 0px 3px;;
        }
        {
            border: none;
        }
        .warning{
            font-size: 13px;
            color: red;
            width: 300px;
            margin: 0 auto;

        }
        .submit{
            width: 300px;
            height: 40px;
            color: white;
            margin: 10px auto;
        }
        .submit button{
            width:100%;
            height:100%;
            color:white;
            font-size:14px;
            cursor:pointer;
            background:red;
            border:none;
        }
        .login{
            width: 300px;
            margin: 0 auto;
            font-size: 14px;
            color: #666;
        }
        .others{
            overflow: hidden;
            width: 300px;
            height: 80px;
            line-height: 80px;
            margin: 0px auto;
        }
        .other-left{
            float: left;
            font-size: 14px;
            color: #999;
        }
        .other-right{
            float: right;
        }
        a{
            color:#3cf;
            text-decoration: none;
        }
    </style>
</head>
<body>
                <div class="login">
                    <a href="${pageContext.request.contextPath}/findInformationServlet?id=0">个人中心</a>
                    <a href="${pageContext.request.contextPath}/findBlogServlet?id=0">发布博客</a>

                </div>

</body>
</html>