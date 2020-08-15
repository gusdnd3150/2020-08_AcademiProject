$(document).ready(function() {

    $("#member_id").focus();

    $("#checkId").click(function (e) {
        e.preventDefault();

        if($("#member_id").val()==""){
            alert("ID를 입력후 눌러주세요");
            $("#member_id").focus();
            return;
        }

        let data = {
            member_id: $('#member_id').val(),
            command: $('#check').val()
        };
        console.log(data);

        $.ajax({
            type: "POST",
            url: "/member",
            data: data,
            success: function (response) {
                if (response == 1) {
                    $('#result').text('이미 등록된 아이디 입니다').css("color", "red");
                } else if (response == 0) {
                    $('#result').text('사용가능한 아이디 입니다').css("color", "blue");
                    $('#idCheckResult').val("1");
                }
            },
            error: function (err) {
                console.log("error!");
            }
        });
    });

    function is_null_field() {
        if($("#member_id").val()==''){
            alert("ID를 입력해주세요");
            return false;
        }else if($("#name").val()==''){
            alert("이름을 입력해주세요");
            return false;
        }else if($("#password").val()==''){
            alert("비밀번호를 입력해주세요");
            return false;
        }else if($("#confirm").val()==''){
            alert("비밀번호 확인을 입력해주세요");
            return false;
        }
        return true;
    }

    $(document).on("click", "#join-submit", function (e) {
        e.preventDefault();

            if(!is_null_field()){
                return false;
            }else if($("#idCheckResult").val()=="0"){
                alert("ID중복버튼을 확인해주세요");
                return false;
            }else if($("#password").val()!=$("#confirm").val()){
                alert("비밀번호와 재입력이 일치하지 않습니다");
                return false;
            }

            let data = {
                member_id: $('#member_id').val(),
                name: $('#name').val(),
                password: $('#password').val(),
                command: $('#command').val()
            };

            console.log(data);

            $.ajax({
                type: "POST",
                url: "/member",
                data: data,
                success: function (response) {
                    window.location.href = "./addMemberOk.jsp?name="+data.name;
                },
                error: function (err) {
                    console.log("error!");
                }
            });
        });
});
