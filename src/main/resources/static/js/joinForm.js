
function join(){
     let emailOrg = $('input[name=emailOrg]').val();
     let email = $('#email').val();
     let dupFlag = $('input[name=dupFlag]').val();
         //이메일 중복여부 확인 true일 경우에만 진행
//  if(emailOrg == email && emailOrg != ''){
//        return;
//  }else
    if(dupFlag == "false"){
       alert("이메일 중복확인을 진행해주세요.");
       return false;
   }else if(emailOrg != email || emailOrg == ''){
        alert("이메일 중복확인을 해주세요.");
        return false;
      }
      else{
      return;
      }
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
        let message;
        if(result == 1){
           message = "이미 가입된 이메일입니다.";
          $('input[name=dupFlag]').attr('value', false);
        }else{
           message = "사용 가능한 이메일입니다.";
           $('input[name=dupFlag]').attr('value', true);
        }
            alert(message);

            $('input[name=emailOrg]').attr('value', email);
        },
    });
}
function isEmpty(){
    let email = $('#email').val();
    let name = $('#name').val();
    let pwd = $('#pwd').val();
    let pwd_2 = $('input[name=pwd_2]').val();
    let checked = $('#terms').is(':checked');
    formValidate();
    if(email != '' && name != '' && pwd != '' && pwd_2 != '' && checked){
        $('.joinBtn').attr('disabled', false);
    }else{
        $('.joinBtn').attr('disabled', true);
    }
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
    const boardForm = $('#boardForm');
    boardForm.validate({
        rules:{
            email:{
                required:true,
                email:true
            },
            name:{
                required:true,
                minlength:2
            },
            pwd:{
               required:true,
               minlength: 8
            },
            pwd_2:{
              required:true,
              minlength: 8,
              equalTo:pwd
            }
        },
        messages:{
            email:{
                required:'이메일은 필수 입력 항목입니다.',
                email: '올바른 이메일 형식으로 입력하세요.'
            },
            name:{
                required:'이름은 필수 입력 항목입니다.',
                minlength : '최소 {0}글자 이상 입력하세요.'
            },
            pwd:{
                required:'비밀번호는 필수 입력 항목입니다.',
                minlength : '최소 {0}글자 이상 입력하세요.'
            },
            pwd_2:{
                required:'비밀번호는 필수 입력 항목입니다.',
                minlength : '최소 {0}글자 이상 입력하세요.',
                equalTo : '동일한 비밀번호를 입력해 주세요.'
           }
        }
    });
}