<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
	function show() {
		var input = $("#input").val();
		var uri = null;
		$.ajax({
			type : "post",
			url : $(event.target).attr('id')+".test",
			data : "input="+input,
			success : function(data,textStatus) {
		        var answer = JSON.parse(data);
		        console.log(answer);
		        document.getElementById("input").value = answer.id;
		        document.getElementById("id").value = answer.id;
		        document.getElementById("pw").value = answer.pw;
		        document.getElementById("name").value = answer.name;
			},
			error:function(data, textSatus){
				console.log('error');
			},
			complete:function(data, textSatus){			
			}
		});
	}
	
	function add() {
		document.getElementById("input").value = "";
        document.getElementById("id").value = "";
        document.getElementById("pw").value = "";
        document.getElementById("name").value = "";
	}
	function save() {
		var id = $("#id").val();
		var pw = $("#pw").val();
		var name = $("#name").val();
		$.ajax({
			type : "post",
			url : "save.test",
			data : "id="+id+"&pw="+pw+"&name="+name,
			success : function(data,textStatus) {
		        alert(data);
			},
			error:function(data, textSatus){
				console.log('error');
			},
			complete:function(data, textSatus){			
			}
		});
	}
</script>
</head>
<body>
<input type="text" id="input">
<button onclick="show()" id="show">조회</button>
<button onclick="add()">추가</button>
<button onclick="save()">저장</button>
<button onclick="show()" id="prev">이전</button>
<button onclick="show()" id="next">이후</button><br>
고객ID<input type="text" id="id"><br>
고객PW<input type="text" id="pw"><br>
고객이름<input type="text" id="name">
</body>
</html>