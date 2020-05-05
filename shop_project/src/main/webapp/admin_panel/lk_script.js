let user_data={
    avatar: "",
    city: "",
    country: "",
    first_name: "",
    flag: false,
    id: 0,
    imail: "",
    login: "",
    mail_index: 000000,
    password: "",
    phone_number: 00000000000,
    second_name: "",
    street: "",
    third_name: ""
};

const authorizate_block = function(){
    $(".lk-onClkk").click(function(){
        if (user_data.flag) ok();
        else {
            $(".login-form").css("display", "block");
            if($("div").hasClass("blockall")) $(".blockall").remove();
            else $(".tytoknoall").append('<div class="blockall"></div>');
        }
    });
    $(".tytoknoall").click(function(){
        $(".login-form").css("display", "none");
        $(".reg-form").css("display", "none");
        $(".blockall").remove();
    });
    $("#SignIn").click(function(){
        let login = $("#inp-login").val();
        let pass = $("#inp-pass").val();
        
        if (login==="" || pass==="") alert("Не введены логин или пароль");
        else authorizate(login, pass);
    });   
};
const authorizate = async function(login, pass){
    $("#reg-ref").click(function(){
        $(".login-form").css("display", "none");
        $(".reg-form").css("display", "block");
    });
    await fetch('/shop_project/api/authoriz', {
        method: 'POST', 
        headers: {'Content-Type': 'application/json;charset=utf-8'}, 
        body: JSON.stringify({
            login: login,
            password: pass,
            id: 0,
            city: "",
            country: "",
            street: "",
            imail: "",
            first_name: "",
            second_name: "",
            third_name: "",
            mail_index: 000000,
            phone_number: 00000000000,
            avatar: "",
            flag: false
        })
    }).then(async function(response) { 
        if (response.ok) {
            user_data = await response.json();
            if(user_data.flag) {
                await ok(); 
                $(".login-form").css("display", "none");
                $(".reg-form").css("display", "none");
                $(".blockall").remove();
            } else alert("Неверный логин или пароль");
        }
        else {
            //console.log(response);
            throw response.text();
        }	       
    });
};

const ok = function(){
    $('#user_name').html(user_data.first_name);
    $('.main').html("<div class='container lk-body'>"+
              "<div class='lk-block-data'>"+
                  "<div class='col-lg-12 lk-header'>Личные данные</div>"+
                  "<div class='col-lg-12 lk-block-body'>"+
                      "<div style='display: flex; flex-direction: row; margin: 15px 0 15px 0;'>"+
                          "<div><img src='"+ user_data.avatar +"'><p style='font-size: 15px; margin: 2px 0 0 0; text-align: center;'>Изменить аватар</p></div>"+
                          "<div style='display: flex; align-items: center; justify-content: center'>"+
                              "<div style='margin:0 0 0 20px'>"+
                                "<p><span>"+ user_data.first_name +"</span><img style='padding: 3px; width: 20px; height: 20px; border-radius: 8px 0 8px 0; margin: 0 0 0 20px; box-shadow: 0 0 4px 0 rgba(0,0,0,0.5);' src='images/pen.png'></p>"+
                                "<p><span>"+ user_data.second_name +"</span></p>"+
                                "<p><span>"+ user_data.third_name +"</span></p>"+
                              "</div>"+
                          "</div>"+
                      "</div>"+
                      "<div style='border-top: 1px solid lightgrey; padding: 15px 0 15px 15px;'>"+            
                        "<p><span>Страна: </span><span>"+ user_data.country +"</span><img style='padding: 3px; width: 20px; border-radius: 8px 0 8px 0; height: 20px; margin: 0 0 0 20px; box-shadow: 0 0 4px 0 rgba(0,0,0,0.5);' src='images/pen.png'></p>"+
                        "<p><span>Город: </span><span>"+ user_data.city +"</span></p>"+
                        "<p><span>Улица, дом: </span><span>"+ user_data.street +"</span></p>"+
                        "<p><span>Почтовый индекс: </span><span>"+ user_data.mail_index +"</span></p>"+
                        "<p><span>Телефон: </span><span>"+ user_data.phone_number +"</span></p>"+
                        "<p><span>Почта: </span><span>"+ user_data.imail +"</span></p>"+
                      "</div>"+
                  "</div>                  "+
              "</div>"+
              "<div class='lk-block-orders'>"+
                  "<div class='col-lg-12 lk-header'>Заказы</div>"+
                  "<div class='lk-suborder-header'>"+
                      "<p>На рассмотрении</p>"+
                  "</div>"+
                  "<div class='lk-suborder-body' id='onView'>"+
                      "<div class='lk-order-description'>"+
                          "<div><img src='images/refrigerator.png'></div>"+
                          "<div style='margin: 0 0 0 20px;'>"+
                              "<div>"+
                                "<p><span>Холодос Mdawdaw2321</span></p>"+
                                "<p><span>x1</span></p>"+
                                "<p><span>Дата заказа: </span><span>12.31.2019</span></p>"+
                                "<div style='display: flex; align-items: flex-end; width: 100%'><p><span>Сумма заказа: </span><span>23222руб</span></p></div>"+
                              "</div>"+
                          "</div>"+
                      "</div>"+
                      "<div class='lk-order-description'>"+
                          "<div>"+
                              "<img src='images/refrigerator.png'>"+
                          "</div>"+
                          "<div style='margin: 0 0 0 20px;'>"+
                              "<div>"+
                                "<p><span>Холодос Mdawdaw2321</span></p>"+
                                "<p><span>x1</span></p>"+
                                "<p><span>Дата заказа: </span><span>12.31.2019</span></p>"+
                                "<div style='display: flex; align-items: flex-end; width: 100%'><p><span>Сумма заказа: </span><span>23222руб</span></p></div>"+
                              "</div>"+
                          "</div>"+
                      "</div>"+
                  "</div>"+
                  "<div class='lk-suborder-header'>"+
                      "<p>В пути</p>"+
                  "</div>"+
                  "<div class='lk-suborder-body' id='onDelivery'>"+
                      "<p id='Nope'>Отсутствуют</p>"+
                  "</div>"+
                  "<div class='lk-suborder-header'>"+
                      "<p>Завершенные</p>"+
                  "</div>"+
                  "<div class='lk-suborder-body' id='onClose'>"+
                      "<div class='lk-order-description'>"+
                          "<div>"+
                              "<img src='images/refrigerator.png'>"+
                          "</div>"+
                          "<div style='margin: 0 0 0 20px;'>"+
                              "<div>"+
                                "<p><span>Холодос Mdawdaw2321</span></p>"+
                                "<p><span>x1</span></p>"+
                                "<p><span>Дата заказа: </span><span>12.31.2019</span></p>"+
                                "<div style='display: flex; align-items: flex-end; width: 100%' ><p><span>Сумма заказа: </span><span>23222руб</span></p></div>"+
                              "</div>"+
                          "</div>"+
                      "</div>"+
                  "</div>"+   
              "</div>");
}

