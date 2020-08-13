<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function idsearch() { //조회 함수
		var _id = $('#_id').val();
		$.ajax({
			type : 'post',
			url : '/member/idsearch.do',
			data : {
				id : _id
			},
			success : function(data) {
				if ("nonMember" != (data)) {
					var Jdata = JSON.parse(data)
					//for (i in Jdata.member) { // 여러게의 회원 목록을 가져 오느것이 아니라서 for문까지 돌릴 필요 없음
					$('#memberId').val(Jdata.member.id);
					$('#memberPwd').val(Jdata.member.pwd);
					$('#memberName').val(Jdata.member.name);
					//}
				} else {
					$('#msg').html("등록되지 않은 아이디입니다.");
					$('#msg').css('color', 'red');
					setTimeout(removeMsg, 3000);
				}
				;
			}
		});
	};

	function add() { //저장 함수
		var _id = $('#memberId').val();
		var _pwd = $('#memberPwd').val();
		var _name = $('#memberName').val();
		$.ajax({
			type : 'post',
			url : '/member/add.do',
			data : {
				id : _id,
				pwd : _pwd,
				name : _name
			},
			success : function(data) {
				if ("등록완료" == (data)) {
					$('#msg').html(_name + "님 등록이 완료 되었습니다.");
					$('#msg').css('color', 'blue');
					setTimeout(removeMsg, 3000);

				} else if ("수정완료" == (data)) {
					$('#msg').html(_name + "님 수정이 완료 되었습니다.");
					$('#msg').css('color', 'blue');
					setTimeout(removeMsg, 3000);

				} else {
					$('#msg').html("등록 실패");
					$('#msg').css('color', 'red');
					setTimeout(removeMsg, 3000);
				};
			}
		});
	};

	function lag() { //이전
		var _id = $('#memberId').val();
		$.ajax({
			type : 'post',
			url : '/member/lag.do',
			data : {
				id : _id
			},
			success : function(data) {
				if ("null" != (data)) { // null이 아니면 회원 데이터 출력
					var Jdata = JSON.parse(data)
					$('#memberId').val(Jdata.member.id);
					$('#memberPwd').val(Jdata.member.pwd);
					$('#memberName').val(Jdata.member.name);
				} else { //null 이면 메세지 내용 출력
					$('#msg').html("조회 할 회원 목록이 없습니다.");
					$('#msg').css('color', 'red');
					setTimeout(removeMsg, 3000);
				}
				;

			}
		});
	};

	function lead() { // 이후
		var _id = $('#memberId').val();
		$.ajax({
			type : 'post',
			url : '/member/lead.do',
			data : {
				id : _id
			},
			success : function(data) {
				if ("null" != (data)) { // null이 아니면 회원 데이터 출력
					var Jdata = JSON.parse(data)
					$('#memberId').val(Jdata.member.id);
					$('#memberPwd').val(Jdata.member.pwd);
					$('#memberName').val(Jdata.member.name);
				} else {//null 이면 메세지 내용 출력
					$('#msg').html("조회 할 회원 목록이 없습니다.");
					$('#msg').css('color', 'red');
					setTimeout(removeMsg, 3000);
				};
			 }
		});
	};

	function removeMember() {//추가 함수 내용 지우기
		$('#_id').val('');
		$('#memberId').val('');
		$('#memberPwd').val('');
		$('#memberName').val('');
	};

	function removeMsg() { // 메세지 내용 삭제
		$('#msg').html("");
	};
	
</script>
</head>
<body>
	<div class="num1">
		<table class="memberTable"
			style="text-align: center; border: 1px solid #dddddd;">
			<thead>
				<tr>
					<th colspan="6"><h4>프로젝트</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 110px;"><button class="btn_1"
							onclick="idsearch()" type="button">조회</button></td>
					<td style="width: 110px;"><button class="btn_2"
							onclick="removeMember()" type="button">추가</button></td>
					<td style="width: 110px;"><button class="btn_3"
							onclick="add()" type="button">저장</button></td>
					<td style="width: 110px;"><button class="btn_4"
							onclick="lag()" type="button">이전</button></td>
					<td style="width: 110px;"><button class="btn_5"
							onclick="lead()" type="button">이후</button></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>아이디조회</h5></td>
					<td><input class="formControl" type="text" id="_id"
						maxlength="20" placeholder="조회 아이디입력"></td>
				</tr>
				<tr>
					<td><h5>아이디</h5></td>
					<td><input class="formControl" type="text" id="memberId"></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>비밀번호</h5></td>
					<td><input class="formControl" type="text" id="memberPwd"></td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>이름</h5></td>
					<td><input class="formControl" type="text" id="memberName"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<h3 id="msg"></h3>
</body>
</html>