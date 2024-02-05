
    function showPass(){
        var x = document.getElementById("password");
        if(x.type === "password"){
            x.type="text";
        }else{
            x.type="password";
        }
    }


    $(document).ready(function(){
        $('#passver, #password').on('keyup', function() {
            if($('#password').val() == $('#passver').val()){
                $('#message').html('Matching').css('color', 'green');
            }else
                $('#message').html('Not Matching').css('color', 'red');
        }) ;
    });


    function passStrength(){
        var x = document.getElementById("password");
        var digits = 0;
        var objlen = 0;
        var  obj={};
        if(x.value.length<8){
            document.getElementById('passstr').style.color='red';
            document.getElementById('passstr').innerHTML='Type at least 8 characters';
        }else{
            for(var i=0;i<x.value.length;i++){
                if(!isNaN(x.value.charAt(i)))digits++;
                var k = x.value.charAt(i);
                if(isNaN(obj[k])){
                    obj[k]=1;
                    objlen++;
                }else{
                    obj[k]=obj[k]+1;
                }
            }
            for(i=0;i<x.value.length;i++){
                if(obj[x.value.charAt(i)]>=x.value.length/2){
                    document.getElementById('passstr').style.color='red';
                    document.getElementById('passstr').innerHTML='Weak';
                    return ;
                }
            }
            if(digits>=x.value.length/2){
                document.getElementById('passstr').style.color='red';
                document.getElementById('passstr').innerHTML='Weak';
            } else if(objlen>=0.8*x.value.length) {
                document.getElementById('passstr').style.color='green';
                document.getElementById('passstr').innerHTML='Strong';
            }else{
                document.getElementById('passstr').style.color='blue';
                document.getElementById('passstr').innerHTML='Medium';

            }
        }
    }

    var lat = document.createElement('double');
    lat.id='lat';
    lat.name='lat';
    lat.value=2384.1230;

    var lon = document.createElement('double');
    lon.id='lon';
    lon.name='lon';
    lon.value=2938.291;

    var patho = document.createElement('input');
    patho.type = 'radio';
    patho.id = 'specialty';
    patho.name= 'specialty';
    patho.value='Pathologist';
    patho.required;

    var label = document.createElement('label')
 
    var description = document.createTextNode('Pathologist');
    label.appendChild(description);

    var gen = document.createElement('input');
    gen.type = 'radio';
    gen.id = 'specialty';
    gen.name = 'specialty';
    gen.value='General Doctor';
    gen.required;
    var label2 =document.createElement('label2');

    description=document.createTextNode('General Doctor');
    label2.appendChild(description);
 
    var newline = document.createElement('br');

    var info = document.createElement('textarea');
    info.id='doctor_info';
    info.name='doctor_info';
    info.innerHTML = 'Information about the doctor.';
    info.rows='4';
    info.cols='50';

    var label3 =document.createElement('label3');


    description=document.createTextNode('Doctor id:');
    label3.appendChild(description);

    var docid = document.createElement('input');
    docid.type='text';
    docid.id='doctor_id';
    docid.required;


    function docInfo() {
        document.getElementById('Doctor').disabled = true;
        var span = document.getElementById('doc');

        span.appendChild(patho);
        span.appendChild(label);
        span.appendChild(gen);
        span.appendChild(label2);
        span.appendChild(newline);
        span.appendChild(info);
        /*span.appendChild(newline);
        span.appendChild(label3);
        span.appendChild(docid);*/
        document.getElementById('Address').innerText="Doctor's Address:";
    }

    function removeDoc(){
        document.getElementById('Doctor').disabled = false;
        var span = document.getElementById('doc');
        span.removeChild(patho);
        span.removeChild(label);
        span.removeChild(gen);
        span.removeChild(label2);
        span.removeChild(newline);
        span.removeChild(info);
        /*span.removeChild(label3);
        span.removeChild(docid);*/
        document.getElementById('Address').innerText="Address:";
    }

    function dat(){
        var x =document.getElementById('birthdate').value;
        document.getElementById('amka').value=x.charAt(8)+x.charAt(9)+x.charAt(5)+x.charAt(6)+x.charAt(2)+x.charAt(3);
    }

    function exist(){

        const data = null;

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
    
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                const obj = JSON.parse(xhr.responseText);
                var text=obj[0].lat;
                var text2=obj[0].lon;
                document.getElementById("lat").value=text;
                document.getElementById("lon").value=text2;
                document.getElementById("loco").innerHTML =xhr.responseText;
            }
        });
        
        
        var addressName=document.getElementById('address').value;
        var city=document.getElementById('city').value;
        var country=document.getElementById('country').value;
        var address=addressName+" " +city+" "+country; 
    
        xhr.open("GET", "https://forward-reverse-geocoding.p.rapidapi.com/v1/search?q="+address+"&accept-language=en&polygon_threshold=0.0");
        xhr.setRequestHeader("x-rapidapi-host", "forward-reverse-geocoding.p.rapidapi.com");
        xhr.setRequestHeader("x-rapidapi-key", "0688173be5msh09ac8d4e1f02017p176c65jsn39c74d24e6fa");
    
        xhr.send(data);

    }

    $(document).ready(function(){
        $("#email").change(function(){
            var email= $("#email").val();
            $.ajax({
                url : 'MyServlet',
                data : {email:email},
                type : 'GET',
                success : function(response){
                    $('#checkEmail').html(response);
                }
            });
        });
    });

    $(document).ready(function(){
        $("#username").change(function(){
            var username= $("#username").val();
            $.ajax({
                url : 'UserNameCheckServlet',
                data : {username:username},
                type : 'GET',
                success : function(response){
                    $('#checkUsername').html(response);
                }
            });
        });
    });

    $(document).ready(function(){
        $("#amka").change(function(){
            var amka= $("#amka").val();
            $.ajax({
                url : 'amkaCheckServlet',
                data : {amka:amka},
                type : 'GET',
                success : function(response){
                    $('#checkAmka').html(response);
                }
            });
        });
    });

    /*$(document).ready(function(){
        $("#userlist").click(function(){

            $.ajax({
                url: 'userlistServlet',
                data: {},
                type : 'GET',
                success : function(response){
                    $('#userspan').innerHTML(response);
                }
            });
        });
    });*/


    function userList(){

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("userspan").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("POST", "userlistServlet",true);
        xhttp.send();
    }

    function deleteuser(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("delresult").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("POST", "adminfeaturesServlet?deleteuser="+document.getElementById("deleteuser").value,true);
        xhttp.send();
    }

    function certifydoc(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("certresult").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("GET", "adminfeaturesServlet?certifydoc="+document.getElementById("certifydoc").value,true);
        xhttp.send();
    }

    function changePass(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("passch").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("GET", "changePassServlet?changepass="+document.getElementById("changepass").value,true);
        xhttp.send();
    }

    function changeMail(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("mailch").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("GET", "changeMailServlet?changemail="+document.getElementById("changemail").value,true);
        xhttp.send();
    }

    function logoutt(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                location.href = 'index.jsp';
            }
        }
        xhttp.open("POST", "changeMailServlet",true);
        xhttp.send();
    }

    function seeDoc(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("sedoc").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("GET", "seeDocServlet",true);
        xhttp.send();
    }

    function bookRan(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("bookres").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("POST", "seeDocServlet?doctor_id="+document.getElementById("bookran").value,true);
        xhttp.send();
    }

    function checkBT(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("checkbt").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("POST", "checkbtandtServlet",true);
        xhttp.send();
    }

    function checkAT(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("checkat").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("GET", "checkbtandtServlet",true);
        xhttp.send();
    }

    function seePatient(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("seep").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("GET", "seePatientServlet",true);
        xhttp.send();
    }

    function guestShow(){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("guestsh").innerHTML=xhttp.responseText;
            }
        }
        xhttp.open("GET", "guestShowServlet",true);
        xhttp.send();
    }
    /*$(document).ready(function(){
        $("#loginbtn").click(function(){
            var id= $("#usrname").val();
            var pass=$("#passwrd").val();
            $.ajax({
                url: 'loginServlet',
                data: {id:id,pass:pass},
                type : 'GET',
                success : function(response){
                    $('#kool').html(response);
                }
            });
        });
    });*/


    /*<html>
<head>
    <script type="text/javascript" src="1.js"></script>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<label for="usrname">Username:
    <input type="text" id="usrname" name="usrname" required> <br><br>
</label>
<label for="passwrd">Password:
    <input type="password" id="passwrd" name="passwrd"  required >
    </label><br><br>
<button type="button" id="loginbtn" name="loginbtn">Login<span id="kool"></span></button>

</body>

</html> */