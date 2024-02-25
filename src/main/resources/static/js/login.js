
function login(){
//    formValidate();
}
//이메일 중복확인
function emailDuplication(){
    let email = $('#email').val();
    if(email == ''){
        alert("이메일을 입력해주세요.");
        return false;
    }
    if(!emailValidate(email)){
        alert("올바른 이메일 형식으로 입력하세요.");
        return false;
    }
    $.ajax({
        type:"get",
        url:"/emailDuplication/"+email,
        success:function(result){
            alert(result);
            $('input[name=dupFlag]').attr('value', true);
            $('input[name=emailOrg]').attr('value', email);
        },
    });
}
//이메일 정규식
function emailValidate(email){
    const pattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;
    if(pattern.test(email) == false){
        return false;
    }
        return true;
}

//폼 유효성 검사
function formValidate(){
    const loginForm = $('#loginForm');
    loginForm.validate({
        rules:{
            email:{
                required:true,
                email:true
            },
            pwd:{
               required:true,
               minlength: 8
            }
        },
        messages:{
            email:{
                required:'이메일을 입력해주세요.',
                email: '올바른 이메일 형식으로 입력하세요.'
            },
            pwd:{
                required:'비밀번호를 입력해주세요.',
                minlength : '최소 {0}글자 이상 입력하세요.'
            }
        }
    });
}